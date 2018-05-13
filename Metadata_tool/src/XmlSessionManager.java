
/**
 * The XmlSessionManager class is a collection of Metadata Tool operating methods
 * @author SJohnston
 * @version 2.0.0
 * @date April 10, 2018 
 */

import java.io.*;

import java.lang.Object;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlSessionManager {

	// Instance Variables
	private DocumentBuilderFactory dbFact;
	private DocumentBuilder dBuild;
	private Document metaDoc;
	private static int numElementNode = 0;

	/**
	 * Default XmlSessionManager constructor
	 */
	public XmlSessionManager() {
		dbFact = null;
		dBuild = null;
		metaDoc = null;
	}

	/**
	 * saveSession takes in the root of a MetadataNode tree, a
	 * currentNode pointer and a list of xml file paths that were used as the
	 * templates for this session. The method creates a String object 
	 * represention of the state of the session at the moment it was saved. 
	 * The tree structure hierarchy is represented by stars for each level of 
	 * the tree. 
	 * 
	 * @precondition the incoming node is the desired root of the tree.
	 * @param root
	 *            is the root of the MetadataNode tree from the Metadata Tool
	 *            session.
	 * @param currentNode
	 *            is a pointer to the node that the Metadata Tool was currently
	 *            working on when the session was saved.
	 * @param templates
	 *            is an array of the file objects of all xml files used as the
	 *            templates for this session
	 * @param xMlSessionSave
	 *            is the formatted string that represents the state of the saved
	 *            Metadata Tool session.
	 * @postcondition a String of the contents of the tree using indentation
	 *                markers to represent the hierarchy
	 * @return the xmlSessionManager String. ?!? should this instead return a
	 *         File ?!?
	 */
	public String saveSession(MetadataNode<?> root, MetadataNode<?> currentNode, String[] templates) {
		String xmlSessionSave = "!MetadataNodeTree!\r";
		// File sessionFile;
	
		xmlSessionSave += metadataTreeToString(root);
		xmlSessionSave += "!EndTree!\r!Current Node!\r" + currentNode.getElement() + "\r!EndCurrentNode!\n!TemplateList!\r";
		for (int index = 0; index < templates.length; index++) {
			xmlSessionSave += templates[index] + "\r";
		}
		xmlSessionSave += "!EndTemplatesList!\r";
	
		return xmlSessionSave;
	}

	/**
	 * The openSession method reads the contents of a session.xsm file, creates
	 * a MetadataNode tree with appropriate attributes, points currentNode to
	 * the first node with the right tag and fills the array of absolute paths
	 * to the template files for the session.  
	 * 
	 * @precondition The incoming file is of type .xsm and not any other type.
	 * @param file
	 *            the incoming .xsm file.
	 * @postcondition a tree has been created with the parent/child/sibling
	 *                connections. and attributes that are identical to when the
	 *                session was saved.
	 * @return the root node to a MetadataNode tree.
	 * @throws TypeMismatchException.
	 * @throws FileNotFoundException
	 * @catch returns a new, empty node to the calling method.
	 */
	public MetadataNode<?> openSession(File file, MetadataNode<?> currentNode, String[] templates) // throws FileNotFoundException
	{
		MetadataNode<?> root = new MetadataNode<Object>("metadata", null, null);
		MetadataNode<?> newNode, parent = root;
		String sessionFileLine = new String();
		Scanner reader = null;
		String element, elementName, question, answer, verified;
		element = "UnInitialized";
		int elementIndex, elementNameIndex, questionIndex, answerIndex, verifiedIndex;
		boolean found = false;
		/*
		 * !! CAUTION !! may throw a FileNotFound exception if a user moved the session file.  
		 */		
		try {
			reader = new Scanner(file);
			// read first line of file
			sessionFileLine = reader.nextLine();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// PART1 load tree
		if (sessionFileLine.contains("!MetadataNodeTree!"))
		{
			int parentIndex = 0;
			while (reader.hasNextLine() && !(sessionFileLine.contains("!EndTree!")))
			{	
				int index = 0;				
				while (sessionFileLine.charAt(index) == '*')
				{
					index++;					
				}					
				elementIndex = sessionFileLine.indexOf(" ElementName:");
				if (index < elementIndex)
				{
					element = sessionFileLine.substring(index, elementIndex);
				}

				if (!(sessionFileLine.contains("metadata")))
				{					
					newNode = new MetadataNode(element, (MetadataNode<?>)null, (MetadataNode<?>)null);					
					// extract the remaining attributes from sessionFileLine string
					loadMetadataNodeAttributes(newNode, sessionFileLine);

					// CHILD CASE
					if (index > parentIndex) // adds child. index should only be 1 greater than parentIndex
					{
						parent.addChild(newNode);
						newNode.setParent(parent);
						parentIndex++;
					} // end add child to parent
					// SIBLING CASE 1
					else if (index == parentIndex) // add sibling. index should be equal to parentIndex
					{
						parent.addSibling(newNode);
						newNode.setParent(parent.getParent());
					} // end add sibling to parent
					// SIBLING CASE 2
					else
					{
						while (index < parentIndex) // add sibling. parentIndex should be decreased until equal to index
						{
							parent = parent.getParent();
							parentIndex--;
						}						
						parent.addSibling(newNode);
						newNode.setParent(parent.getParent());	

					} // end else add sibling to grandparent
					parent = newNode;
				} // end if not metadata root line					
				sessionFileLine = reader.nextLine();				
			} // end while not at end of tree
		} // end if sessionFileLine is reading entries for tree
		while (reader.hasNextLine() && !(sessionFileLine.contains("!Current Node!")) && !(sessionFileLine.contains("!TemplateList!")))
		{
			sessionFileLine = reader.nextLine();
		}
		// PART2 set currentNode
		if (sessionFileLine.contains("!Current Node!"))
		{
			elementIndex = sessionFileLine.indexOf('\r');
			if (elementIndex > 0)
				element = sessionFileLine.substring(0, elementIndex);
			else
				element = sessionFileLine;
			// call method that searches tree to find the node with the same element name
			found = findMetadataNodeWithName(root, currentNode, element);				
		}
		while (reader.hasNextLine() && !(sessionFileLine.contains("!TemplateList!")))
		{
			sessionFileLine = reader.nextLine();
		}
		// PART 3 fill template list
		if (sessionFileLine.contains("!TemplateList!"))
		{
			sessionFileLine = reader.nextLine();
			int index = 0;
			while (!(sessionFileLine.contains("!EndTemplateList!")))
			{
				templates[index] = sessionFileLine;
				index++;
			}
		}

		return root;		
	}	
	
	/**
	 * The loadMetadataNodeAttributes method populates the attributes of a node from
	 * a String line extracted from an session.xsm file.
	 * @param newNode the node needing attributes populated
	 * @param sessionFileLine the string from a session.xsm file containing the necessary
	 * entries for populating the node attributes.
	 */
	private void loadMetadataNodeAttributes(MetadataNode<?> newNode, String sessionFileLine) 
	{
		String elementName, question, answer, verified;
		int elementIndex, elementNameIndex, questionIndex, answerIndex, verifiedIndex;
		elementIndex = sessionFileLine.lastIndexOf("ElementName: ");
		elementNameIndex = sessionFileLine.indexOf(" Question:");
		elementName = sessionFileLine.substring(elementIndex, elementNameIndex);
		newNode.setElementName(elementName);
		elementNameIndex = sessionFileLine.lastIndexOf("Question: ");
		questionIndex = sessionFileLine.indexOf(" Answer:");
		question = sessionFileLine.substring(elementNameIndex, questionIndex);
		newNode.setQuestion(question);
		questionIndex = sessionFileLine.lastIndexOf("Answer: ");
		answerIndex = sessionFileLine.indexOf(" Verified:");
		answer = sessionFileLine.substring(questionIndex, answerIndex);
		newNode.setAnswer(answer);
		answerIndex = sessionFileLine.lastIndexOf("Verfied: ");
		verifiedIndex = sessionFileLine.indexOf('\r');
		verified = sessionFileLine.substring(answerIndex, verifiedIndex);
		newNode.setVerified((verified.equals("Y") ? true : false));		
	}

	/**
	 * The findMetadataNodeWithName method searches a tree looking for a node with a tag
	 * matching the desired string provided
	 * @param root is the node that the method whose tag is being compared to the string
	 * @param currentNode is the node that needs to be assigned a place to point to
	 * @param element is the tag the method is looking for
	 * @return true if the matching node is found, false if there is no matching node found
	 */
	private boolean findMetadataNodeWithName(MetadataNode<?> root, MetadataNode<?> currentNode, String element) 
	{
		// recursively call this method until matching node is found
		// BASE CASE - match found
		boolean found = false;
		if (root.getElement().equals(element))
		{
			currentNode = root;
			found = true;
		}
		// RECURSIVE CASE - match not found, look at the next child/sibling
		else 
		{
			if (root.getChild() != null)
			{
				root = root.getChild();
				found = findMetadataNodeWithName(root, currentNode, element);
			}
			if (root.getSibling() != null && !found)
			{
				root = root.getSibling();
				found = findMetadataNodeWithName(root, currentNode, element);
			}
		}
		return found;
	}
	

	/**
	 * file to DOM takes in a valid XML or HTML file and returns a Document
	 * Object Model
	 * 
	 * @precondition incoming file is an XML or HTML file
	 * @param file
	 *            File object from calling method
	 * @return returns a Document Object Model of the XML or HTML contents
	 *         <ELEMENT>, <ATTRIBUTE>, <CONTENT>
	 * @throws Throws
	 *             and exception if the incoming file is not an XML or HTML
	 */
	public Document fileToDOM(File file) {
		try {
			dbFact = DocumentBuilderFactory.newInstance();
			dbFact.setNamespaceAware(true);
			dBuild = dbFact.newDocumentBuilder();
			metaDoc = dBuild.parse(file);
			metaDoc.normalize();
		} catch (Exception e) {
			System.out.println(e);
			JOptionPane.showMessageDialog(null, e, "XML parsing error!",
					JOptionPane.ERROR_MESSAGE);
			metaDoc = null;
		}

		return metaDoc;
	}

	/**
	 * The importDOMToMetadata method takes in a node and returns a MetadataNode.
	 * and returns the root of a tree of MetadataNode objects. A MetadataNode
	 * object maps a DOM Element into the MetadataNode element, a DOM Content
	 * into the MetadataNode question and and sets the Metadata answer and
	 * verified variables to default.
	 * 
	 * @param dom
	 *            the root of a Document Object Model tree
	 * @param root
	 *            a MetadataNode object that is the root of the tree or subtree
	 * @return root, the root of the entire MetadataNode tree
	 */
	public MetadataNode<?> importDOMToMetadata(Node node) {
		// starting over:
		Node sibling, child;
		MetadataNode <?> root = new MetadataNode<Object>("root", (MetadataNode<?>)null, (MetadataNode<?>)null);
		// then, if you have children, we will connect you to your first child.
		// if you are not an element, well, then we don't know what to do with you. 
		// First, let us make some code to see what type of node is the root that is given to us		
		if (node.getNodeType() == Node.ELEMENT_NODE)
		{
			root.setElement(node.getNodeName());
			
			//System.out.print("I am an element. My tag is: " + root.getElement()); 
			
			if (node.hasChildNodes())
			{
				child = node.getFirstChild();
				root.setAnswer(child.getNodeValue());
				if (root.getAnswer().equals("\n"))
				{
					root.setAnswer("");
				}							
			}
			// send node to method that looks for an associated comment to this node (usually the next sibling or child node)
			retrieveNodeDescription(node, root);			
		}
		// Hello Element Node. Let's build your family tree.
		
		// now that we have you named, do you have siblings?
		if (node.getNextSibling() != null)
		{
			sibling = node.getNextSibling();
			// we need to find the next Element sibling
			while (!(sibling == null) && (sibling.getNodeType() != Node.ELEMENT_NODE))
			{
				sibling = sibling.getNextSibling();
			}
			// then we use that to add a sibling for you.
			if (sibling != null)
			{			
				root.addSibling(importDOMToMetadata(sibling));
				// root.getSibling().setParent(root.getParent());
			} 
			else
				root.addSibling(null);
		}
		
		if (node.hasChildNodes())
		{
			child = node.getFirstChild();
			// first we need to find the next element in your children. 
			while ((child != null) && (child.getNodeType() != Node.ELEMENT_NODE))
			{
				if (child.getFirstChild() != null)
					{
						child = child.getFirstChild();
					}
				else
				{
					child = child.getNextSibling();
				}		
			}
			// then, we use that to add a child for you
			if (child != null)
			{
				root.addChild(importDOMToMetadata(child));				
				root.getChild().setParent(root);
				connectParent(root.getChild());
			}
			else
				root.addChild(null);			
			/*if (root.getElement().equals("metadata"))
			{
				root.getChild().setParent(root);
				connectParent(root.getChild());
			}*/
		} // end importDOMToMetadataNode		
		
		return root;		
	}
	/**
	 * The addDOMToTree method takes in a Document Object Model (DOM) node
	 * and the root to a MetadataNode tree and adds only those nodes from the
	 * DOM that are necessary copies or are non-duplicates of elements already
	 * in the MetadataNode tree.
	 * 
	 * @assumption Add DOM Tree assumes the incoming DOM tree and the
	 *             MetadataNode tree are geospatial metadata files that follow
	 *             the same element order.
	 * @param dom
	 *            is a root to a Document object tree
	 * @param root
	 *            is the root to a MetadataNode tree
	 * @return the root of the MetadataNode tree.
	 */
	public MetadataNode<?> addDOMToTree(Node node, MetadataNode<?> root) {
		// PSEUDOCODE 
		// LOOP
		// COMPARE Does MetdataNode have element equal to Node Element tag
		// IF YES
		// CONDITIONS
		// FOR
		// conditions to be met for adding: 
			// node name does not already exist in MetadataNode tree
			// parent same in both node and MetadataNode
			// sibling same in both node and MetadataNode 
			// or end of sibling list, add new sibling
		boolean exists = false;
		boolean found = false;
		Node sibling, parent = node;
		MetadataNode<?> mSibling, mParent, mThis = root;
		// BASE CASE - matching parents
			// search for matching sibling
				// if parents sibling matches, 
					// do the tags match?
						// if tags match, exists = true
						// if tags do not match, add
				// else parents match but matching sibling not found
		if (node.getNodeName().equals(mThis.getElement()))
		{
			exists = true;
		}
		// RECURSIVE CASE - parents do not match, search next child, then next sibling
		else
		{
			 //
		}
		return root;
	}

	/**
	 * The saveMetadataToDOM method takes a Metadata Node and a Document file, 
	 * searches the Document for a matching tag and updates its comment. Currently
	 * the method does account for multiple instances of a tag.
	 * 
	 * @precondition the Document files are NOT the templates originally used at
	 *               the beginning of the session but are the working object 
	 *               representations of the Documents that are updated during a
	 *               session.
	 * @param metaNode
	 *            is the root of the MetadataNode tree
	 * @param domNode
	 *            is a Document object that is being updated
	 * @postcondition the Document object is updated with changes from the
	 *                MetadataNode tree and is in a state that can readily be
	 *                exported to a valid XML metadata file
	 */
	public void saveMetadataToDOM(MetadataNode<?> metaNode, Document dom) {
		// metaNode is not, necessarily, the root of the MetadataNode tree
		// dom is the Document object
		// find the element match, then update the Node text
		// recursive case: Metadata Node has child or has sibling:
		// call this method again with next MetadataNode and next Node.
		// base case: reach Metadata Node with no child and no sibling 		
		boolean ancestryMatch = false;
		int index = 0;
		NodeList nList;
		Node node;
		// find the DOM element that matches the metadata node element		
		nList = dom.getElementsByTagName(metaNode.getElement());
		node = nList.item(0);
		// check that the branch path to the root is the same in the 
		// Document as it is in the MetadataNode
		// call private method ancestorCompare(MetadataNode, Node)
		while (!ancestryMatch && index < nList.getLength()) 		// !!! POTENTIAL FOR INFINITE LOOP !!! //
		{
			node = nList.item(index);
			ancestryMatch = ancestorCompare(metaNode, node);
			index++;
		} // end loop find correct branch path of the Document
		
		// if the list is not empty, proceed. else, go to next metaNode item
		if ((nList != null) && ancestryMatch && !(node.hasChildNodes()))
		{
			node.setTextContent(metaNode.getAnswer());
		}
		
		// find the next text node which will be the node that corresponds to the tag.
		
		// update the text node value with the metadata node answer
		// BASE CASE
		if ((metaNode.getChild() == null) && (metaNode.getSibling() == null))
		{
			return;
		} // end base case
		// RECURSIVE CASE
		else 
		{
			// call this method with child first
			if (metaNode.getChild() != null)
			{
				saveMetadataToDOM(metaNode.getChild(), dom);
			}
			// call this method with sibling second
			if (metaNode.getSibling() != null)
			{
				saveMetadataToDOM(metaNode.getSibling(), dom);
			}				
		} // end recursive case
		
		return;
	}

	/**
	 * The exportXMLFiles method takes in an array of Documents and an array of
	 * names of the files to be exported and the file path to export those
	 * files.
	 * 
	 * @precondition the Documents and the names of files should be in matching
	 *               order
	 * @param domList
	 *            is a list of Document objects uses as the DOMSource
	 * @param nameList
	 *            is a list of names for the files of each Document
	 * @param savePath
	 *            is the folder in which the files are to be saved
	 * @return
	 * @throws IOException 
	 */
	public String [] exportXMLFiles(Document[] domList, String[] nameList, String projectNumber) throws IOException {
		// Create TransformerFactory
		String[] outputList = new String[nameList.length];
		String path = "";
		String fileName = "";
		String extension = "";
		char token = '\\';
		char dot = '.';
		int tokenIndex = 0;
		int dotIndex = 0;
		// Add projectNumber to front of tile name, remove "template" from file name
		for (int i = 0; i < nameList.length; i++)
		{
			// find index of last token and file extension
			tokenIndex = nameList[i].lastIndexOf(token) + 1;
			dotIndex = nameList[i].lastIndexOf(dot);
			// assign string before token to path
			path = nameList[i].substring(0, tokenIndex);
			// assign string after token to fileName
			fileName = nameList[i].substring(tokenIndex,  dotIndex);
			// assign file extension to extension
			extension = nameList[i].substring(dotIndex, nameList[i].length());
			fileName = (projectNumber + "_" + fileName);
			tokenIndex = fileName.indexOf("_template");
			fileName = fileName.substring(0, tokenIndex);
			outputList[i] = path + fileName + extension;
		}
		
		TransformerFactory transFactory = TransformerFactory.newInstance();
		try {
			// Create Transformer
			Transformer trans = transFactory.newTransformer();
			// Transform each Document to a Result
			for (int index = 0; index < domList.length; index++) {
				DOMSource source = new DOMSource(domList[index]);
				// This is where the magic happens
				StreamResult result = new StreamResult(new File(outputList[index])); 
				trans.transform(source, result);				
			}
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return outputList;
	}

	/**
	 * The domTreeToString converts a Document Object Model (DOM) to a single
	 * String object for displaying the file contents in an easily readable
	 * format. Only leaf nodes are displayed with xml tags being replaced by
	 * properly descriptive titles for each element. Contents are displayed
	 * beneath and indented to show relationship to the title.
	 * 
	 * @precondition the incoming node is the desired root of a DOM tree.
	 *               Contrary to the name of the method, you cannot send this
	 *               method a Document object or it will throw an exception and
	 *               most likely crash and burn.
	 * @param root
	 *            is the node given as the root of a MetadataNode tree.
	 * @postcondition a plain text display of the contents of the tree using
	 *                indentation markers to show hierarchy and content
	 *                ownership. Formatting of text includes only indentation,
	 *                not font characteristics such as bold, italics, size, etc.
	 * @return the metadataTreeString String.
	 * 
	 */
	public String domTreeToString(Node node) {
		String domTreeString = "";
		// Print out *s for indentation place holders based on loop variable
		// numElementNode
		for (int i = 0; i < numElementNode; i++) {
			// System.out.print("*");
			domTreeString += "*";
		}
	
		// print the node. This is where we do any operations to the current
		// parameter node
		
		// System.out.print(node.getNodeName() + ((numElementNode == 0) ? "\n" : " "));
		domTreeString += node.getNodeName() + ((numElementNode == 0) ? "\n" : " ");
	
		// iterate through the nodeList
		NodeList nodeList = node.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node currentNode = nodeList.item(i);
	
			// if the node is a comment node, print out the value
			if (currentNode.getNodeType() == Node.COMMENT_NODE) {
				// System.out.println(currentNode.getNodeValue());
				domTreeString += currentNode.getNodeValue() + "\n";
			}
			
			if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
				// calls this method for all the children which is Element
				numElementNode++;
				domTreeString += domTreeToString(currentNode);
				numElementNode--;
			} // end if
		} // end for
		return domTreeString;
	}

	/**
	 * The metadataTreeToString converts a MetadataNode tree to a specifically
	 * formatted single String object that is ideal for either displaying the
	 * tree or saving the tree in a Metadata Tool Session file.
	 * 
	 * @precondition the incoming node is the desired root of the tree
	 * @param root
	 *            is the node given as the root of a MetadataNode tree
	 * @postcondition a display of the contents of the tree using indentation
	 *                markers to show the hierarchy
	 * @return the metadataTreeString String.
	 */
	public String metadataTreeToString(MetadataNode<?> root) {
		String metadataTreeString = "";
	
		// Print out *s for indentation place holders based on loop variable
		// numElementNode
		for (int i = 0; i < numElementNode; i++) {
			// System.out.print("*");
			metadataTreeString += "*";
		}
	
		// print the node. This is where we do any operations to the current
		// parameter node
		
		// System.out.print(root.getElement() + ((numElementNode == 0) ? "\r" : " "));
		metadataTreeString += root.getElement() + ((numElementNode == 0) ? "\r" : " ");
		
	
		// iterate through the tree, child first, then sibling
		// add this node's tag, name, question, answer and verified state to string
		if (numElementNode != 0)
		{
			// System.out.println(" ElementName: " + root.getElementName() + " Question: " + root.getQuestion() + " Answer: " + root.getAnswer() + " Verified: " + ((root.getVerified()? "Y" : "N")) + "\r");			
			metadataTreeString += (" ElementName: " + root.getElementName() + " Question: " + root.getQuestion() + " Answer: " 
			+ ((root.getAnswer().equals("\n")) ? "" : root.getAnswer()) + " Verified: " 
					+ ((root.getVerified()? "Y" : "N")) + "\r");			
		}		
		
			// if the node has a child, call child
			if (root.getChild() != null)
			{
				numElementNode++;
				metadataTreeString += metadataTreeToString(root.getChild());
				numElementNode--;
			} // end if has a child
			// if the node has a sibling, call sibling
			if (root.getSibling() != null)
			{
				metadataTreeString += metadataTreeToString(root.getSibling());
			} // end if has a sibling		 
	
		return metadataTreeString;
	}

	/**
	 * The printDOM method walks a DOM tree extracting each element and the
	 * comments associated with that element.
	 * 
	 * @precondition the method assumes that the incoming node is the root of a
	 *               DOM tree that was created from an XML or HTML file.
	 * @param node
	 *            is the first element of a DOM tree.
	 * @postcondition the console will display an indented representation of a
	 *                DOM object
	 */
	public void printDOM(Node node) {
	
		// calls the domTreeToString() method and displays in console
		System.out.println(domTreeToString(node));
	
	}// end treePrint

	/**
	 * printMetadataTree method walks a MetadataNode tree printing each leaf of
	 * the tree in hierarchical order. Printed leaf contents include all
	 * attributes of the leaf: Element, Element Name, Question, Answer and
	 * Verified.
	 * 
	 * @precondition the method assumes that the incoming node is the root of a
	 *               MetadataNode tree that is associated with a Metadata Tool
	 *               session.
	 * @param mNode
	 *            is the root node of a MetadataNode tree.
	 * @postcondition the console will display an indented representation of a
	 *                DOM object
	 */
	public void printMetadataTree(MetadataNode<?> mNode) {
	
		// calls the metadataTreeToString() method and displays in console
		System.out.println(metadataTreeToString(mNode));
	
	}// end printMetadataTree method

	/**
	 * The whoseYourDaddy method is a band-aid fix for setting the parent to a MetadataNode.
	 * @param root
	 */
	private void connectParent(MetadataNode <?> root) 
	{
		MetadataNode <?> adopted;
		
		if (root.getSibling() != null)
		{
			adopted = root.getSibling();		
			while (adopted != null && root.getParent() != null)
			{					
				adopted.setParent(root.getParent());
				adopted = adopted.getSibling();
			}
		}		
		return;
	}

	/**
	 * The retrieveNodeDescription method receives a node and a MetadataNode and 
	 * applies the description of the node Element as the ElementName and Question 
	 * for the MetadataNode.
	 * @param node the ELEMENT_NODE used to find a description
	 * @param root the MetadataNode to store the ElementName and Question
	 * @return and integer value, index, that represents the number of nodes skipped to get to the next ELEMENT_NODE
	 */
private void retrieveNodeDescription(Node node, MetadataNode <?> root) 
	{
		// Hello node. We see you have a name. Let's see if you have any comments associated with you.
		// to do this, we cycle through the next few nodes associated with you until we find:
			// 1. the next element node.
			// 2. the end of the list.
		// if we find an description, we assign it to you.
		// if we find an element, we send back to our boss the number of nodes we encountered between you and the next element
		// first, we find out if you have any siblings. If not, then we look for children.			
		int dashIndex, dotIndex, colonIndex, delimiter;
		dashIndex = dotIndex = colonIndex = delimiter = 0;
		NodeList nList = node.getChildNodes();		
		Node child = nList.item(0);
		Node sibling = node;	
		int found = 0;
		
		if (node.getFirstChild() != null) // the description is a child
		{			
			for (int c = 0; (child.getNodeType() != Node.ELEMENT_NODE) && (c < nList.getLength()); c++ )
			{				
				child = nList.item(c);
				// if a comment node is not encountered, then this method ends.
				if (child.getNodeType() == Node.COMMENT_NODE)
				{					
					dashIndex = child.getNodeValue().indexOf('-');
					dotIndex = child.getNodeValue().indexOf('.');
					colonIndex = child.getNodeValue().indexOf(':');
					if ( dashIndex > 0 )
					{
						delimiter = dashIndex;
					}
					else if ( dotIndex > 0 )
					{								
						delimiter = dotIndex;
					}	
					else if ( colonIndex > 0 )
					{						
						delimiter = colonIndex;
					}
					if ( delimiter > 0 )
					{
						root.setElementName(child.getNodeValue().substring(0, delimiter));
						root.setQuestion(child.getNodeValue().substring((delimiter + 1), child.getNodeValue().length()));
						found = 1;
					}
					else 
					{
						root.setQuestion(child.getNodeValue().substring(delimiter, child.getNodeValue().length()));
						found = 0;
					}
					
				} // end if child is a comment		 			
			} // end for looping through siblings in a list.
		}
		if (found == 0) // the description is a sibling
		{			
			sibling = sibling.getNextSibling();
			while ((sibling != null) && (sibling.getNodeType() != Node.ELEMENT_NODE))
			{							
				// if a comment node is not encountered, then this method ends.
				if (sibling.getNodeType() == Node.COMMENT_NODE)
				{					
					dashIndex = sibling.getNodeValue().indexOf('-');
					dotIndex = sibling.getNodeValue().indexOf('.');
					colonIndex = sibling.getNodeValue().indexOf(':');
					if ( dashIndex > 0 )
					{
						delimiter = dashIndex;
					}
					else if ( dotIndex > 0 )
					{							
						delimiter = dotIndex;
					}
					else if ( dotIndex > 0 )
					{
						delimiter = colonIndex;
					}
					if ( delimiter > 0 )
					{
						root.setElementName(sibling.getNodeValue().substring(0, delimiter));
						root.setQuestion(sibling.getNodeValue().substring((delimiter + 1), sibling.getNodeValue().length()));
					}
					else
					{
						root.setQuestion(sibling.getNodeValue().substring(delimiter, sibling.getNodeValue().length()));
					}
					
				} // end if sibling is a comment					
				sibling = sibling.getNextSibling();	
			} // end while sibling is not an element nor null
		} // end else incoming node is a leaf
		
		return;
	}


	/**
	 * The ancestorCompare method is a helper method to be sure that the node about to be updated 
	 * is in the correct branch of the DOM tree. If it is not, then the method returns false and
	 * the calling method must go to the next tag in the DOM with the same tag name and ask this
	 * method to check the branch path to the root again.
	 * @param metaNode the MetadataNode whose branch path to the root is being compared to
	 * @param node the Node whose branch path to the root is being evaluated
	 * @return true for a matching branch path and false for a branch path mismatch
	 */
	private boolean ancestorCompare(MetadataNode<?> metaNode, Node node) {
		// is the chain of parents the same in both sets of trees
		String metaNodeAncestry = "";
		String nodeAncestry = "";
		boolean ancestryMatch = false;
		metaNodeAncestry = metaNode.getElement();
		nodeAncestry = node.getNodeName(); // NULL POINTER EXCEPPTION - REQUIRES DEBUG //
		
		// If the incoming metaNode and node are both the root, return true
		/**
		if (metaNodeAncestry.equals("metadata"))
		{
			ancestryMatch = true;
		}
		
		// else, walk up ancestry tree to be sure we have the right Document leaf
		else
		{
		*/	while (!(metaNodeAncestry.contains("metadata")))
			{				
				if ((metaNode.getParent() != null))
					metaNode = metaNode.getParent();
				if ((node.getParentNode() != null))
					node = node.getParentNode();
				metaNodeAncestry = (metaNode.getElement() + "->" + metaNodeAncestry);
				nodeAncestry = (node.getNodeName() + "->" + nodeAncestry);
			}	
			if (metaNodeAncestry.equals(nodeAncestry))
				ancestryMatch = true;			
			else 
				ancestryMatch = false;
		//}
		// return the ancestry match result	
		return ancestryMatch;
	}
}

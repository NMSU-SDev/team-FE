
/**
 * The XmlSessionManager class is a collection of Metadata Tool operating methods
 * @author SJohnston
 * @version 2.0.0
 * @date April 10, 2018 
 */

import java.io.*;

import java.lang.Object;

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
	 * saveSession takes in a MetadataNode tree at its root along with the
	 * currentNode pointer and the list of xml files used as templates for this
	 * session and creates a very long String object that represents the state
	 * of this session at the moment it is saved. When opened in a text editor,
	 * the .xsm file should show each Node of the tree, starting with its root,
	 * cascading down the family genealogy, indenting each generation of the
	 * family to the right unto the last leaf. Each leaf will have displayed the
	 * state of its attributes at the time of save: Element, ElementName,
	 * Question, Answer and Verified. After the MetadataNode tree, the String
	 * should state the element name of the CurrentNode pointer. Last, the
	 * String should list the template files that this session is based on.
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
		StringBuilder sb = new StringBuilder();
		String xmlSessionSave;
		
		sb.append("!MetadataNodeTree!\n");
		// File sessionFile;
	
		sb.append( metadataTreeToString(root) );
		sb.append("!EndTree!\n!Current Node!\n").append( currentNode.getElement() ).append( "\n!EndCurrentNode!\n!TemplateList!\n");
		
		if ( templates == null ) {
			sb.append("!EndTemplatesList!\n");
			return sb.toString();
		}
		
		for (int index = 0; index < templates.length; index++) {
			sb.append( templates[index] ).append("\n");
		}
		sb.append("!EndTemplatesList!\n");
		
		xmlSessionSave = sb.toString();
	
		return xmlSessionSave;
	}

	/**
	 * The openSession method takes in a Metadata Tool proprietary .xsm file and
	 * creates a populated MetadataNode tree with the state of all attributes
	 * for all children and siblings.
	 * 
	 * 
	 * @precondition The incoming file is of type .xsm and not any other type.
	 * @param file
	 *            the incoming .xsm file.
	 * @postcondition a tree has been created with the parent/child/sibling
	 *                connections. and attributes that are identical to when the
	 *                session was saved.
	 * @return the root node to a MetadataNode tree.
	 * @throws TypeMismatchException.
	 * @catch returns a new, empty node to the calling method.
	 */
	public MetadataNode<?> openSession(File file, MetadataNode<?> currentNode, String[] templates) {
		MetadataNode<?> rootMNode = new MetadataNode<Object>("not implemented", null, null);
		/*
		 * Set parentNode to rootMNode. Set parentIndentation = 0; Set
		 * nextIndentation = parentIndentation; Get first String line
		 * parentIndentation = count indentation markers in String line To
		 * populate the attributes of the any Node, read String line, each line
		 * represents a node. Parse text in each line into the 5 attributes of a
		 * node: element, elementName, Question, Answer, Verified populate
		 * parentNode Begin loop: get String nextLine while not encountering
		 * special break character '\r' (known as a carriage return), create new
		 * node nextIndentation = count indentation markers for new node parse
		 * text to node attributes If next line has more indentation markers
		 * '*', add newNode to parentNode; parentNode.addChild(newNode) else if
		 * next line has equal indentation markers '*',, add newNode as sibling
		 * to parentNode else if next line has less indentation markers '*'
		 * point parentNode to getParent() for each 'less' indentation add
		 * newNode as sibling to parentNode get String nextLine end loop
		 * 
		 * After the tree is created we want to set currentNode to the element
		 * in rootMNode that = the element listed in the .xsm file
		 * 
		 * Last, we want to load into the file array the list of template files
		 * by grabbing them from their absolute paths. !! CAUTION !! may throw a
		 * FileNotFound exception if a user moved those files.		 * 
		 */
		return rootMNode;
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
			/**System.out.println(root.getElement());
			System.out.println(node.getTextContent());
			System.out.println((node.hasAttributes())? node.getAttributes(): "No attributes");
			System.out.println(!(node.getFirstChild() == null) ? "Has child" : "Is leaf" );
			if ((node.getFirstChild() == null))
				root.setAnswer(node.getTextContent());
			*/
			//System.out.print("I am an element. My tag is: " + root.getElement()); 
			
			if (node.hasChildNodes())
			{
				child = node.getFirstChild();
				root.setAnswer(child.getNodeValue());
				/*if (root.getAnswer().contains("\n"))
				{
					int escapeSequence = root.getAnswer().indexOf("\n");
					if (escapeSequence > 0)
					{
						root.setAnswer(root.getAnswer().substring(0, root.getAnswer().indexOf("\n")));
					}
					else
						root.setAnswer("");
				}
				*/			
			}
			retrieveNodeDescription(node, root);
			//System.out.println(" My name is: " + root.getElementName() + " My description is: " + root.getQuestion());
		}
		
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
			
		} // end importDOMToMetadataNode
		// mini report
		//System.out.print("Tag: " + root.getElement() + " Name: " + root.getElementName());		
		//System.out.print(" Question: " + root.getQuestion() + " Answer: " + root.getAnswer());
		//System.out.println(" Verified: " + ((root.getVerified() ? "True" : "False")));
		
		// Hello Element Node. Let's build your family tree.
		// send node to method that looks for an associated comment to this node (usually the next sibling or child node)		
		return root;		
	}
	/**
	 * The addDOMToTree method takes in a Document Object Model (DOM) root node
	 * and the root to a MetadataNode tree and adds only those nodes from the
	 * DOM that are necessary copies or are non-duplicates of elements already
	 * in the MetadataNode tree. Implementation planned for Demo 3
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
	public MetadataNode<?> addDOMToTree(Node dom, MetadataNode<?> root) {
		// PSEUDOCODE
		// POINTER MetadataNode currentMetaNode
		// LOOP
		// COMPARE Does DOM have element equal to currentMetaNode
		// IF YES
		// CONDITIONS
		// FOR
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
		else // RECURSIVE CASE
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
		
		if ( root == null ) return metadataTreeString;
	
		// Print out *s for indentation place holders based on loop variable
		// numElementNode
		for (int i = 0; i < numElementNode; i++) {
			// System.out.print("*");
			metadataTreeString += "*";
		}
	
		// print the node. This is where we do any operations to the current
		// parameter node
		
		// System.out.print(root.getElement() + ((numElementNode == 0) ? "\n" : " "));
		metadataTreeString += root.getElement() + ((numElementNode == 0) ? "\n" : " ");
		
	
		// iterate through the tree, child first, then sibling
		// add this node's tag, name, question, answer and verified state to string
		if (numElementNode != 0)
		{
			// System.out.println(" ElementName: " + root.getElementName() + " Question: " + root.getQuestion() + " Answer: " + root.getAnswer() + " Verified: " + ((root.getVerified()? "Y" : "N")));			
			metadataTreeString += (" ElementName: " + root.getElementName() + " Question: " + root.getQuestion() + " Answer: " + root.getAnswer() + " Verified: " + ((root.getVerified()? "Y" : "N")) + "\n");			
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
		MetadataNode <?> adopted = root;
		if (root.getSibling() != null)
		{
			adopted = root.getSibling();		
			while (adopted != null)
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

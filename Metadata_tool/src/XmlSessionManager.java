
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
	 * Import DOM Tree method takes in a Document Object Model tree at its root
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
		MetadataNode<?> root = new MetadataNode<Object>(null, null, null);
		int dashIndex = 0;

		// Print out *s for indentation place holders based on loop variable
		// numElementNode
		for (int i = 0; i < numElementNode; i++) {
			System.out.print("*");
		}

		// Get the node. This is where we do any operations to the current
		// parameter node
		root.setElement(node.getNodeName()); // root is now the parent
		System.out.print(root.getElement() + " ");

		// iterate through the nodeList
		NodeList nodeList = node.getChildNodes();
		System.out.print(" Node List Length : " + nodeList.getLength());
		int index = 0;

		while (index < nodeList.getLength()) {
			Node childNode = nodeList.item(index);
			if (childNode.getNodeType() == Node.COMMENT_NODE) {
				// the following assumes that the comment node belongs to the
				// parent
				dashIndex = childNode.getNodeValue().indexOf('-');
				if (dashIndex < 0)
				{
					dashIndex = 0;
				}
				
				root.setElementName(childNode.getNodeValue().substring(0, dashIndex));
				root.setQuestion(
						childNode.getNodeValue().substring((dashIndex + 1), childNode.getNodeValue().length()));
				// root.setElement(node.getNodeName());
				// System.out.print(root.getElement() + " ");
				System.out.print(root.getElementName() + " - " + root.getQuestion());
			} else if (childNode.getNodeType() == Node.ELEMENT_NODE && childNode.hasChildNodes()) {
				
				System.out.println();
				if (root.getChild() != null) {
					// add a sibling to root, assume another branch (recursive call)					
					root.addSibling(importDOMToMetadata(childNode));					
				} else {
					// add a child to root, then go down to next child (recursive call)
					numElementNode++;
					root.addChild(importDOMToMetadata(childNode)); 																					
				}
			} else if (childNode.getNodeType() == Node.ELEMENT_NODE && !(childNode.hasChildNodes())) {
				System.out.println();
				MetadataNode currentNode = new MetadataNode<Object>(childNode.getNodeName(), null, null);
				numElementNode++;
				root.addChild(currentNode);
				root = currentNode;	
				root.addSibling(importDOMToMetadata(childNode));
				numElementNode--;				
				/* assume the rest are leaves
				index++;
				while (index < nodeList.getLength()) {
					
					if (siblingNode.getNodeType() == Node.COMMENT_NODE) {
						// the following assumes that the comment node belongs
						// to the parent
						dashIndex = siblingNode.getNodeValue().indexOf('-');
						currentNode.setElementName(siblingNode.getNodeValue().substring(0, dashIndex));
						currentNode.setQuestion(siblingNode.getNodeValue().substring((dashIndex + 1),
								siblingNode.getNodeValue().length()));
						// root.setElement(node.getNodeName());
						System.out.print(currentNode.getElementName() + " - " + currentNode.getQuestion());
					} // end if sibling is a Comment
					if (siblingNode.getNodeType() == Node.ELEMENT_NODE) {
						System.out.println();
						MetadataNode newSibling = new MetadataNode<Object>(siblingNode.getNodeName(), null, null);
						// add a sibling to root, then go down to next child (recursive call
						currentNode.addSibling(newSibling); 
						currentNode = newSibling;
					} // end if sibling is an Element
					if (siblingNode.hasChildNodes())
					{
						break;
					}
					index++;
				} // end while there are more leaves
				*/
			} else {
				// skip the junk
			}
			index++;
		} // end while node has children
		/*
		 * for (int i = 0; i < nodeList.getLength(); i++) { Node childNode =
		 * nodeList.item(i); // current node is a child of root // if the node
		 * is a comment node, print out the value if (childNode.getNodeType() ==
		 * Node.COMMENT_NODE) { if (root.getChild() != null) { MetadataNode
		 * nextChildNode = root.getChild(); do { if (nextChildNode.getElement()
		 * == childNode.getNodeName()) { // the following assumes that the
		 * comment node belongs to one of the children dashIndex =
		 * childNode.getNodeValue().indexOf('-');
		 * nextChildNode.setElementName(childNode.getNodeValue().substring(0,
		 * dashIndex));
		 * nextChildNode.setQuestion(childNode.getNodeValue().substring((
		 * dashIndex+1), childNode.getNodeValue().length()));
		 * //childNode.setElement(node.getNodeName());
		 * System.out.print(nextChildNode.getElementName() + " - " +
		 * nextChildNode.getQuestion()); } if (nextChildNode.getSibling() !=
		 * null) nextChildNode = nextChildNode.getSibling(); } while
		 * (nextChildNode.getSibling() != null); } else { // the following
		 * assumes that the comment node belongs to the parent dashIndex =
		 * childNode.getNodeValue().indexOf('-');
		 * root.setElementName(childNode.getNodeValue().substring(0,dashIndex));
		 * root.setQuestion(childNode.getNodeValue().substring((dashIndex+1),
		 * childNode.getNodeValue().length())); //
		 * root.setElement(node.getNodeName()); //
		 * System.out.print(root.getElement() + " ");
		 * System.out.print(root.getElementName() + " - " + root.getQuestion());
		 * // else loop - find root.getChild().getElement() ==
		 * currentNode.getNodeName() } }
		 * 
		 * if (childNode.getNodeType() == Node.ELEMENT_NODE) { if
		 * (childNode.hasChildNodes()) { // calls this method for all the
		 * children which is Element numElementNode++; System.out.println();
		 * root.addChild(importDOMToMetadata(childNode)); // add a child to
		 * mNode and go down to next child (recursive call) numElementNode--; //
		 * go up to parent } else // then it is an element node that has a
		 * sibling for a comment { // loop to get past trash Node parent =
		 * childNode.getParentNode(); NodeList parentList =
		 * parent.getChildNodes(); int p = i+1; while (p <
		 * parentList.getLength()) { Node siblingNode = parentList.item(p); if
		 * (siblingNode.getNodeType() == Node.COMMENT_NODE) { // the following
		 * assumes that the comment node belongs to one of the children
		 * dashIndex = siblingNode.getNodeValue().indexOf('-');
		 * root.setElementName(siblingNode.getNodeValue().substring(0,dashIndex)
		 * );
		 * root.setQuestion(siblingNode.getNodeValue().substring((dashIndex+1),
		 * siblingNode.getNodeValue().length()));
		 * //childNode.setElement(node.getNodeName());
		 * System.out.print(root.getElementName() + " - " + root.getQuestion());
		 * }
		 * 
		 * if (siblingNode.getNodeType() != Node.ELEMENT_NODE) {
		 * System.out.println();
		 * root.addSibling(importDOMToMetadata(siblingNode)); } p ++; } }
		 * 
		 * } // end if
		 * 
		 * } // end for
		 */
		numElementNode--;
		return root;
	}

	/**
	 * The saveMetadataToDOM method takes a MetadataNode at its root and an
	 * array of all of the DOM files that are being updated for the session. It
	 * copies the DOM files, each to a new name, then updates appropriate
	 * element contents.
	 * 
	 * @precondition the Document files are NOT the templates originally used at
	 *               the beginning of the session but are copies of the
	 *               templates.
	 * @param mNode
	 *            is the root of the MetadataNode tree
	 * @param udpateFileList
	 *            is an array of the Document objects that are being updated as
	 *            the session progresses.
	 * @postcondition Document files are updated with changes from the
	 *                MetadataNode tree and are in a state that can readily be
	 *                exported to valid XML metadata files
	 */
	public void saveMetadataToDOM(MetadataNode<?> mNode, Document document) {
		// Loop find matching elements that should contain comments (leaves in
		// both trees)
		// ensure matched element is really for the current data type and not
		// for a different data type
		// update Document COMMENT for that ELEMENT
		// get next Document object
		MetadataNode<?> currentNode = mNode;
		NodeList currentDoc = document.getElementsByTagName("metadta");
		Node node = currentDoc.item(0);
		while (!currentNode.equals(mNode.getLastChild())) {
			currentDoc = document.getElementsByTagName(currentNode.getElement());
			for (int index = 0; index < currentDoc.getLength(); index++) {
				node = currentDoc.item(index);
				node.setNodeValue(currentNode.getAnswer());
			}
			if (!(currentNode.getChild() == null)) // has child
			{
				currentNode = currentNode.getChild();
			} else if (!(currentNode.getSibling() == null)) // not a child but
															// has a sibling
			{
				currentNode = currentNode.getSibling();
			} else // last child
			{
				do // loop: go up one
				{
					currentNode.getParent();
				} while (currentNode.getSibling() == null); // find next
															// sibling. if no
															// sibling, go up
															// one again
				currentNode.getSibling(); // get next sibling
			}
		}
		return;
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
	 * @throws FileNotFoundException
	 */
	public void exportXMLFiles(Document[] domList, String[] nameList, String savePath) throws FileNotFoundException {
		// Create TransformerFactory
		TransformerFactory transFactory = TransformerFactory.newInstance();
		try {
			// Create Transformer
			Transformer trans = transFactory.newTransformer();
			// Transform each Document to a Result
			for (int index = 0; index < domList.length; index++) {
				DOMSource source = new DOMSource(domList[index]);
				// !!! need this to output to an array of file objects
				StreamResult result = new StreamResult(System.out); 
				trans.transform(source, result);
				// Convert Result to a file, stored in an array
				String newPath = (savePath + "\\" + nameList[index] + ".xml");
				try {
					PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(newPath)));
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return;
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
		MetadataNode<?> rootMNode = new MetadataNode<Object>(null, null, null);
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
		 * FileNotFound exception if a user moved those files.
		 * 
		 */
		return rootMNode;
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
		String xMlSessionSave = "";
		// File sessionFile;

		xMlSessionSave += metadataTreeToString(root);
		xMlSessionSave += "Current Node: " + currentNode.getElement() + "\n";
		for (int index = 0; index < templates.length; index++) {
			xMlSessionSave += templates[index] + "\n";
		}

		return xMlSessionSave;
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
			System.out.print("*");
			metadataTreeString += "*";
		}

		// print the node. This is where we do any operations to the current
		// parameter node
		metadataTreeString += root.getElement() + " ";

		// iterate through the nodeList
		NodeList nodeList = (NodeList) root.getChild();
		for (int i = 0; i < nodeList.getLength(); i++) {
			MetadataNode<?> currentNode = (MetadataNode<?>) nodeList.item(i);

			// if the node is a leaf, save node attributes to a formatted string
			if (currentNode.isLeaf()) {
				metadataTreeString += " : " + currentNode.getElementName() + " : " + currentNode.getQuestion() + " : "
						+ currentNode.getAnswer() + " : " + ((currentNode.getVerified()) ? "V" : "U") + "\n";
			}

			if (!currentNode.isLeaf()) {
				// calls this method for all the children which is Element
				numElementNode++;
				metadataTreeString += metadataTreeToString(currentNode);
				numElementNode--;
			} // end if
		} // end for

		return metadataTreeString;
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
			System.out.print("*");
			domTreeString += "*";
		}

		// print the node. This is where we do any operations to the current
		// parameter node
		System.out.print(node.getNodeName() + " ");
		domTreeString += node.getNodeName() + " ";

		// iterate through the nodeList
		NodeList nodeList = node.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node currentNode = nodeList.item(i);

			// if the node is a comment node, print out the value
			if (currentNode.getNodeType() == Node.COMMENT_NODE) {
				System.out.println(currentNode.getNodeValue());
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
}

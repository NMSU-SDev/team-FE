
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
		return root;
	}
	
	/**
	 * The saveMetadataToDOM method takes a MetadataNode at its root and an array of 
	 * all of the DOM files that are being updated for the session. It copies
	 * the DOM files, each to a new name, then updates appropriate element contents.
	 * @precondition the Document files are NOT the templates originally used at the beginning
	 * of the session but are copies of the templates.   
	 * @param mNode is the root of the MetadataNode tree
	 * @param udpateFileList is an array of the Document objects that are being updated
	 * as the session progresses.
	 * @postcondition Document files are updated with changes from the MetadataNode tree
	 * and are in a state that can readily be exported to valid XML metadata files
	 */
	public void saveMetadataToDOM(MetadataNode<?> mNode, Document[] udpateFileList)
	{
		// Loop (Begin with first Document object in array)
			// Loop find matching elements that should contain comments (leaves in both trees)
			// ensure matched element is really for the current data type and not for a different data type
				// update Document COMMENT for that ELEMENT
			// get next Document object
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
	 * The exportXMLFiles method takes in an array of Documents and an array of names of the 
	 * files to be exported and the file path to export those files. 
	 * @precondition the Documents and the names of files should be in matching order
	 * @param domList is a list of Document objects uses as the DOMSource
	 * @param nameList is a list of names for the files of each Document
	 * @param savePath is the folder in which the files are to be saved
	 * @return 
	 * @throws FileNotFoundException 
	 */
	public void exportXMLFiles(Document [] domList, String [] nameList, String savePath) throws FileNotFoundException
	{		
		// Create TransformerFactory		
		TransformerFactory transFactory = TransformerFactory.newInstance();
		try {
			// Create Transformer
			Transformer trans = transFactory.newTransformer();
			// Transform each Document to a Result
			for (int index = 0; index < domList.length; index++)
			{
				DOMSource source = new DOMSource(domList[index]);
				StreamResult result = new StreamResult(System.out); //!!! need this to output to an array of file objects
				trans.transform(source, result);
				// Convert Result to a file, stored in an array
				String newPath = (savePath + "\\" + nameList[index] + ".xml");
				try 
				{ 
					PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(newPath)));
					out.close();
				}
				catch (IOException e)
				{
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
	 * openSession method takes in a Metadata Tool proprietary .xsm file and
	 * creates a populated MetadataNode tree with the state of all attributes
	 * for all children and siblings from the .xsm session file.
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
	public MetadataNode<?> openSession(File file) {
		MetadataNode<?> root = new MetadataNode<Object>(null, null, null);
		return root;
	}

	/**
	 * saveSession takes in a MetadataNode tree at its root along with the
	 * currentNode pointer and creates a very long String object that represents
	 * the state of a session at the moment it is saved. When opened in a text
	 * editor, the .xsm file should show each Node of the tree, starting with
	 * its root, cascading down the family genealogy, indenting each generation
	 * of the family to the right unto the last leaf. Each leaf will have
	 * displayed the state of its attributes at the time of save: Element,
	 * ElementName, Question, Answer and Verified.
	 * 
	 * @precondition the incoming node is the desired root of the tree.
	 * @param root
	 *            is the root of the MetadataNode tree from the Metadata Tool
	 *            session.
	 * @param currentNode
	 *            is a pointer to the node that the Metadata Tool was currently
	 *            working on when the session was saved.
	 * @param xMlSessionManager
	 *            is the formatted string that represents the state of the saved
	 *            Metadata Tool session.
	 * @postcondition a String of the contents of the tree using indentation
	 *                markers to represent the hierarchy
	 * @return the xmlSessionManager String.
	 */
	public String saveSession(MetadataNode<?> root, MetadataNode<?> currentNode) {
		String xMlSessionManager = "Current Node: " + currentNode.getElement() + "\n";
		xMlSessionManager += metadataTreeToString(root);
		return xMlSessionManager;
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
			// System.out.print("*");
			domTreeString += "*";
		}

		// print the node. This is where we do any operations to the current
		// parameter node
		// System.out.print(node.getNodeName() + " ");
		domTreeString += node.getNodeName() + " ";

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
}

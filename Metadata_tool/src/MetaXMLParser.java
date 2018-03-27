/**
 * 
 * @author SJohnston
 * @version 1.0.0
 * @date March 9, 2018 
 */

import java.io.*;

import java.lang.Object;

import javax.xml.parsers.*;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class MetaXMLParser {

	//Instance Variables
	private DocumentBuilderFactory dbFact;
	private DocumentBuilder dBuild;
	private Document metaDoc;
	private static int numElementNode = 0;
	private MetadataNode metaNode;

	/**
	 * Default MetaXMLParser constructor
	 */
	public MetaXMLParser()
	{
		dbFact = null; 
		dBuild = null;
		metaDoc = null;
		metaNode = null;
	}
	
	/**
	 * MetadataParse takes in a valid XML or HTML file and returns a Document Object Model tree
	 * @param file is an XML or HTML file
	 * @return Document is a DOM tree of the XML or HTML contents <ELEMENT>, <ATTRIBUTE>, <CONTENT>
	 * @throws Throws an exception if the incoming file is not an XML or HTML
	 */
	public Document metadataParse (File file)
	{
		Document doc = null;
		int numDocTags = 0;
		NodeList nList = null;
		Node nNode = null;
		doc = metaTreeDoc(file);				// send the metaTreeDoc method a file and it returns a DOM object.
		doc.normalize();
		nList = doc.getElementsByTagName("metadata");
						
		numDocTags = 0;
		nNode = nList.item(0);
		treePrint(nNode);
		//treeTraverse(nNode);
		System.out.println("End of tree");
		return doc;
	}
	
	/**
	 * XML tree document creator
	 * @precondition incoming file is an XML or HTML file	  
	 * @param file File object from calling method
	 * @return returns a Document tree object
	 * @throws Throws and exception if the incoming file is not an XML or HTML
	 */
	public Document metaTreeDoc (File file)
	{		
		try 
		{
			dbFact = DocumentBuilderFactory.newInstance(); 
			dBuild = dbFact.newDocumentBuilder();
			metaDoc = dBuild.parse(file);
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		return metaDoc; 		
	}
	
	//Lucas Hermann's super awesome node traversal
	/**
	 * TreeTraversal method walks a DOM tree extracting each element and the comments associated that element.
	 * This method should be modified/upgraded to return a node or an array that contains two parts: the element name and the text of the comment.
	 * @precondition the method assumes that the incoming node is the root of a DOM tree that was created from an XML or HTML file.
	 * @param node is the first element of a DOM tree. 
	 * @postcondition currently the method outputs the element and its associated comment text on the command line.
	 */
	public void treePrint(Node node) 
	{
		
	    //Print out *s for indentation place holders based on loop variable numElementNode
		for( int i = 0; i < numElementNode; i++)
			System.out.print("*");
	    
		//print the node. This is where we do any operations to the current parameter node 
		System.out.print(node.getNodeName() + " ");

		//iterate through the nodeList
	    NodeList nodeList = node.getChildNodes();
	    for (int i = 0; i < nodeList.getLength(); i++) 
	    {
	        Node currentNode = nodeList.item(i);
	        
	        //if the node is a comment node, print out the value
	        if (currentNode.getNodeType() == Node.COMMENT_NODE)
	        	System.out.println(currentNode.getNodeValue());
	        
	        if (currentNode.getNodeType() == Node.ELEMENT_NODE) 
	        {
	            //calls this method for all the children which is Element
	        	numElementNode++;
	        	treePrint(currentNode);
	        	numElementNode--;
	        } //end if
	    } //end for
	}//end Tree Traverse		
	
	/**
	 * Import DOM Tree method takes in a Document Object Model tree at its root and returns 
	 * the root of a tree of MetadataNode objects. A MetadataNode object maps a DOM Element 
	 * into the MetadataNode element, a DOM Content into the MetadataNode question and
	 * and sets the Metadata answer and verified variables to default. 
	 * @param dom the root of a Document Object Model tree
	 * @param root a MetadataNode object that is the root of the tree or subtree
	 * @return root, the root of the entire MetadataNode tree
	 */
	public MetadataNode importDOMTree(Document dom)
	{
		MetadataNode root = new MetadataNode(null, null, null);
		return root;
	}
	
	/**
	 * Add DOM Tree method takes in a Document Object Model tree and the root to a MetadataNode
	 * tree and adds only those nodes from the DOM that are necessary copies or are non-
	 * duplicates of elements already in the MetadataNode tree
	 * @assumption Add DOM Tree assumes the incoming DOM tree and the MetadataNode tree are 
	 * geospatial metadata files that follow the same element order. 
	 * @param dom is a root to a Document object tree
	 * @param root is the root to a MetadataNode tree
	 * @return the root of the MetadataNode tree.
	 */
	@SuppressWarnings("rawtypes")
	public MetadataNode addDOMTree(Document dom, MetadataNode root)
	{
		return root;
	}
	
	/**
	 * Open Session Tree method takes in a Metadata Tool proprietary file(s) and 
	 * creates a populated MetadataNode tree with the state of all elements, 
	 * questions, answers, verified flags, children and siblings from the selected session
	 * @param file
	 * @return
	 */
	public MetadataNode openSessionTree(File file)
	{
		MetadataNode root = new MetadataNode(null, null, null);
		return root;
	}
	
	/**
	 * Save Session Tree takes in a MetadataNode tree at its root and creates a very long 
	 * String object that, when opened in a text editor, shows each Node of the tree, 
	 * starting with its root, cascading down to the next sibling and to the right for the
	 * first child, including the values for element, question, answer and verified of each 
	 * Node. 
	 * @param root is the root of a MetadataNode tree
	 * @param sessionState
	 * @return sessionState : a String of the full MetadataNode tree
	 */
	public String saveSessionTree(MetadataNode root)
	{
		String sessionState = new String ("");
		return sessionState;
	}
}

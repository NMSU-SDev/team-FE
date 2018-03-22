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
	static int numElementNode = 0;
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
	
	public Document MetadataParse (File file)
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
		treeTraverse(nNode);
		//treeTraverse(nNode);
		System.out.println("End of tree");
		return doc;
	}
	
	/**
	 * XML tree document creator
	 * @precondition incoming file is an XML or HTML file	  
	 * @param file File object from calling method
	 * @return returns a Document tree object
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
	public void treeTraverse(Node node) {
		
	    //Print out *s for indentation place holders based on loop variable numElementNode
		for( int i = 0; i < numElementNode; i++)
			System.out.print("*");
	    
		//print the node. This is where we do any operations to the current parameter node 
		System.out.print(node.getNodeName() + " ");

		//iterate through the nodeList
	    NodeList nodeList = node.getChildNodes();
	    for (int i = 0; i < nodeList.getLength(); i++) {
	        Node currentNode = nodeList.item(i);
	        
	        //if the node is a comment node, print out the value
	        if (currentNode.getNodeType() == Node.COMMENT_NODE)
	        	System.out.println(currentNode.getNodeValue());
	        
	        if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
	            //calls this method for all the children which is Element
	        	numElementNode++;
	        	treeTraverse(currentNode);
	        	numElementNode--;
	        } //end if
	    } //end for
	}//end Tree Traverse	
	
	public void copyTemplateDom(Node domNode)
	{
		// copy name of incoming domNode as the Element name of a new MetadataNode
		MetadataNode metaNode = new MetadataNode(domNode.getNodeName(), null, null); // default constructor takes three parameters: the element name and pointers to a child and sibling nodes
		
		// point NodeList to incoming domNode
		NodeList domList = domNode.getChildNodes();
		
		// iterate through the domList
		for (int i = 0; i < domList.getLength(); i++)
		{
			Node currentNode = nodeList.item(i);
			
			//if the node is a comment node, then that becomes the question for the new element node
		}
	}
	
	public void copySessionTree(MetadataNode sessionNode)
	{
		
	}
	
}

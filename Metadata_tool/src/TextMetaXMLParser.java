/**
 * 
 * @author SJohnston & LHermann
 * @version 1.0.1
 * @date March 12, 2018
 * Command line test code for interfacing with the MetaXMLParser and learning how to navigate a DOM tree object.
 */

import java.io.File;
import java.util.Scanner;

import javax.xml.parsers.*;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

public class TextMetaXMLParser {

	static int numElementNode = 0;
	
	
	/**
	 * The purpose of this method is to text the MetaXML Parser class and to develop and test 
	 * the treeTraversal method so that it can be customized for incorporating its abilities
	 * into the Metadata tool GUI. 
	 * @param args
	 */
	public static void main(String[] args) {
		// Instance variables
		File file;
		String inputFile = "";
		Scanner scan = new Scanner(System.in);
		int numDocTags = 0;
		NodeList nList = null;
		Node nNode = null;
				
		Document doc = null;
		
		System.out.print("Enter file name and path: ");
		inputFile = scan.nextLine();
		file = new File(inputFile);
		
		MetaXMLParser newParser = new MetaXMLParser();
		doc = newParser.metaTreeDoc(file);				// send the metaTreeDoc method a file and it returns a DOM object.
		doc.normalize();
		nList = doc.getElementsByTagName("metadata");
						
		numDocTags = 0;
		nNode = nList.item(0);
		treeTraverse(nNode);
		//treeTraverse(nNode);
		System.out.println("End of tree");
				
	}
	
	//Lucas Hermann's super awesome node traversal
	/**
	 * TreeTraversal method walks a DOM tree extracting each element and the comments associated that element.
	 * This method should be modified/upgraded to return a node or an array that contains two parts: the element name and the text of the comment.
	 * @precondition the method assumes that the incoming node is the root of a DOM tree that was created from an XML or HTML file.
	 * @param node is the first element of a DOM tree. 
	 * @postcondition currently the method outputs the element and its associated comment text on the command line.
	 */
	public static void treeTraverse(Node node) {
	
	    //Print out *s for indentation place holders based on global variable numElementNode
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
	
}

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

public class TestMetaXMLParser {

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
		Node domNode = null;
		MetadataNode nNode = null;
		MetaXMLParser parse = new MetaXMLParser();
				
		Document doc1 = null;		 
		
		System.out.print("Enter file name and path: ");
		inputFile = scan.nextLine();
		file = new File(inputFile);
		
		// ** TEST XML FILE PARSER ** //
		doc1 = parse.fileToDOM(file);				//doc1 is a Document Object Model (DOM)
				
		// ** TEST PARSE DOM
		
				
		System.out.println("End of tree");
				
	}	
	
	
	public void printNode(MetadataNode mNode)
	{
		System.out.println("Element: " + mNode.getElement());
		System.out.println("Element Name: " + mNode.getElementName());
		System.out.println("Question: " + mNode.getQuestion());
		System.out.println("Answer: " + mNode.getAnswer());
		System.out.println("Verified: " + mNode.getVerified());
		System.out.println(!(mNode.getChild()==null) ? "Has child" : "Has no child");
		System.out.println(!(mNode.getSibling()==null) ? "Has sibling" : "Has no sibling");
		return;
	}
	
	/**
	 * TreePrint method walks a MetadataNode tree extracting each element and the comments associated that element.
	 * This method has been modified/upgraded to call the node methods print() and treeSize. Elements are displayed in the console.
	 * @precondition the method assumes that the incoming node is the root of a DOM tree that was created from an XML or HTML file.
	 * @param node is the first element of a DOM tree. 
	 * @postcondition currently the method outputs the element and its associated comment text on the command line.
	 */
	public void treePrint(MetadataNode node) 
	{
		node.print((int) node.treeSize(node));
	    
	}//end treePrint
}

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
		Document doc2 = null; 
		
		System.out.print("Enter file name and path: ");
		inputFile = scan.nextLine();
		file = new File(inputFile);
		doc1 = parse.metadataParse(file);
		
		System.out.print("Enter a second file name and path: ");
		inputFile = scan.nextLine();
		file = new File(inputFile);
		doc2 = parse.metadataParse(file);
		
		nNode = parse.importDOMTree(doc1);
		domNode = doc1.getParentNode();
		parse.treePrint(domNode);
		
		nNode = parse.addDOMTree(doc2, nNode);
		domNode = doc2.getParentNode();
		parse.treePrint(domNode);
		
		nNode = parse.openSessionTree(file);
		inputFile = parse.saveSessionTree(nNode);
				
		System.out.println("End of tree");
				
	}		
	
}

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
		MetaXMLParser parse = new MetaXMLParser();
				
		Document doc = null;
		
		System.out.print("Enter file name and path: ");
		inputFile = scan.nextLine();
		file = new File(inputFile);
		
		doc = parse.MetadataParse(file);
		/*MetaXMLParser newParser = new MetaXMLParser();
		doc = newParser.metaTreeDoc(file);				// send the metaTreeDoc method a file and it returns a DOM object.
		doc.normalize();
		nList = doc.getElementsByTagName("metadata");
						
		numDocTags = 0;
		nNode = nList.item(0);
		parse.treeTraverse(nNode);
		//treeTraverse(nNode);*/
		System.out.println("End of tree");
				
	}		
	
}

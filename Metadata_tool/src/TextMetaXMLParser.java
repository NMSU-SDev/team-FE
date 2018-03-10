/**
 * 
 * @author SJohnston
 * @version 1.0.0
 * @date March 9, 2018
 * Command line test code for interfacing with the MetaXMLParser
 */

import java.io.File;
import java.util.Scanner;

import javax.xml.parsers.*;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

public class TextMetaXMLParser {

	public static void main(String[] args) {
		// Instance variables
		File file;
		String inputFile = "";
		Scanner scan = new Scanner(System.in);
		int numDocTags = 0;
		NodeList nList = null;
		Node nNode = null;
		DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
		try
		{
			DocumentBuilder dB = dBF.newDocumentBuilder();
		}
		catch (Exception e)
		{
			System.err.println("Unable to configure parser");
		}
		
		Document doc = null;
		
		System.out.print("Enter file name and path: ");
		inputFile = scan.nextLine();
		file = new File(inputFile);
		
		MetaXMLParser newParser = new MetaXMLParser();
		doc = newParser.metaTreeDoc(file);
		nList = doc.getElementsByTagName("metadata");
						
		numDocTags = 0;
		nNode = nList.item(0);
		parentNode(nNode);
		System.out.println("End of tree");
				
	}
	
	public static void parentNode(Node pNode)
	{
	    NodeList tList = pNode.getChildNodes();
		Node tNode = pNode;
		
		//base case parent has no children
				
		if (!pNode.hasChildNodes()) 
		{				
			//System.out.println("Child has no children");
			whatToPrint(pNode);
			return;
		}	
		
		// else parent has children
		else 									
		{
			whatToPrint(pNode);
			//System.out.println("Parent has at least one child");			
			System.out.print("\t");
			tNode = pNode.getFirstChild();
			// Print all children
			whatToPrint(tNode);
			parentNode(tNode);
			if (tNode.getNextSibling() != null)
			{
				for (int children = 0; children < tList.getLength(); children++)	
				{					
					tNode = tList.item(children);
					//System.out.println("Child has a sibling");	
					parentNode(tNode);				
				}
			}
		}
		
		
		return;
	}
	
	public static void whatToPrint(Node printNode)
	{
		String isValid = printNode.getNodeName();
		if ( !(isValid== "#text") && !(isValid== "#comment") && !(isValid == null) )
			System.out.println(printNode.getNodeName());
	}

}

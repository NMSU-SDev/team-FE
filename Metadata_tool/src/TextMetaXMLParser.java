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
		//whatToPrint(nNode);
		treeTraverse(nNode);
		System.out.println("End of tree");
				
	}
	
	public static void treeTraverse(Node pNode)
	{		
		NodeList sList = pNode.getChildNodes();
		Node cNode = null;										// set instantiate child
		Node aNode = null;
		Node rNode = null;
		Node tNode = null;
		String isValid = pNode.getNodeName();
		String areNode = "";
		String teeNode = "";
		String ceeNode = "";
		String ayeNode = "";
		int index = 0;
		int sibling = 0;
		
		rNode = pNode;
		areNode = rNode.getNodeName();
		while (rNode.getNodeName() != "metadata")				// branch spacing
		{
			System.out.print("*");
			tNode = rNode.getParentNode();
			teeNode = tNode.getNodeName();
			rNode = tNode;
			areNode = rNode.getNodeName();
		}
		System.out.println(pNode.getNodeName());				// print this node				
			
		// RECURSIVE CASE: Does node have a child?
		if (pNode.hasChildNodes()) 									
		{																			
			cNode = pNode.getFirstChild();						// get oldest child					
			ceeNode = isValid= cNode.getNodeName();			
			index++;
			
			while ( (isValid == "#text") || (isValid == "#comment") || (isValid == null) && index < sList.getLength())
			{				
				// get next node				
				cNode = sList.item(index);
				ceeNode = isValid= cNode.getNodeName();					// load next node name into isValid string
				index++;

			}	// loop while an invalid child
								
			treeTraverse(cNode);								// check if child has children	
			
		}
		
		//does child have siblings
		if (pNode.getNextSibling() != null)
		{													
			sibling++;		
			aNode = pNode.getNextSibling();				
			ayeNode = isValid = aNode.getNodeName();
			while ( ((isValid == "#text") || (isValid == "#comment") || (isValid == null)) && aNode != null)
			{				
				tNode = aNode.getNextSibling();					// get next sibling	
				aNode = tNode;
				if (aNode != null)
					ayeNode = isValid= aNode.getNodeName();				
				sibling++; 
			}													// loop while and invalid child
			if (aNode != null)
				treeTraverse(aNode);							// check if sibling has children
			return;
		}
		
		//BASE CASE: parent has no children, return
		if (!pNode.hasChildNodes()) 						
		{											
			return;
		}
		
		return;
	}	

}

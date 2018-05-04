
/**
 * 
 * @author SJohnston & LHermann
 * @version 1.0.1
 * @date March 12, 2018
 * Command line test code for interfacing with the XmlSessionManager and learning how to navigate a DOM tree object.
 */

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TestXmlSessionManager
{

	static int numElementNode = 0;
	private static String inputFile = "";
	private static final int MAX = 2;
	private static XmlSessionManager parse = new XmlSessionManager();
	private static String [] templates = new String[MAX];
	private static Document [] docs = new Document [MAX];
	private static File [] files = new File [MAX];
	private static Scanner scan = new Scanner(System.in);
	/**
	 * The purpose of this method is to text the MetaXML Parser class and to
	 * develop and test the treeTraversal method so that it can be customized
	 * for incorporating its abilities into the Metadata tool GUI.
	 * 
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException
	{
		// Instance variables
		String response = "y";
		int index = 0;		
		MetadataNode<?> mNode = null;	
		// Scanner input = new Scanner(System.in);
		boolean done = false;
		do 
		{
			files[index] = userInput(index);	
			docs [index] = parse.fileToDOM(files[index]);
			
			System.out.print("Would you like to enter another file? (Y/N) ");	
			if (scan.hasNext())
			{
				response = scan.nextLine();
				response = response.toLowerCase();
			}			
			else 
			{
				response = "n";
			}			
			if (response == "n")
				done = true;
			index++;
		} while (!done && index < MAX);
		// code works find up to this point... <SJohnston 12:23am 4/20/2018> //
		index = 0;

		// ** TEST IMPORT DOM TO METADATA ** input a Node, output a MetadataNode
		// object //
		NodeList nList = null;
		Node nNode = null;
		nList = docs[index].getElementsByTagName("metadata");
		nNode = nList.item(index);
		//System.out.println(nNode.getNodeName());
		mNode = parse.importDOMToMetadata(nNode);		
		mNode.print(0); // print terminates
		// code works find up to this point... <SJohnston 2:51am 4/22/2018> //
		
		// ** TEST DOM PRINT ** input a Node, output to console the contents of
		// the Node tree //
		nNode = nList.item(0);
		parse.printDOM(nNode);
		// code works find up to this point... <SJohnston 10:12Pm 4/23/2018> //		
		
		// ** TEST SAVE SESSION ** //
		// open saved file in default text editor, check for formatting //
		inputFile = parse.saveSession(mNode, mNode, templates);
		saveFileToDisk(inputFile);
		/** !!! UNUSED BUT SAVED FOR LATER CODE !!!
		inputFile = ".\\test.txt";
		files[index] = new File (inputFile);
		
		try {
			Desktop.getDesktop().open(files[index]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		// ** TEST ADD DOM TO TREE ** input a Node and the root of a
		// MetadataNode tree, output an updated MetadataNode root with
		// dissimilar nodes added 
		// mNode = parse.addDOMToTree(doc1[0].getParentNode(), mNode);
		

		// ** TEST PRINT METADATA TREE  ** input a MetadataNode, output to
		// console contents of the MetadataNode tree //
		parse.printMetadataTree(mNode);				// !!! NOT WORKING !!! MetadataNode cannot be cast to org.w3c.dom.NodeList
		
		
		// ** TEST OPEN SESSION ** input session file, current MetadataNode, array of Strings, 
		// update current MetadataNode and array of Strings, output root of MetadataNode tree
		
		
		// ** TEST SAVE METADATA TO DOM ** input a DOM with unpopulated fields and a populated MetadataNode and output a DOM with populated fields
		// import file of a blank template to a DOM (doc1)
		
		// print DOM (doc1) to show it has no entries
		
		// import file of a filled template to a DOM (doc2), then to a MetadataNode
		
		// print MetadataNode to show it has entries
		
		// update DOM (doc1) with MetadataNode
		
		// print DOM (doc1) to show it now has entries
		
		

		// ** TEST METADATA TREE TO STRING ** input MetadataNode, output
		// indented String of the MetadataNode tree //
		// The method saveSession calls metadataTreeToString and already works //

		// ** TEST DOM TREE TO STRING ** input Node, output indented String of
		// the Node tree //
		// The method printDOM calls domTreeToString and already works //

		System.out.println("End of tree");
		printNode(mNode);
		
		
	}

	public static void printNode(MetadataNode<?> mNode)
	{
		System.out.println("Element: " + mNode.getElement());
		System.out.println("Element Name: " + mNode.getElementName());
		System.out.println("Question: " + mNode.getQuestion());
		System.out.println("Answer: " + mNode.getAnswer());
		System.out.println("Verified: " + mNode.getVerified());
		System.out.println(!(mNode.getChild() == null) ? "Has child" : "Has no child");
		System.out.println(!(mNode.getSibling() == null) ? "Has sibling" : "Has no sibling");
		return;
	}

	/**
	 * The userInput method is so user input can be called from many different tests
	 * @return
	 */
	private static File userInput(int ndx)
	{				
		System.out.print("Enter name and path for a metadata file: ");
		templates[ndx] = scan.nextLine();
		return (new File(templates[ndx]));
	}
	
	/**
	 * TreePrint method walks a MetadataNode tree extracting each element and
	 * the comments associated that element. This method has been
	 * modified/upgraded to call the node methods print() and treeSize. Elements
	 * are displayed in the console.
	 * 
	 * @precondition the method assumes that the incoming node is the root of a
	 *               DOM tree that was created from an XML or HTML file.
	 * @param node
	 *            is the first element of a DOM tree.
	 * @postcondition currently the method outputs the element and its
	 *                associated comment text on the command line.
	 */
	public void treePrint(MetadataNode<?> node)
	{
		node.print((int) MetadataNode.treeSize(node));

	}// end treePrint
	
	private static void saveFileToDisk(String filePath)
	{
		FileWriter writer = null;
		try
		{
		    writer = new FileWriter("test.txt");
		    writer.write(filePath);
		    writer = new FileWriter("test.xsm");
		    writer.write(filePath);
		}
		catch ( IOException e)
		{
		}
		finally
		{
		    try
		    {
		        if ( writer != null)
		        writer.close( );
		    }
		    catch ( IOException e)
		    {
		    }
		}		
		// code works find up to this point... <SJohnston 10:12Pm 4/23/2018> //
	}
}

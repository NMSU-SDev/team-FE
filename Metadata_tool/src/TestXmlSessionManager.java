
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
		final int MAX = 1;
		int index = 0;
		File file;
		String inputFile = "";
		Scanner scan = new Scanner(System.in);
		MetadataNode<?> mNode = null;
		XmlSessionManager parse = new XmlSessionManager();
		
		String [] templates = new String[MAX];		
		Document [] doc1 = new Document [MAX];
		
		do {
		System.out.print("Enter name and path for " + (index+1) + " of " + MAX + " files: ");
		inputFile = scan.nextLine();
		templates[index] = inputFile;
		file = new File(templates[index]);
		
		// ** TEST XML FILE PARSER ** input a file, output a Document object //
		doc1 [index] = parse.fileToDOM(file);
		System.out.println(doc1[index].getDocumentURI());
		index ++;
		} while (index < MAX);
		// code works find up to this point... <SJohnston 12:23am 4/20/2018> //
		

		// ** TEST IMPORT DOM TO METADATA ** input a Node, output a MetadataNode
		// object //
		NodeList nList = null;
		Node nNode = null;
		nList = doc1[0].getElementsByTagName("metadata");
		nNode = nList.item(0);
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
		FileWriter writer = null;
		try
		{
		    writer = new FileWriter("test.txt");
		    writer.write(inputFile);
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
		inputFile = ".\\test.txt";
		file = new File (inputFile);
		
		try {
			Desktop.getDesktop().open(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// code works find up to this point... <SJohnston 10:12Pm 4/23/2018> //
		
		// ** TEST ADD DOM TO TREE ** input a Node and the root of a
		// MetadataNode tree, output an updated MetadataNode root with
		// dissimilar nodes added //
		// mNode = parse.addDOMToTree(doc1[0].getParentNode(), mNode);
		

		// ** TEST PRINT METADATA TREE  ** input a MetadataNode, output to
		// console contents of the MetadataNode tree //
		parse.printMetadataTree(mNode);				// !!! NOT WORKING !!! MetadataNode cannot be cast to org.w3c.dom.NodeList
		
		
		// ** TEST OPEN SESSION ** //
		

		// ** TEST METADATA TREE TO STRING ** input MetadataNode, output
		// indented String of the MetadataNode tree //
		// The method saveSession calls metadataTreeToString and already works //

		// ** TEST DOM TREE TO STRING ** input Node, output indented String of
		// the Node tree //
		// The method printDOM calls domTreeToString and already works //

		System.out.println("End of tree");
		printNode(mNode);
		
		scan.close();
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
}


/**
 * 
 * @author SJohnston & LHermann
 * @version 1.0.1
 * @date March 12, 2018
 * Command line test code for interfacing with the XmlSessionManager and learning how to navigate a DOM tree object.
 */

import java.awt.Desktop;
import java.io.BufferedWriter;
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
		File file;
		String inputFile = "";
		Scanner scan = new Scanner(System.in);
		MetadataNode<?> mNode = null;
		XmlSessionManager parse = new XmlSessionManager();
		String [] templates = new String[10];		

		Document doc1 = null;
		Document doc2 = null;

		System.out.print("Enter file name and path: ");
		inputFile = scan.nextLine();
		templates[0] = inputFile;
		file = new File(templates[0]);
		
		// ** TEST XML FILE PARSER ** input a file, output a Document object //
		doc1 = parse.fileToDOM(file);
		System.out.println(doc1.getDocumentURI());

		// ** TEST IMPORT DOM TO METADATA ** input a Node, output a MetadataNode
		// object //
		NodeList nList = null;
		Node nNode = null;
		nList = doc1.getElementsByTagName("metadata");
		nNode = nList.item(0);
		System.out.println(nNode.getNodeName());
		mNode = parse.importDOMToMetadata(nNode);
		System.out.println(mNode.toString());

		// ** TEST ADD DOM TO TREE ** input a Node and the root of a
		// MetadataNode tree, output an updated MetadataNode root with
		// dissimilar nodes added //
		System.out.print("Enter file name and path: ");
		inputFile = scan.nextLine();
		templates[1] = inputFile;
		file = new File(templates[1]);		
		doc2 = parse.fileToDOM(file);
		
		mNode = parse.addDOMToTree(doc1.getParentNode(), mNode);

		// ** TEST DOM PRINT ** input a Node, output to console the contents of
		// the Node tree //
		parse.printDOM(doc1.getParentNode());

		// ** TEST METADATA TREE PRINT ** input a MetadataNode, output to
		// console contents of the MetadataNode tree //
		parse.printMetadataTree(mNode);

		// ** TEST SAVE SESSION ** //
		// open saved file in default text editor, check for formatting //
		inputFile = parse.saveSession(mNode, mNode, templates);
		BufferedWriter writer = null;
		try
		{
		    writer = new BufferedWriter( new FileWriter("test.xsm"));
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
		inputFile = "\test.xsm";
		file = new File (inputFile);
		
		try {
			Desktop.getDesktop().open(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// ** TEST OPEN SESSION ** //
		

		// ** TEST METADATA TREE TO STRING ** input MetadataNode, output
		// indented String of the MetadataNode tree //

		// ** TEST DOM TREE TO STRING ** input Node, output indented String of
		// the Node tree //

		System.out.println("End of tree");
		scan.close();
	}

	public void printNode(MetadataNode<?> mNode)
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

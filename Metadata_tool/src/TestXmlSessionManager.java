
/**
 * @author SJohnston & LHermann
 * @version 2.0
 * @date began March 12, 2018
 * @date revised May 5, 2018
 * @deprecated Command line test code for interfacing with the 
 * @deprecated XmlSessionManager and learning how to navigate a DOM tree object.
 * TestXmlSessionManager purpose is to aid in testing the methods of 
 * the XmlSessionManager class to verify that they meet requirements
 * specified by the user.
 */

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Scanner;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.util.Date;

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
	private static String logOfTest = "";
	private static int projectNumber = 0;
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
		logOfTest += "BEGIN TEST XML SESSION MANAGER\n\n";
		logOfTest += "INSTANTIATION AND INITIALIZATION\n";
		String response = "y";
		int index = 0;
		int counter = 1;
		MetadataNode<?> mNode = null;	
		MetadataNode<?> currentNode = new MetadataNode("", (MetadataNode)null, (MetadataNode)null);
		// Scanner input = new Scanner(System.in);		
		boolean done = false;
		// Select files for test
		do 
		{
			files[index] = userInput(index);
			docs [index] = parse.fileToDOM(files[index]);
			
			System.out.print("Would you like to enter another file? (Y/N) ");	
			if (scan.hasNext())
			{
				response = scan.nextLine();
				response = response.toLowerCase();
				if (response.equals("n"))
				{
					done = true;
				}
				else 
				{
					done = false;
					counter++;
					index++;
				}
				
			}
			else 
			{
				System.out.println("Invalid entry");
			}
			
		} while (!done && index < MAX);
		// code works find up to this point... <SJohnston 12:23am 4/20/2018> //
		index = 0;
		done = false;
		
		logOfTest += (counter + " XML files added:\n");
		
		while (index < templates.length && index < counter)
		{
			logOfTest += (templates[index] + "\n");
			index++;
		}
		logOfTest += "END INSTANTIATION AND INITIALLIZATION\n\n";		
		saveFileToDisk("test_1", logOfTest);
		
		// ** TEST IMPORT DOM TO METADATA ** input a Node, output a MetadataNode
		// object //
		index = 0;
		logOfTest += "TEST IMPORT DOM TO METADATA\n";
		NodeList nList = null;
		Node nNode = null;
		nList = docs[index].getElementsByTagName("metadata");
		nNode = nList.item(index);
		//System.out.println(nNode.getNodeName());
		mNode = parse.importDOMToMetadata(nNode);
		if (mNode != null)
		{
			logOfTest += "XmlSessionManager importDOMToMetadata - PASS\n";
			logOfTest += (parse.metadataTreeToString(mNode) + "\n");
			mNode.print(0); // print terminates
			logOfTest += "MetadataNode.print to console - PASS\n";
		}
		else 
		{
			logOfTest += "XmlSessionManager importDOMToMetadata - FAIL\n";
			logOfTest += "MetadataNode.print to console - FAIL\n";
		}// code works find up to this point... <SJohnston 2:51am 4/22/2018> //
		logOfTest += "END IMPORT DOM TO METADATA TEST\n\n";
		saveFileToDisk("test_2", logOfTest);
		
		
		// ** TEST DOM PRINT ** input a Node, output to console the contents of
		// the Node tree //
		logOfTest += "TEST DOM PRINT\n";
		nNode = nList.item(0);
		if (nNode != null)
		{
			logOfTest += "Node tree to String:\n";
			logOfTest += (parse.domTreeToString(nNode) + "\n");
			parse.printDOM(nNode);
			logOfTest += "XmlSessionManager.printDOM to console - PASS\n";
		}
		else 
		{
			logOfTest += "XmlSessionManager.domTreeToString - FAIL\n";
			logOfTest += "XmlSessionManager.printDOM to console - FAIL\n";
		}// code works find up to this point... <SJohnston 10:12Pm 4/23/2018> //
		logOfTest += "END DOM PRINT TEST\n\n";
		saveFileToDisk("test_3", logOfTest);
				
		
		// ** TEST SAVE SESSION ** //
		// open saved file in default text editor, check for formatting //
		logOfTest += "TEST SAVE SESSION\n";
		inputFile = parse.saveSession(mNode, mNode, templates);
		if (inputFile != "")
		{
			logOfTest += "XmlSessionManager.saveSession String:\n";
			logOfTest += (inputFile + "\n");
			saveFileToDisk("session_1", inputFile);
			logOfTest += "Session file saved to disk.\n";
		}
		else
		{
			logOfTest += "XmlSessionManager.saveSession - FAIL\n";
		}
		logOfTest += "END SAVE SESSION TEST\n\n";
		saveFileToDisk("test_4", logOfTest);
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
		
		// ** TEST ADD DOM TO METADATA NODE TREE ** input a Node and the root of a
		// MetadataNode tree, output an updated MetadataNode root with
		// dissimilar nodes added 
		mNode = null;
		logOfTest += "TEST ADD DOM TO METADATA NODE TREE\n";
		mNode = parse.addDOMToTree(docs[0].getParentNode(), mNode);
		if (mNode != null)
		{
			logOfTest += parse.metadataTreeToString(mNode);
			
		}
		logOfTest += "END ADD DOM TO METADATA NODE TREE TEST\n\n";
		saveFileToDisk("test_5", logOfTest);

		// ** TEST OPEN SESSION ** input session file, current MetadataNode, array of Strings, 
		mNode = new MetadataNode("", (MetadataNode)null, (MetadataNode)null);
		logOfTest += "TEST OPEN SESSION\n";
		logOfTest += (printNode(mNode) + "\n");				
		// update current MetadataNode and array of Strings, output root of MetadataNode tree
		mNode = parse.openSession(files[1], currentNode, templates);
		logOfTest += ("Root MetadataNode:\n" + printNode(mNode) + "\n");
		logOfTest += ("MetadataNode Tree from Session File:\n" + parse.metadataTreeToString(mNode) + "\n");
		logOfTest += ("Current Node:\n" + printNode(currentNode) + "\n");
		logOfTest += ("Template files:\n");
		index = 0;
		while (index < templates.length && index < MAX)
		{
			logOfTest += (templates[index] + "\n");
			index++;
		}
		logOfTest += "END OPEN SESSION TEST\n\n";
		saveFileToDisk("test_6", logOfTest);
		
		// ** TEST SAVE METADATA TO DOM ** input a DOM with un-populated fields and a populated MetadataNode and output a DOM with populated fields
		logOfTest += "TEST SAVE METADATA TO DOM\n";
		index = 0;
		mNode = new MetadataNode("empty", (MetadataNode)null, (MetadataNode)null);
		logOfTest += "Contents of Metadata Node before loading DOM:\n";
		logOfTest += (printNode(mNode) + "\n");
		
		// import file of a blank template to a DOM (doc1)
		logOfTest += "Path to a blank XML template file:\n";
		System.out.print("Path to a blank XML template file: ");		
		files[index] = userInput(index);
		docs[index] = parse.fileToDOM(files[index]);
		// show loaded contents of DOM (docs[0]) to show it has no entries
		logOfTest += ("DOM contents:\n" + parse.domTreeToString(docs[index]));
				
		index++; 
		
		// import file of a filled template to a DOM (docs[1]), then to a MetadataNode
		logOfTest += "Path to a filled XML template file:\n";
		System.out.println("Path to a filled XML template file:\n");
		files[index] = userInput(index);
		docs[index] = parse.fileToDOM(files[index]);
		// show loaded contents of DOM (docs[1]) to show it has populated entries
		logOfTest += ("DOM contents:\n" + parse.domTreeToString(docs[index]));
		logOfTest += "Loading the Metadata Node...\n";
		nList = docs[index].getChildNodes();
		nNode = nList.item(index);
		mNode = parse.importDOMToMetadata(nNode);
		// show MetadataNode to show it has entries
		logOfTest += ("Metadata Node contents:\n");		
		
		index--;
		
		// update DOM (docs[0]) with MetadataNode entries
		logOfTest += "Saving entries from Metadata Node Tree to DOM with no entries...\n";
		parse.saveMetadataToDOM(mNode, docs[index]);
		
		// print DOM (docs[0]) to show it now has entries
		logOfTest += "Contents of updated DOM:\n";
		logOfTest += parse.domTreeToString(docs[index]);
		logOfTest += "END SAVE METADATA TO DOM TEST\n\n";
		saveFileToDisk("test_7", logOfTest);
		
		// ** TEST EXPORT XML FILES ** input DOM array, templates array, and project Number
		// output files to disk
		logOfTest += "TEST EXPORT XML FILES\n";
		System.out.print("What is the project number: ");
		projectNumber = scan.nextInt();
		inputFile = ("" + projectNumber);
		parse.exportXMLFiles(docs, templates, inputFile);
		index = 0;
		while (index < templates.length && index < MAX)
		{
			logOfTest += (templates[index] + "\n");
		}
		
		System.out.print("Root of MetadataNode: ");
		printNode(mNode);	
		logOfTest += "\nEND TEST XML SESSION MANAGER";
		saveFileToDisk("test_8", logOfTest);
	}

	public static String printNode(MetadataNode<?> mNode)
	{
		String nodeReport = "";
		nodeReport += ("Element: " + mNode.getElement() + "\n");
		nodeReport += ("Element Name: " + mNode.getElementName() + "\n");
		nodeReport += ("Question: " + mNode.getQuestion() + "\n");
		nodeReport += ("Answer: " + mNode.getAnswer() + "\n");
		nodeReport += ("Verified: " + mNode.getVerified() + "\n");
		nodeReport += ((!(mNode.getChild() == null) ? "Has child" : "Has no child") + "\n");
		nodeReport += ((!(mNode.getSibling() == null) ? "Has sibling" : "Has no sibling") + "\n");
		nodeReport += ((!(mNode.getParent() == null) ? "Has parent" : "Is root") + "\n");
		return nodeReport;
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
	
	private static void saveFileToDisk(String fileName, String fileContent)
	{
		FileWriter writer = null;
		try
		{
		    writer = new FileWriter((fileName + ".txt"));
		    writer.write(fileContent);
		    writer = new FileWriter((fileName + ".xsm"));
		    writer.write(fileContent);
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

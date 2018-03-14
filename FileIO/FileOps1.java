import java.io.*;
import java.nio.file.*;
import java.util.Scanner;

import javax.swing.JFileChooser;

import java.awt.Desktop;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

/* Merrick Metadata Project
 * CS 371 Software Dev - Spring 2018
 * 2018 March 12 - 16
 * 
 * Basic File I/O operations code
 * Revision 2a
 * 
 * Author: Jacob Espinoza
 * 
 * Description: Basic File I/O operations that work on text files
 * 
 * */
public class FileOps1 {
	
	/**
	 * Attempts to create a blank new file, given a path object for the location of the new file.
	 * Displays a message if the file was created or not.
	 * A new file will not be created if one with the same name already exists.
	 * @param filePath A path object for the file that is to be created
	 */
	public static void createFile ( Path filePath )  {
		File fObj = filePath.toFile();
		String fName = fObj.getName();
		
		try {
			if ( fObj.createNewFile() ) {
				// created a new file
				System.out.printf("New file \"%s\" was created!\n", fName );
			}
			else {
				// otherwise don't create a new file
				System.out.printf("File \"%s\" already exists, no action done.\n", fName );
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Attempts to create a new file, given a path object for the location of the new file.
	 * Writes some sample XML code to the new file.
	 * Displays a message if the file was created or not.
	 * If the file already exists, it will not be altered.
	 * @param filePath A path object for the file that is to be created
	 */		
	public static void createFileStub ( Path filePath ) {
		
		File fObj = filePath.toFile();
		String fName = fObj.getName();
		
		String sample = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
				"<note>\r\n" + 
				"  <heading>Test File</heading>\r\n" + 
				"  <body>This is a test XML file</body>\r\n" + 
				"</note> ";
		byte byteArray[] = sample.getBytes();
		
		if ( fObj.exists() == false ) {
			if ( !(fObj.toString().contains("xml")) ) System.out.println(" WARNING: file type mismatch, expected XML.");
			try( OutputStream out = new BufferedOutputStream(Files.newOutputStream(filePath, CREATE))) {
				out.write( byteArray, 0, byteArray.length );
				System.out.println(" XML file creation successful!");
			}
			catch (IOException e ) {
				System.err.println( e );
			}
		}
		else
			System.out.printf(" **File \"%s\" already exists!!\n", fName );
		
	} // end create file stub method
	
	
	/**
	 * The writeFile method writes to the file that was specified.
	 * In future versions, it will write XML code generated from a document object model.
	 * 
	 * @param fileObjIn a File object that will be written to.
	 * @return a File object reference to the saved file or null if an error occurred.
	 */
	// an additional parameter will be a document object model object
	public static File writeFile ( File fileObjIn ) {
		
		// initialize the saved file object to null at the start
		File savedFile = null;
		
		Path pathToFile = fileObjIn.toPath();
		
		// some test output to write to the file
		String content = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
				"<note>\r\n" + 
				"  <heading>Test File</heading>\r\n" + 
				"  <body>This test XML file tests the file writing method.</body>\r\n" + 
				"</note> ";
		byte byteArray[] = content.getBytes();
		
		try( OutputStream out = new BufferedOutputStream(Files.newOutputStream(pathToFile, CREATE, APPEND))) {
			out.write( byteArray, 0, byteArray.length );
			System.out.println(" XML file creation successful!");
		}
		catch (IOException e ) {
			System.err.println( e );
			return savedFile;
		}
		
		// no exceptions were handled by the catch block
		// return a File object reference to the saved file
		return ( savedFile = fileObjIn );
		
	}
	
	/**
	 * Opens the file specified using the default program associated to that file type.
	 * Assumes the program is run on an OS with GUI support.
	 * 
	 * @param fileObj A File object that corresponds to the file to be opened.
	 * @return a File object of the file that was opened or null if the file could not be opened.
	 */
	public static File openFile ( ) {
		
		final JFileChooser fc = new JFileChooser();
		int fileChooserReturnVal;
		File fileSelected = null;
		
		// implementation 1: opens files using default programs
		// ** mainly for testing, requires an OS with a GUI
		if (!Desktop.isDesktopSupported() ) {
			System.err.println(" *** ERROR: Desktop is not supported on this system.");
			return null;
		}
		// desktop is used for now to open the file in the OS' default program
		Desktop d1 = Desktop.getDesktop();
		
		// will need to change parameter of showOpenDialog to the parent component of the dialog box
		fileChooserReturnVal = fc.showOpenDialog(null);
		fileSelected = fc.getSelectedFile();
		if ( fileSelected != null ) {
			System.out.printf("File selected = %s\n", fileSelected.toString() );
		} else 
			System.out.println("No file was selected.");
		
		
		if ( fileChooserReturnVal == JFileChooser.APPROVE_OPTION ) {
			try { d1.open( fileSelected ); }
			catch (Exception e) { e.printStackTrace(); }
			return fileSelected;
		}
		else if ( fileChooserReturnVal == JFileChooser.CANCEL_OPTION ) {
			System.out.println("User cancelled file selection.");
			return null;
		}
		else {
			// the user closed the dialog or an error occurred.
			return null;
		}
		
	} // end method openFile

	public static void main(String[] args) {
		// Main for testing purposes only!
		
		Scanner s = new Scanner(System.in);
		
		Path p = Paths.get("./test.xml");
		Path p2;
		
        StringBuffer pathString1 = new StringBuffer( "./" );
        
        System.out.println("Enter a filename to create a blank file in current directory (include extension):");

        pathString1.append( s.nextLine() );
        p2 = Paths.get( pathString1.toString() );
       
        createFile( p2 );
		
		System.out.println("\nCalling create file stub function.....");
		createFileStub( p );
		
		System.out.println("\nAn open file dialog box has been created");
		
		openFile( );
		System.err.flush();
		s.close();
		
		System.out.println("  Done." );

	}

}

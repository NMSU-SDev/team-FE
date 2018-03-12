import java.io.*;
import java.nio.file.*;
import java.util.Scanner;
import java.awt.Desktop;

//import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

/* Merrick Metadata Project
 * CS 371 Software Dev - Spring 2018
 * 2018 March 12 - 16
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
	
	public static void saveFile ( File fileObj ) {
		// TODO work on this
		// ** more specification is needed
	}
	
	/**
	 * Opens the file specified using the default program associated to that file type.
	 * Assumes the program is run on an OS with GUI support.
	 * 
	 * @param fileObj A File object that corresponds to the file to be opened.
	 */
	public static void openFile ( File fileObj ) {
		// work on this
		boolean fileExists1 = fileObj.exists();
		
		if ( fileExists1 == false ) {
			System.err.println(" *** ERROR: File cannot be opened, it does not exist!");
			return;
		}
		
		// implementation 1: opens files using default programs
		// ** mainly for testing, requires an OS with a GUI
		if (!Desktop.isDesktopSupported() ) {
			System.err.println(" *** ERROR: Desktop is not supported on this system.");
			return;
		}
		
		Desktop d1 = Desktop.getDesktop();
		if ( fileExists1 ) {
			try {
				d1.open( fileObj );
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	} // end method openFile

	public static void main(String[] args) {
		// Main for testing purposes only!
		
		Scanner s = new Scanner(System.in);
		
		Path p = Paths.get("./test.xml");
		Path p2;
		File f1;
		
        StringBuffer pathString1 = new StringBuffer( "./" );
        StringBuffer file1s = new StringBuffer("./");
        
        System.out.println("Enter a filename to create a blank file (include extension):");

        pathString1.append( s.nextLine() );
        p2 = Paths.get( pathString1.toString() );
       
        createFile( p2 );
		
		System.out.println("\nCalling create file stub function.....");
		createFileStub( p );
		
		System.out.println("\nEnter a filepath to open the file (same directory) ");
		
		file1s.append( s.nextLine() );
		f1 = new File ( file1s.toString() );
		
		openFile( f1 );
		System.err.flush();
		s.close();
		
		System.out.println(" Done." );

	}

}

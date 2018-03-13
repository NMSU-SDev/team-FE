/**
 * 
 * @author SJohnston
 * @version 1.0.0
 * @date March 9, 2018 
 */

import java.io.*;

import java.lang.Object;

import javax.xml.parsers.*;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class MetaXMLParser {
		
	/**
	 * Default MetaXMLParser constructor
	 */
	public MetaXMLParser()
	{
		dbFact = null; 
		dBuild = null;
		metaDoc = null;
	}
	
	/**
	 * XML tree document creator
	 * @precondition incoming file is an XML or HTML file	 * 
	 * @param file File object from calling method
	 * @return returns a Document tree object
	 */
	public Document metaTreeDoc (File file)
	{		
		try 
		{
			dbFact = DocumentBuilderFactory.newInstance(); 
			dBuild = dbFact.newDocumentBuilder();
			metaDoc = dBuild.parse(file);
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		return metaDoc; 		
	}
	
	
		
	//Instance Variables
	private DocumentBuilderFactory dbFact;
	private DocumentBuilder dBuild;
	private Document metaDoc;

}

import java.io.File;
import java.util.ArrayList;

/* SharedData.java
 * Description: contains all of the global shared data our application needs
 *   Allows data to move between the different frames and dialogs.
 *   
 * Author: Jacob Espinoza (initial)
 * Date: 2018 April 09
 * Last revised: 2018 May 13
 * 
 * */

public class SharedData
{
	public static File templateFile;
	public static boolean templateStatus;
	public static File sessionFile;
	
	private static ArrayList<String> aListPaths = new ArrayList<String>();

	public static void setTemplateFile(File fInput)
	{
		SharedData.templateFile = fInput;
		if (fInput != null ) {
			SharedData.aListPaths.add( fInput.getAbsolutePath() );
		} else {
			SharedData.aListPaths.add("");
		}
		
	}
	
	public static boolean removeTemplateFile(String fileSubStr) {
		String compare1 = "";
		
		for ( int i = 0; i < aListPaths.size(); i++ ) {
			compare1 = aListPaths.get(i);
			if ( compare1.contains(fileSubStr) ) {
				aListPaths.remove(i);
				return true;
			}
		}
		// the loop finished and a substring was never found
		return false;
	}
	
	public static void addTemplateFile(File fInput)
	{
		SharedData.aListPaths.add( fInput.getAbsolutePath() );
	}

	public static File getTemplateFile()
	{
		return SharedData.templateFile;
	}
	
	public static String[] getTemplatePaths()
	{
		String[] a = new String[ aListPaths.size()];
		aListPaths.toArray(a);
		return a;
	}

	public static void changeTemplateSet(boolean result)
	{
		SharedData.templateStatus = result;
	}

	public static boolean isTemplateSet()
	{
		return templateStatus;
	}
	
	public File getFilefromClPath(String fName) {
		StringBuilder sb = new StringBuilder();
		String str = "";
		
		// get the file from classpath resources
		ClassLoader cl = getClass().getClassLoader();
		File retFile = new File( cl.getResource(fName).getFile() );
		
		try {
			sb.append("File absolute path: ");
			sb.append( retFile.getAbsolutePath() ).append("\n");
			str = sb.toString();
			
			System.out.println(str);
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		
		return retFile;		
	}

}

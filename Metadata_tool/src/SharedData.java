import java.io.File;

/* SharedData.java
 * Description: contains all of the global shared data our application needs
 *   Allows data to move between the different frames and dialogs.
 *   
 * Author: Jacob Espinoza (initial)
 * Date: 2018 April 09
 * 
 * */

public class SharedData
{
	public static File templateFile;
	public static boolean templateStatus;
	public static File sessionFile;

	public static void setTemplateFile(File fInput)
	{
		SharedData.templateFile = fInput;
	}

	public static File getTemplateFile()
	{
		return SharedData.templateFile;
	}

	public static void changeTemplateSet(boolean result)
	{
		SharedData.templateStatus = result;
	}

	public static boolean isTemplateSet()
	{
		return templateStatus;
	}

}

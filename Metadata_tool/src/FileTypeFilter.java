/**
 * FileTypeFilter permits the filtered display of only certain file
 * types in the JFileChooser dialog box. More than one file type are
 * permitted to be displayed in the drop down menu by creating multiple
 * instances of this class.
 * @author Sanford Johnston
 * @date April 1, 2018
 * @version 1.0
 */
import java.io.File;

import javax.swing.filechooser.FileFilter;

public class FileTypeFilter extends FileFilter 
{

	private String description = "";
	private String fileExt = "";

	public FileTypeFilter(String extension) {
		fileExt = extension;
	}

	public FileTypeFilter(String extension, String typeDescription) {
		fileExt = extension;
		this.description = typeDescription;
	}

	@Override
	public boolean accept(File file) {
		if (file.isDirectory())
			return true;
		return (file.getName().toLowerCase().endsWith(fileExt));
	}

	@Override
	public String getDescription() {
		return description + String.format(" (*$s, args)", fileExt);
	}

}

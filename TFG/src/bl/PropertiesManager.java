package bl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;

@Singleton
@LocalBean
public class PropertiesManager {

	public File[] PropFileList(String user) {

		// We're going to get all the property files that Radicale generate
		// when it creates a new addressbook so the user will be able to
		// select what addressbook he wants to edit
		File file = new File("/var/www/Radicale-1.1.1/collections/" + user);
		FilenameFilter filenameFilter = new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				if (name.lastIndexOf('.') > 0) {
					// get last index for '.' char
					int lastIndex = name.lastIndexOf('.');

					// get extension
					String str = name.substring(lastIndex);

					// match path name extension
					if (str.equals(".props")) {
						return true;
					}
				}
				return false;
			}
		};
		File[] listOfFiles = file.listFiles(filenameFilter);
		return listOfFiles;
	}
		public List<String> DescriptionFromPropFiles(File[] file)
	{
		List<String> description = new ArrayList<String>();
		try {
		for (File propFile : file) {
			
				BufferedReader br= new BufferedReader(new FileReader(propFile.getAbsolutePath()));
				String line =br.readLine();
				System.out.println(line);
				line=line.substring(line.indexOf("displayname"));
				line=line.substring(line.indexOf(" ")+2,line.indexOf("\"")-1);
				description.add(line.toString());
				br.close();
		} }catch (Exception e) {
				// TODO Auto-generated catch block
				description.add("Doesn't exist any addressbook");
			}
			
			
		
		return description;
		
	}
}

package bl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
@Singleton
@LocalBean
public class PropertiesManager {

	
	public File[] getPropFileList(String user)
	{
		
		//We're going to get all the property files that Radicale generate 
		//when it creates a new addressbook so the user will be able to 
		//select what addressbook he wants to edit
		File file=new File("/var/www/Radicale-1.1.1/collections/"+user);
		FilenameFilter filenameFilter=new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				 if(name.lastIndexOf('.')>0)
	               {
	                  // get last index for '.' char
	                  int lastIndex = name.lastIndexOf('.');
	                  
	                  // get extension
	                  String str = name.substring(lastIndex);
	                  
	                  // match path name extension
	                  if(str.equals(".txt"))
	                  {
	                     return true;
	                  }
	               }
				return false;	
			}	
		};
			File[] listOfFiles=file.listFiles(filenameFilter);
			return listOfFiles;
	}
	
	public List<String> getDescriptionFromPropFiles(File[] file)
	{
		List<String> description = new ArrayList<String>();
		for (File propFile : file) {
			try {
				BufferedReader br= new BufferedReader(new FileReader(propFile));
				String line =br.readLine();
				line=line.substring(line.indexOf("description"));
				line=line.substring(line.indexOf(" ")+2,line.indexOf(","));
				description.add(line);
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		return description;
		
	}
}

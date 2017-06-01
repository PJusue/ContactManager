package bl;

import java.io.File;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;

@Singleton
@LocalBean
public class GetVCardFileName {

	public String getFileName(String user, String description) {

		String [] fileNamePart = null;
		PropertiesManager properties = new PropertiesManager();
		File[] fileList = properties.PropFileList(user);
		List<String> descriptionList = properties
				.DescriptionFromPropFiles(fileList);
		for (int i = 0; i < descriptionList.size(); i++) {
			if (descriptionList.get(i).equalsIgnoreCase(description)) {

				fileNamePart = fileList[i].getName().split("\\.");

			}
		}
		
		return fileNamePart[0];

	}

}

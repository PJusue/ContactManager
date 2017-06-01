package pl;

import java.io.File;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import bl.PropertiesManager;


@ManagedBean
@SessionScoped
public class PrintAddressbooks {
	
	@EJB
	private PropertiesManager ejb;
	/*@EJB
	private ReadFromVcardFile ejb1;*/
	private String user;
	private List<String> descriptionList;
	public List<String> getPropertiesFromAVCardFile()
	{
		File[] fileCollection=ejb.getPropFileList(user);
		descriptionList=ejb.getDescriptionFromPropFiles(fileCollection);
		return descriptionList;
	}

	
	
}

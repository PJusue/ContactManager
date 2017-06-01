package pl;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import bl.PropertiesManager;

@SessionScoped
@ManagedBean
//This managebean allows the user to choose an addressbook 
public class PropertiesDescriptions {

	@EJB
	private PropertiesManager ejb = new PropertiesManager();
	private String user;
	private String description;
	private boolean var=true;
	public List<String> theDescriptions(String user) {
		List<String> descriptions;
		descriptions= ejb.DescriptionFromPropFiles(ejb.PropFileList(user));
		if(descriptions.size()!=0)
		{
		if(descriptions.get(0).equalsIgnoreCase("Doesn't exist any addressbook"))
			setVar(false);
		else
			setVar(true);
		}
		
		return descriptions;
	}
	public void chooseDescription(String description)
	{
		this.description=description;
	}
	public String getUser()
	{
		return user;
	}
	public void setUser(String user)
	{
		this.user=user;
	}
	public boolean isVar() {
		return var;
	}
	public void setVar(boolean var) {
		this.var = var;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}

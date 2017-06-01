package pl;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
 
@ManagedBean
public class ButtonView {
     
    public void buttonActionName() {
        addMessage("Contact name changed");
    }
    public void buttonActionPhone() {
        addMessage("Contact phone added");
    }
    public void buttonActionPhone1() {
        addMessage("Contact phone deleted");
    }
    public void buttonActionEmail1() {
        addMessage("Contact email deleted");
    }
    public void buttonActionEmail2() {
        addMessage("Contact email added");
    }
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
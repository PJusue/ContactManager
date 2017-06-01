package pl;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import ezvcard.VCard;
import ezvcard.property.Email;
import ezvcard.property.Telephone;

@ManagedBean
@RequestScoped
// This manageBean is used to print all the contact data
public class ShowContact {

	public List<Email> getEmails(VCard user) {

		return user.getEmails();
	}

	public String getName(VCard user) {
		return user.getFormattedName().getValue();
	}

	public List<Telephone> getPhone(VCard user) {
		return user.getTelephoneNumbers();
	}




}
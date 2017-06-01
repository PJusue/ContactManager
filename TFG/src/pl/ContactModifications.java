package pl;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import bl.GetAllTheVCardsAssociatedToAName;
import bl.GetVCardFileName;
import bl.WriteOnAVCardFile;
import ezvcard.VCard;
import ezvcard.parameter.EmailType;
import ezvcard.parameter.TelephoneType;
import ezvcard.property.Email;
import ezvcard.property.StructuredName;
import ezvcard.property.Telephone;
import ezvcard.property.Uid;
import ezvcard.property.VCardProperty;

import java.util.List;

@ManagedBean
@RequestScoped
// This manageBean allows the user doing modification in the editable contacts of our server.
public class ContactModifications {

	private VCard vcard;
	// ****************************
	private String name;
	private String email;
	private String emailType;
	private String number;
	private String numberType;
	@EJB
	private GetVCardFileName ejb;
	@EJB
	private WriteOnAVCardFile ejb1;
	@EJB
	private GetAllTheVCardsAssociatedToAName ejb2;

	public void addNewContact(String name,String email,String emailType,String number,String numberType,String user,String description)
	{
		List<VCard> vcardList=ejb2.getVCardListAssociatedToAName(ejb.getFileName(user, description), user);
		VCard vcard=new VCard();
		StructuredName structuredName=new StructuredName();
		structuredName.setGiven(name);
		Uid uid=Uid.random();
		vcard.setUid(uid);
		vcard.setStructuredName(structuredName);
		vcard.setFormattedName(name);
		vcard.addEmail(email, EmailType.get(emailType));
		vcard.addTelephoneNumber(number,TelephoneType.get(numberType));
		vcardList.add(vcard);
		ejb1.WriteOnAEspecificFile(ejb.getFileName(user, description), user,
				vcardList);
		
	}
	public void deleteContact(VCard contact,String user,String description)
	{
		List<VCard> vcardList=ejb2.getVCardListAssociatedToAName(ejb.getFileName(user, description), user);
		for (int i = 0; i < vcardList.size(); i++) {
			if(vcardList.get(i).getFormattedName().getValue().equalsIgnoreCase(contact.getFormattedName().getValue()))
				vcardList.remove(i);
		}
		ejb1.WriteOnAEspecificFile(ejb.getFileName(user, description), user,
				vcardList);
	}
	public void modifyName(String oldName, String name, String user,
			String description) {

		StructuredName structuredName = new StructuredName();
		List<VCard> vcardList = ejb2.getVCardListAssociatedToAName(
				ejb.getFileName(user, description), user);
		for (int i = 0; i < vcardList.size(); i++) {

			if (vcardList.get(i).getFormattedName().getValue().equals(oldName)) {

				setVcard(vcardList.get(i));
				vcardList.get(i).setFormattedName(name);
				structuredName.setGiven(name);
				vcardList.get(i).setStructuredName(structuredName);

			}
		}
		ejb1.WriteOnAEspecificFile(ejb.getFileName(user, description), user,
				vcardList);

	}
	
	public void addANewEmail(String newEmail, VCard vcard, String user,
			String description, String emailType) {
		int contador=1;
		List<VCard> vcardList = ejb2.getVCardListAssociatedToAName(
				ejb.getFileName(user, description), user);
		for (int i = 0; i < vcardList.size(); i++) {
			contador++;
		}
		for (int i = 0; i < vcardList.size(); i++) {
			if (vcardList.get(i).getFormattedName().getValue()
					.equalsIgnoreCase(vcard.getFormattedName().getValue())) {
				vcardList.get(i).addEmail(newEmail, EmailType.get(emailType));
				setVcard(vcardList.get(i));
			}
		}
		
		ejb1.WriteOnAEspecificFile(ejb.getFileName(user, description), user,
				vcardList);
	}

	public void addNewPhoneNumber(String newNumber,String telephoneType, VCard vcard,String user,String description)
	{
		List<VCard> vcardList=ejb2.getVCardListAssociatedToAName(ejb.getFileName(user, description), user);
		
		for (int i = 0; i < vcardList.size(); i++) {
			if(vcardList.get(i).getFormattedName().getValue().equalsIgnoreCase(vcard.getFormattedName().getValue()))
			{
				vcardList.get(i).addTelephoneNumber(newNumber, TelephoneType.get(telephoneType));
				setVcard(vcardList.get(i));
			}
		}
		ejb1.WriteOnAEspecificFile(ejb.getFileName(user, description), user, vcardList);
	
		

		
	}
	public void deleteAnEmail(Email email, String user, String description) {
		
		List<VCard> vcardList = ejb2.getVCardListAssociatedToAName(
				ejb.getFileName(user, description), user);

		for (int i = 0; i < vcardList.size(); i++) {

			for (int j = 0; j < vcardList.get(i).getEmails().size(); j++) {

				if (vcardList.get(i).getEmails().get(j).getValue()
						.equalsIgnoreCase(email.getValue())) {
					VCardProperty vc = new Email(email);
					vcardList.get(i).removeProperty(vc);
					setVcard(vcardList.get(i));
				}
			}
		}
		ejb1.WriteOnAEspecificFile(ejb.getFileName(user, description), user,
				vcardList);

	}

	public void deleteAPhoneNumber(Telephone phone,String user,String description)
	{
		List<VCard> vcardList=ejb2.getVCardListAssociatedToAName(ejb.getFileName(user, description), user);
		for (int i = 0; i < vcardList.size(); i++) {
			for (int j = 0; j < vcardList.get(i).getTelephoneNumbers().size(); j++) {
				if(vcardList.get(i).getTelephoneNumbers().get(j).equals(phone))
				{
					VCardProperty vc=new Telephone(phone);
					vcardList.get(i).removeProperty(vc);
					setVcard(vcardList.get(i));

				}
				
			}
		}
		ejb1.WriteOnAEspecificFile(ejb.getFileName(user, description), user,
				vcardList);
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailType() {
		return emailType;
	}

	public void setEmailType(String emailType) {
		this.emailType = emailType;
	}

	public VCard getVcard() {
		return vcard;
	}

	public void setVcard(VCard vcard) {
		this.vcard = vcard;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	public String getNumberType()
	{
		return  numberType;
	}
	public void setNumberType(String numberType)
	{
		this.numberType=numberType;
	}

	

}
package pl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ezvcard.VCard;
import bl.GetAllTheVCardsAssociatedToAName;
import bl.GetVCardFileName;

@ManagedBean
@SessionScoped
// This manageBean shows the list of contacts available to edit
public class ContactsDisplay {

	@EJB
	private GetVCardFileName ejb;
	@EJB
	private GetAllTheVCardsAssociatedToAName ejb1;
	private VCard vcard;
	private boolean var = false;

	public List<VCard> showConta(String user, String description) {

		List<VCard> vcardsList,vcardList2;
		
		vcardsList = ejb1.getVCardListAssociatedToAName(
				ejb.getFileName(user, description), user);
		vcardList2 = sortTheListOfVCards(vcardsList);
		return vcardList2;
	}

	public List<VCard> sortTheListOfVCards(List<VCard> vcardList) {
		List<String> vcardNames = new ArrayList<String>();
		List<VCard> vcardListOrdered = new ArrayList<VCard>();
		for (VCard vcard : vcardList) {

			vcardNames.add(vcard.getFormattedName().getValue());
			

		}
		Collections.sort(vcardNames);
		for (int i = 0; i < vcardNames.size(); i++) {
			for (int j = 0; j < vcardList.size(); j++) {

				if (vcardNames.get(i).equalsIgnoreCase(
						vcardList.get(j).getFormattedName().getValue())) {
					vcardListOrdered.add(vcardList.get(j));
				}
			}

		}
		
	
		return vcardListOrdered;
	}

	public VCard getVcard() {
		ContactModifications cm = new ContactModifications();
		if (cm.getVcard() != null)
			return cm.getVcard();
		else
			return vcard;
	}

	public void chooseVCard(VCard vcard) {
		this.vcard = vcard;
	}

	public boolean isVar() {
		return var;
	}

	public void setTrueVar() {
		setVar(true);
	}

	public void setVar(boolean var) {
		this.var = var;
	}
}
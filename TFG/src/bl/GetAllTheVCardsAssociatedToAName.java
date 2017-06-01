package bl;

import java.io.IOException;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;

import ezvcard.VCard;

@Singleton
@LocalBean
public class GetAllTheVCardsAssociatedToAName {

	public List<VCard> getVCardListAssociatedToAName(String filename,String user)
	{
		List<VCard> contacts = null;
		ReadFromVcardFile readObject=new ReadFromVcardFile();
		try {
			contacts=readObject.ReadFromAnEspecificFile(filename,user);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return contacts;
	}
}

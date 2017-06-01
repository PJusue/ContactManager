package bl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;

import ezvcard.Ezvcard;
import ezvcard.VCard;

@Singleton
@LocalBean
public class WriteOnAVCardFile {

	public void WriteOnAEspecificFile(String filename,String user,List<VCard> vcards)
	{
		File file =new File("/var/www/Radicale-1.1.1/collections/"+user+"/"+filename);
		try {
			Ezvcard.write(vcards).go(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

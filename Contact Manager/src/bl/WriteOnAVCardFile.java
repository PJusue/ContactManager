package bl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import ezvcard.Ezvcard;
import ezvcard.VCard;

public class WriteOnAVCardFile {

	public void WriteOnAEspecificFile(String filename,List<VCard> vcards)
	{
		File file =new File(filename);
		try {
			Ezvcard.write(vcards).go(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

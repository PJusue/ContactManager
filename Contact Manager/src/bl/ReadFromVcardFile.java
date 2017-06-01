package bl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import ezvcard.*;

public class ReadFromVcardFile {

	public List<VCard> ReadFromAnEspecificFile(String filename) {
		File file = new File(filename);
		List<VCard> vcards = null;
		try {
			vcards = Ezvcard.parse(file).all();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vcards;
	}

}

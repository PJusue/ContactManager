package bl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import ezvcard.*;

public class ReadFromVcardFile {

	public List<VCard> ReadFromAnEspecificFile(String filename,String user)
			throws IOException {
		filename="/var/www/Radicale-1.1.1/collections/"+user+"/"+filename;
		File file = new File(filename);
		List<VCard> vcards = Ezvcard.parse(file).all();
		return vcards;
	}

}

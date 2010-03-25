package testFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class WriteFile {

	public static void writeFileWithChars(String str, String filename, int num) throws IOException{
		File file = new File(filename);
		OutputStream os = new FileOutputStream(file);
		for(int i = 0; i < num; i++){
			os.write(str.getBytes());
		}
		os.close();
	}
	
	public static void main(String[] args) {
		try {
//			writeFileWithChars("¹þ", "D:/512ChineseChar.txt", 512);
		    writeFileWithChars("a", "D:/254Char.txt", 254);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

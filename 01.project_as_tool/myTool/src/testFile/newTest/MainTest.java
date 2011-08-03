/*
 * History
 *   Version     Update Date 　　        Updater　　　　       Details
 *   1.0.00  Aug 3, 2011      Cathy Wu        Create
 */

package testFile.newTest;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainTest {
	
	private String filename;
	private byte[] context;
	
	
	public MainTest(String filename) {
		this.filename = filename;
	}
	
	
	public String getFilename() {
		return filename;
	}

	public void readFileContext() throws IOException {
		DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(filename)));
		System.out.println(in.available() + " bytes in all.");
		this.context = new byte[in.available()];
		int i = 0;
		while (in.available() != 0) {
			this.context[i++] = in.readByte();
			System.out.println(this.context[i - 1]);
		}
		in.close();
	}
	
	public void writeFileContext() throws IOException {
		DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(filename + ".bak")));
		for (int i = 0; i < this.context.length; i++) {
			out.write(this.context[i]);
		}
		out.close();
	}


	public static void main(String[] args) {
		MainTest m = new MainTest("E:/project/JAVA/wlz-project/01.project_as_tool/myTool/src/testFile/newTest/tst_text1.txt");
		try {
			m.readFileContext();
			m.writeFileContext();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public byte[] getContext() {
		return context;
	}


	public void setContext(byte[] context) {
		this.context = context;
	}
}

package project_tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CopyProject {

    public void copyProject(String oldDir, String newDir) throws Exception {
        File fileDir = new File(oldDir);
        if (!fileDir.isDirectory()) {
            throw new Exception("Not a directory");
        }
        
        File newFileDir = new File(newDir);
        
        File[] childFiles = fileDir.listFiles();
        String temp = null;
        File tempFile = null;
        for (File file : childFiles) {
            if (file.isFile()) {
                temp = newFileDir.getPath() + "\\" + file.getName();
                tempFile = new File(temp);
                tempFile.createNewFile();
            }
        }
    }
    
    public static void main(String[] args) {
        File file = new File("D:\\projects\\offerme\\30_Coding\\my_trunk");
        System.out.println(file.getPath());
    }
}

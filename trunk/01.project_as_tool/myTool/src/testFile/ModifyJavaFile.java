/*
 * History
 *   Version     Update Date 　　        Updater　　　　       Details
 *   1.0.00  2010-5-6      Cathy Wu        Create
 */

package testFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class ModifyJavaFile {
    private final static String CON_STR = "import au.com.iwanttobuy.offerme.business.entity.UserSessionBean;";
    
    private static FileFilter fileFilter = new FileFilter(){
        public boolean accept(File pathname) {
            String tmp=pathname.getName().toLowerCase();
            if(pathname.isDirectory() || tmp.endsWith(".java")){
                return true;
            }
            return false;
        }
    };
    
    private File[] getFiles(String filePath) {
        File file = new File(filePath);
        if (!file.isDirectory()) {
            return null;
        }
        
        return file.listFiles(fileFilter);
    }
    
    public void delStr(File file, StringBuffer sbf) throws IOException {
        OutputStream os = new FileOutputStream(file);
        os.write(sbf.toString().getBytes());        
    }
    
    public List<File> rewriteFile2(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.isDirectory()) {
            return new ArrayList<File>();
        }
        File[] files = file.listFiles(fileFilter);
        
        List<File> list = new ArrayList<File>();
        
        for (File f : files) {
            if (f.isFile()) {
                list.add(f);
            }
            if (f.isDirectory()) {
                list.addAll(rewriteFile2(f.getPath()));
            }
        }
//        if (list.size() > 0) {
//            System.out.println(filePath + " exist " + list.size() + " files");
//        }
        
        return list;
    }
    
    
    public List<File> rewriteFile(String filePath) throws IOException {
        File file = new File(filePath);

        if (!file.isDirectory()) {
            return new ArrayList<File>();
        }
        
        List<File> files = rewriteFile2(filePath);
        System.out.println(files.size());
        
        List<File> tempList = new ArrayList<File>();

        FileReader fr = null;
        BufferedReader br = null;
        String temp = null;
        
        int count = 0;
        boolean flag = false;
        
        for (File f : files) {
            fr = new FileReader(f);
            br = new BufferedReader(fr);
            temp = null;
            count = 0;
            flag = false;
            StringBuffer sbf = new StringBuffer();
            do {
               temp = br.readLine();
               count++;
               if (count > 80 && !flag) {
                   break;
               }
               
               if (!flag && temp != null && temp.trim().contains(CON_STR)) {
                   System.out.println(f.getName());
                   tempList.add(f);
                   flag = true;
                   continue;
               }
               if (temp != null) {
                    sbf.append(temp);
                    sbf.append("\n");
               }
            } while (temp != null);
            if (flag) {
                System.out.println(sbf.length());
                delStr(f, sbf);
            }
            br.close();
            fr.close();
            System.gc();
        }
        return tempList;
    }
    
    public void testFileEncode(String filePath) {
        File[] files = getFiles(filePath);
        for (File f : files) {
            
        }
    }
    
    public static void main(String[] args) {
        try {
            ModifyJavaFile mf = new ModifyJavaFile();
            mf.rewriteFile("D:/projects/offerme/30_Coding/my_trunk/src/au/com/iwanttobuy/offerme");            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

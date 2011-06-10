/*
 * History
 *   Version     Update Date 　　        Updater　　　　       Details
 *   1.0.00  2011-4-29      Cathy Wu        Create
 */

package testFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FetchFileNames {

    private String filePath;
    
    public FetchFileNames(String path) {
        this.filePath = path;
    }
    
    public List<String> fetchNames() {
        File dir = new File(filePath);
        if (!dir.isDirectory()) {
            return null;
        }
        
        String[] arr = dir.list();
        
        List<String> list = new ArrayList<String>();
        
        for (String a : arr) {
            list.add(a);
        }
        
        Collections.sort(list);
        
        return list;
    }
    
    private List<String> getCompareFiles() throws IOException {
        String path = "D:/development_documents/BA/BA-321/datafeed/result/test.txt";
        File file = new File(path);
        if (!file.isFile()) {
            return null;
        }
        List<String> result = new ArrayList<String>();
        
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        
        String temp = null;
        while ((temp = br.readLine()) != null) {
            result.add(temp.trim());
        }
        br.close();
        fr.close();
        return result;
    }
    
    public List<String> filterNameList(List<String> oriNames) {
        List<String> result = new ArrayList<String>();
        for (String name : oriNames) {
            if (name.trim().endsWith("nopic.gif")) {
                result.add(name.substring(0, name.indexOf(" ")));
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        try {
            FetchFileNames f = new FetchFileNames("D:/development_documents/BA/BA-321/datafeed/result/morefun image");
            List<String> list = f.filterNameList(f.fetchNames());
            List<String> all = f.getCompareFiles();
            for (String name : all) {
                System.out.print(name + "\t");
                if (list.contains(name)) {
                    System.out.println();
                } else {
                    System.out.println("http://www.offerme.com.au/images/email/temp_images/" + name + ".jpg");
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

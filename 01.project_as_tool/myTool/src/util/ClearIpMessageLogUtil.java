/*
 * All Rights Reserved. Copyright(C) 2008 - 2010 OfferMe.com.au, Australia
 *
 * History
 *   Version     Update Date 　　        Updater　　　　       Details
 *   1.0.00  2010-3-25      Cathy Wu        Create
 */

package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClearIpMessageLogUtil {

    private final static String path = "C:/Program Files/IPMsg/ipmsg.log";
    
    public static void main(String[] args) {
        try {
            if (isNeedClear()) {
                copyLog();
                removeLog();
            } else {
                System.out.println("Needn't to clear IP Messenger log.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static String getFileAppendName() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm");
        return sdf.format(date);
    }
    
    public static boolean isNeedClear() {
        
        File file = new File(path);
                
        return (file.length() / 1024 > 400);
    }
    
    public static void copyLog() throws IOException {
        File file = new File(path);
        InputStream is = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        
        File file2 = new File(path + "." + getFileAppendName());
        OutputStream os = new FileOutputStream(file2, true);
        
        String data = null;
        StringBuffer sb = new StringBuffer();
        while((data = br.readLine())!=null)
        {
            sb.append(data);
            sb.append("\n\r");
        }
        
        os.write(sb.toString().getBytes());
        
        os.close();
        br.close();
        isr.close();
        is.close();
    }
    
    public static void removeLog() throws IOException {
        File file = new File(path);
        OutputStream os = new FileOutputStream(file);
        os.write("".getBytes());
        os.close();
    }
}

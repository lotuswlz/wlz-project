/*
 * All Rights Reserved. Copyright(C) 2008 OfferMe.com.au, Australia
 *
 * History
 *   Version     Update Date 　　        Updater　　　　       Details
 *   1.0.00  2009-12-11      Cathy Wu        Create
 */

package testNet;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;

import com.lowagie.text.pdf.codec.Base64.InputStream;

public class AnalysisData {

    private final static String LINK = "http://www.kaixin001.com/!rich/market.php";
    
    public static void fetchDataFromKaixinNet() {
        StringBuffer sb = new StringBuffer();
        try {
            URL url = new URL(LINK);
            Reader reader = new InputStreamReader(new BufferedInputStream(url.openStream()));
            int c;
            while ((c = reader.read()) != -1) {
                    sb.append((char) c);
            }
            reader.close();
            System.out.println(sb.toString());
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        fetchDataFromKaixinNet();
    }
}

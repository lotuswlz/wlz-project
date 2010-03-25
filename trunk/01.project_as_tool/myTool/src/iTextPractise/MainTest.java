/*
 * All Rights Reserved. Copyright(C) 2008 OfferMe.com.au, Australia
 *
 * History
 *   Version     Update Date 　　        Updater　　　　       Details
 *   1.0.00  2009-10-13      Cathy Wu        Create
 */

package iTextPractise;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.DocumentException;

public class MainTest {
    
    private final static String HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> <!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\"> <html xmlns=\"http://www.w3.org/1999/xhtml\"> <head> <title>My First Document</title> <style type=\"text/css\"> b { color: green; }</style> </head> <body>";
    private final static String FOOTER = "</body></html>";

    public static void t() throws IOException, DocumentException {
        String inputFile = "D:/development_documents/iText/a.html";
        String url = new File(inputFile).toURI().toURL().toString();
        String outputFile = "D:/development_documents/iText/firstdoc.pdf";
        OutputStream os = new FileOutputStream(outputFile);

        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocument(url);
        renderer.layout();
        renderer.createPDF(os);
        renderer.finishPDF();

        os.close();

    }
    
    public static void t2() throws IOException, DocumentException {
        String outputFile = "D:/development_documents/iText/firstdoc.pdf";
        OutputStream os = new FileOutputStream(outputFile);

        ITextRenderer renderer = new ITextRenderer();
        renderer
                .setDocumentFromString(HEADER + "<h1>MyTest 2</h1><hr/><h5>alsdfjsadlkfjasldkfj</h5><b>asdfasdfasdfasd</b>" + FOOTER);
        renderer.layout();
        renderer.createPDF(os);
        renderer.finishPDF();

        os.close();

    }
    
    public static void t3() throws IOException, DocumentException {
        String outputFile = "D:/development_documents/iText/firstdoc3.pdf";
        String charString = "<h1>MyTest 3</h1><hr/><h5>alsdfjsadlkfjasldkfj</h5><b>asdfasdfasdfasd</b>";
        OutputStream os = new FileOutputStream(outputFile);

        ITextRenderer renderer = new ITextRenderer();
        renderer
                .setDocumentFromString(HEADER + charString + FOOTER);
        renderer.layout();
        renderer.createPDF(os);
        renderer.finishPDF();

        os.close();

    }
    
    public static void t4() throws DocumentException, IOException {
        String filepath = "E:\\MyProject\\myTool\\src\\iTextPractise\\index.html";
        String url = new File(filepath).toURI().toURL().toString();
        String outputFile = "E:\\MyProject\\myTool\\src\\iTextPractise\\index.pdf";
        OutputStream os = new FileOutputStream(outputFile);

        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocument(url);
        renderer.layout();
        renderer.createPDF(os);
        renderer.finishPDF();

        os.close();
    }

    public static void main(String[] args) {
        try {
            t4();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}

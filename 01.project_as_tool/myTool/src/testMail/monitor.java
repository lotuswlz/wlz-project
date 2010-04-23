package testMail;

/*
 * Copyright 1996-2007 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Sun Microsystems nor the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;

import javax.mail.*;
import javax.mail.event.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import sun.misc.BASE64Decoder;

import com.sun.mail.imap.*;

// call: java -cp
// .;testMail\mail.jar;testMail\geronimo-activation_1.1_spec-1.0.2.jar
// testMail.monitor imap.gmail.com jasonzou@iwanttobuy.com.au passwordhere Inbox
// 10000
/* Monitors given mailbox for new mail */
// java -cp .;testMail\mail.jar;testMail\geronimo-activation_1.1_spec-1.0.2.jar  testMail.monitor imap.gmail.com jasonzou@iwanttobuy.com.au passwordhere Inbox 10000
//E:\MyProject\myTool\bin>java -cp .;testMail\mail.jar;testMail\geronimo-activation_1.1_spec-1.0.2.jar;testMail\velocity-1.5.jar;testMail\velocity-tools-generic-1.4.jar;testMail\commons-collections.jar;testMail\commons-logging-1.1.jar;testMail\commons-lang-2.4.jar testMail.monitor imap.gmail.com [email] [password] Inbox 1000

public class monitor {
    
    private final static String pathStr = "D:\\projects\\Maintenance\\email_jmrp\\practise\\";
    
    public static String generateFileName() {
        return generateFileName("", ".txt");
    }
        
    public static String generateFileName(String appendName, String extendName) {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String path = pathStr + sdf.format(d) + "\\";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
        return path + appendName + extendName;
    }

    public static void main(String argv[]) {
        if (argv.length != 5) {
            System.out
                    .println("Usage: monitor <host> <user> <password> <mbox> <freq>");
            System.exit(1);
        }
        System.out.println("\nTesting monitor\n");

        long timeout = System.currentTimeMillis();
        try {
            Properties props = System.getProperties();

            // set this session up to use SSL for IMAP connections
            props.setProperty("mail.imap.socketFactory.class",
                    "javax.net.ssl.SSLSocketFactory");
            // don't fallback to normal IMAP connections on failure.
            props.setProperty("mail.imap.socketFactory.fallback", "false");
            // use the simap port for imap/ssl connections.
            props.setProperty("mail.imap.socketFactory.port", "993");

            // Get a Session object
            Session session = Session.getInstance(props, null);
            // session.setDebug(true);

            // Get a Store object
            Store store = session.getStore("imap");

            // Connect
            store.connect(argv[0], argv[1], argv[2]);

            // Open a Folder
            Folder folder = store.getFolder(argv[3]);
            if (folder == null || !folder.exists()) {
                System.out.println("Invalid folder");
                System.exit(1);
            }

            folder.open(Folder.READ_WRITE);

            Flags q = new Flags(Flags.Flag.SEEN);
            // q.add("PROCESSED");
            javax.mail.search.FlagTerm flagTerm = new javax.mail.search.FlagTerm(
                    q, false);
            Message[] unreads = folder.search(flagTerm);
//            displayEmailAddress(unreads, false);
            List<String> emailList = getUserEmailListUnsubscribed(unreads);
            System.out.println(emailList.size());
            StringBuffer sb = new StringBuffer();
            if (emailList.size() > 0) {
                String email = null;
                sb.append("'");
                sb.append(emailList.get(0));
                sb.append("'");
                for (int i = 1; i < emailList.size(); i++) {
                    email = emailList.get(i);
                    sb.append(",'");
                    sb.append(email);
                    sb.append("'");
                }
            }
            String content = JmrpFromMailUtils.generateSqlFile(sb.toString());
            File file = new File(generateFileName("exec_file", ".sql"));
            OutputStream os = new FileOutputStream(file);
            os.write(content.getBytes());
            os.close();
            timeout = System.currentTimeMillis() - timeout;
            System.out.println("\n Use time " + (timeout / 1000) + " seconds.");

            // Add messageCountListener to listen for new messages
            folder.addMessageCountListener(new MessageCountAdapter() {
                public void messagesAdded(MessageCountEvent ev) {
                    Message[] msgs = ev.getMessages();
//                    try {
//                        displayEmailAddress(msgs, false);
                    List<String> emailList = getUserEmailListUnsubscribed(msgs);
                    for (String email : emailList) {
                        System.out.println("\t" + email);
                    }
                    
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
                }
            });

            // Check mail once in "freq" MILLIseconds
            int freq = Integer.parseInt(argv[4]);
            boolean supportsIdle = false;
            try {
                if (folder instanceof IMAPFolder) {
                    IMAPFolder f = (IMAPFolder) folder;
                    f.idle();
                    supportsIdle = true;
                }
            } catch (FolderClosedException fex) {
                throw fex;
            } catch (MessagingException mex) {
                supportsIdle = false;
            }
            for (;;) {
                if (supportsIdle && folder instanceof IMAPFolder) {
                    IMAPFolder f = (IMAPFolder) folder;
                    f.idle();
                    System.out.println("IDLE done");
                } else {
                    Thread.sleep(freq); // sleep for freq milliseconds

                    // This is to force the IMAP server to send us
                    // EXISTS notifications.
                    folder.getMessageCount();
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private static List<String> getUserEmailListUnsubscribed(Message[] messages) {
        if (messages == null || messages.length == 0) {
            return null;
        }
        try {
            List<String> list = new ArrayList<String>();
            
            String fileContent = null;
            String fileName = null;
            String senderAddress = null;
            String temp = null;
            int len = 0;
            int count = 0;
            String fmt = "";
            Map<String, Integer> map = new HashMap<String, Integer>();
            System.out.print("Fetching mails...... ");
            for (Message msg : messages) {
                senderAddress = ((InternetAddress[])msg.getFrom())[0].getAddress();
//                System.out.println("From: " + senderAddress + "; subject: " + msg.getSubject());
                if (msg.getSubject().startsWith("complaint about message from")
                        && senderAddress.trim().equals("staff@hotmail.com")) {
                    count = count + 1;
                    len = fmt.length();
                    for (int j = 0; j < len; j++) {
                        System.out.print("\b");
                    }
//                    percentage = nf.format(count / messages.length);
                    fmt = String.valueOf(count + " / " + messages.length);
                    System.out.print(fmt);
                    JmrpMailBean bean = getMailContent(msg, false);
                    if (bean == null) {
                        System.out.println("Error: bean is null");
                        continue;
                    }
                    fileName = writeToFile(msg);
                    fileContent = getFileContent(fileName);
//                    System.out.println(fileContent);
                    temp = "www.offerme.com.au/unsubscribe?mail="
                            /*+ bean.getTo()[0].trim() + "&mailType="*/;
                    if (fileContent.contains(temp)) {
//                        bean.setUnsubscribeSetting(getUnsubscribedMailType(
//                                fileContent, temp));
                        bean.setUnsubscribeSetting(1);
                    } else {
                        System.out.print("Message Number "
                                + msg.getMessageNumber()
                                + ": doesn\'t contains " + temp);
                    }
//                    System.out.println("\t" + bean.toString());
                    if (!bean.isNewsLetter()) {
                        System.out.println("\tIs Newsletter: " + bean.isNewsLetter());
                    }
                    if (bean.isNewsLetter()) {
                        msg.setFlag(Flags.Flag.SEEN, true);
                        for (int i = 0; i < bean.getTo().length; i++) {
                            
                            if (list.contains(bean.getTo()[i])) {
                                map.put(bean.getTo()[i], map.get(bean.getTo()[i]) + 1);
                                continue;
                            }
                            list.add(bean.getTo()[i]);
                            map.put(bean.getTo()[i], 1);
                        }
                    }
                }
            }
            if (map.size() > 0) {
                for (Iterator<Entry<String, Integer>> it = map.entrySet().iterator(); it.hasNext();) {
                    Entry<String, Integer> e = it.next();
                    System.out.println("From: " + e.getKey() + "\t" + e.getValue() + " times");
                }
                map.clear();
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static int getUnsubscribedMailType (String content, String str) {
        str = str.replaceAll("(\\.|\\\\\\|\\\\d|\\]|\\[|\\{|\\}|\\^|\\$|\\-|\\+|\\*|\\(|\\)|\\?)", "\\\\$1");
        String regex = "(" + str + "[\\d]+)";
        Pattern ptn = Pattern.compile(regex);
        Matcher mtc = ptn.matcher(content);
        String temp = null;
        while (mtc.find()) {
            temp = mtc.group();
        }
        if (temp == null) {
            return 0;
        }
        temp = temp.replaceAll(".*&mailType=([\\d]+)", "$1");
        return Integer.parseInt(temp);
    }
    
    private static JmrpMailBean getMailContent(Object message, boolean isChild) throws Exception {
        JmrpMailBean bean = null;
        Part part = (Part) message;
        String contenttype = part.getContentType();
        int nameindex = contenttype.indexOf("name");
        boolean conname = false;
        if (nameindex != -1)
            conname = true;
        if (part.isMimeType("text/plain") && !conname) {
            bean = new JmrpMailBean(((Message)message));
            try {
                bean.setContent((String) ((Message) message).getContent());
            } catch (Exception e) {
                System.out.println("Error for message: "
                        + ((Message) message).getMessageNumber()
                        + "; Exception: " + e.getMessage());
            }
        } else if (part.isMimeType("text/html") && !conname) {
            if (isChild) {
                IMAPNestedMessage im = (IMAPNestedMessage) message;
                InternetAddress[] address = (InternetAddress[]) im.getAllRecipients();
                String[] receivers = new String[address.length];
                for (int i = 0; i < address.length; i++) {
                    receivers[i] = address[i].getAddress();
                }
                bean = new JmrpMailBean(im.getMessageNumber(), im.getSubject(),
                        ((InternetAddress) im.getFrom()[0]).getAddress(),
                        receivers, im.getContentType(), 0);
            } else {
                bean = new JmrpMailBean((Message) message);
                bean.setContent((String) ((Message) message).getContent());
            }
        } else if (part.isMimeType("multipart/*")) {
//            Multipart multipart = (Multipart) part.getContent();
//            int counts = multipart.getCount();
//            for (int i = 0; i < counts; i++) {
//                bean = getMailContent(multipart.getBodyPart(i), false);
//            }
            bean = new JmrpMailBean((Message) message);
        } else if (part.isMimeType("message/rfc822")) {
            bean = getMailContent(part.getContent(), true);
        } else {
            System.out.println("other type: " + part.getContentType());
        }
        return bean;
    }
    
    private static String writeToFile(Message message) {
        String fileName = generateFileName("original_mail_" + message.getMessageNumber(), ".txt");
        try {
            File file = new File(fileName);
            OutputStream os = new FileOutputStream(file);
            MimeUtility.encode(os, "7bit");
            message.writeTo(os);
            MimeUtility.encode(os, "7bit");
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return fileName;
    }
    
    private static String getFileContent(String fileName) {
        try {
            StringBuffer sbf = new StringBuffer();
            File file = new File(fileName);
            InputStream is = new FileInputStream(file);
            
//            FileReader fr = null;
//            BufferedReader br = null;
//            
//            fr = new FileReader(file);
//            br = new BufferedReader(fr);
//            
//            String inputLine;
//
//            while ((inputLine = br.readLine()) != null) {
//              System.out.println(inputLine);
//            }
//            br.close();
            
            int tempbyte = 0;
            while ((tempbyte = is.read()) != -1) {
                sbf.append((char) tempbyte);
            }
            return sbf.toString();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    
    private static String getFileContent(Message message) {
        try {
            StringBuffer sbf = new StringBuffer();
            InputStream is = message.getInputStream();
            int tempbyte = 0;
            while ((tempbyte = is.read()) != -1) {
                sbf.append((char) tempbyte);
            }
            return sbf.toString();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    private static void displayEmailAddress(Message[] unreads, boolean flag)
            throws FileNotFoundException, IOException {
        if (unreads != null) {
            System.out.println("Got " + unreads.length
                    + " messages without processed");
            String fileName = generateFileName();
            File file = new File(fileName);
            OutputStream os = new FileOutputStream(file, true);
            InternetAddress [] address = null;
            String senderAddress = null;
            String subject = null;
            for (int i = 0; i < unreads.length; i++) {
                try {
                    address = (InternetAddress[]) unreads[i].getFrom();
                    if (address.length > 1) {
                        System.out.println("Error: one mail has " + address.length + "sender.");
                    }
                    senderAddress = address[0].getAddress();
                    subject = unreads[i].getSubject();
                    System.out.println("No: " + unreads[i].getMessageNumber()
                            + " From: " + senderAddress + " Subject: "
                            + subject);
                    if (senderAddress.trim().equals("staff@hotmail.com")
                            && subject.trim().startsWith(
                                    "complaint about message from")) {
                        
//                        unreads[i].writeTo(os);
//                        unreads[i].setFlag(Flags.Flag.SEEN, true);
                    }
                    ReceiveOneMail r = new ReceiveOneMail((MimeMessage) unreads[i]);
                    r.getMailContent(unreads[i], false);
//                    System.out.println(r.getBodyText());
                } catch (MessagingException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
            }
            os.close();
            System.out.println("email get complete...");
            if (flag) {
                JmrpFromMailUtils.exeCommand("grep \"To: \" \"" + fileName + "\"", null);
            }
        }
    }
    
    private static String getMailMessage(MimeMultipart m) throws MessagingException, IOException {
        return m.getBodyPart(0).getContent().toString();
    }

    private static void writeEmailToFile(Message[] unreads)
            throws FileNotFoundException, IOException {
        if (unreads != null) {
            System.out.println("Got " + unreads.length
                    + " messages without processed");
            File file = new File("mail.txt");
            OutputStream os = new FileOutputStream(file, true);
            for (int i = 0; i < unreads.length; i++) {
                try {
                    System.out.println("-----");
                    System.out.println("Message "
                            + unreads[i].getMessageNumber() + ":");
                    // unreads[i].writeTo(os);
                    System.out.println(unreads[i].getSubject());
                    InputStream is = ((javax.mail.internet.MimeMultipart) unreads[i]
                            .getContent()).getBodyPart(0).getInputStream();
                    int tempbyte;
                    StringBuffer sbf = new StringBuffer();
                    while ((tempbyte = is.read()) != -1) {
                        sbf.append((char) tempbyte);
                    }
                    System.out
                            .println("\n\n------------------------------------------------------------");
                    System.out.println(unreads[i].getFrom()[0].toString());
                    System.out.println(unreads[i].getContent().getClass()
                            .getName()
                            + ":"
                            + unreads[i].getContent().getClass()
                                    .getCanonicalName()
                            + ":"
                            + unreads[i].getContent().getClass()
                                    .getSimpleName());
                    sun.misc.BASE64Decoder b = new BASE64Decoder();
                    System.out
                            .println(((javax.mail.internet.MimeMultipart) unreads[i]
                                    .getContent()).toString());
                    byte[] bytes = ((javax.mail.internet.MimeMultipart) unreads[i]
                            .getContent()).getBodyPart(0).getContent()
                            .toString().getBytes("UTF-8");
                    os.write(bytes);
                    is.close();
                    /**
                     * unreads[i].setFlags(new Flags("PROCESSED"), true);
                     * unreads[i].setFlag(Flags.Flag.SEEN, true);
                     */
                } catch (IOException ioex) {
                    ioex.printStackTrace();
                } catch (MessagingException mex) {
                    mex.printStackTrace();
                }
            }
            os.flush();
            os.close();
            System.out.println("complete");
        }
    }
}

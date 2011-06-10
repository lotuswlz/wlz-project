/*
 * History
 *   Version     Update Date 　　        Updater　　　　       Details
 *   1.0.00  2010-9-16      Cathy Wu        Create
 */
package improsys;
import improsys.contactimport.common.AbstractImporter;
import improsys.contactimport.common.Contact;
import improsys.contactimport.common.Response;
import improsys.contactimport.common.util.ImportUtils;
import improsys.contactimport.common.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Collection;

import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
import org.apache.commons.ssl.HttpSecureProtocol;
import org.apache.commons.ssl.TrustMaterial;

public class GmailImporter extends AbstractImporter {

    
    private static final String ADDRESS_LOCATION = "http://mail.google.com/mail/?&view=cl&search=contacts&pnl=a&zx=";
    private static String startURL = "http://mail.google.com/mail/";
    private String aCurmBox ="";
    
    private String addressBookURL = "";
    private String previewURL = "";
    private String ik = "";
    private boolean isNew = false;
    
    public GmailImporter(String user,String pass)
    {
        super(user,pass);
        initializeHttpClient(false,CookiePolicy.RFC_2109);
    }
    
    public GmailImporter()
    {
    	super("","");
    	initializeHttpClient(false,CookiePolicy.RFC_2109);
    }
    
    public static void main(String[] args) throws Exception 
	{
    	args = new String[2];
    	args[0] = "cathywu1983@gmail.com";
    	args[1] = "4666521855";
        if (args.length != 2) 
        {
            System.err.println("Usage: java GmailAddressImporter <login> <password>");
            System.exit(1);
        }
        
        String user = args[0];
        String password = args[1];
        
         
        
        GmailImporter app = new GmailImporter(user,password);
        app.setDebugOutput(false);
        app.doLogin();
        if(app.isLoggedIn())
        {
            //get the contacts here..
            //app.getContacts();
            Contact[] cs = app.getContacts();
            for(int i = 0;i<cs.length;i++)
    		{
//    			System.out.println(StringUtils.padCharToRight(contacts[i].getName(),20,' ')+"\t"+StringUtils.padCharToLeft(contacts[i].getEmailAddress(),30,' '));
    			//try {
					System.out.println(new String(cs[i].getName() + "\t\t" + cs[i].getEmailAddress()));
				//} catch (UnsupportedEncodingException e) {
					//System.out.println(i + "; " + e.getMessage());
				//}
    		}
        }
        else
            System.out.println("Can not log in.");
   
    }
    
    public void doLogin()
    {
        Response r;
        boolean success = false;
        try
        {
            HttpSecureProtocol newprotocol = new HttpSecureProtocol();
            newprotocol.setCheckExpiry(false);
            newprotocol.setCheckCRL(false);
            newprotocol.setCheckHostname(false);
            newprotocol.setTrustMaterial(TrustMaterial.TRUST_ALL);
            ProtocolSocketFactory psf = newprotocol;
            Protocol trustHttps = new Protocol("https-expired", psf, 443);
            Protocol.registerProtocol("https-expired", trustHttps);
            
            r = this.getHttpResponse("http://www.gmail.com","",false,false,"",false, false);
            //r = this.getHttpResponse(startURL,"",false,false,"",false, false);
            // Obtain the referer
            String glax=r.getResponseBody().substring(r.getResponseBody().indexOf("<form id=\"gaia_loginform\""));
            glax=r.getResponseBody().substring(0,r.getResponseBody().indexOf("</form"));
            glax=glax.substring(glax.indexOf("name=\"GALX\""));
            glax=glax.substring(glax.indexOf("value=\"")+"value=\"".length());
            glax=glax.substring(0,glax.indexOf("\""));
            
            String dsh=r.getResponseBody().substring(r.getResponseBody().indexOf("<form id=\"gaia_loginform\""));
            dsh=r.getResponseBody().substring(0,r.getResponseBody().indexOf("</form"));
            dsh=dsh.substring(dsh.indexOf("name=\"dsh\""));
            dsh=dsh.substring(dsh.indexOf("value=\"")+"value=\"".length());
            dsh=dsh.substring(0,dsh.indexOf("\""));
            
            String conti=r.getResponseBody().substring(r.getResponseBody().indexOf("<form id=\"gaia_loginform\""));
            conti=r.getResponseBody().substring(0,r.getResponseBody().indexOf("</form"));
            conti=conti.substring(conti.indexOf("name=\"continue\""));
            conti=conti.substring(conti.indexOf("value=\"")+"value=\"".length());
            conti=conti.substring(0,conti.indexOf("\""));
            
            String referer = "https://www.google.com/accounts/ServiceLogin?service=mail&passive=true&rm=false&continue=http%3A%2F%2Fmail.google.com%2Fmail%2F%3Fui%3Dhtml%26zy%3Dl&bsv=zpwhtygjntrz&scc=1&ltmpl=default&ltmplcache=2";
            // Parse the next url and post parameter value
            String nextLocation = "https-expired://www.google.com/accounts/ServiceLoginAuth?service=mail";
            String paramString = "dsh="+dsh+"ltmpl=default&ltmplcache=2&continue="+conti+"&service=mail&rm=false&ltmpl=default&ltmpl=default&scc=1&GALX="+glax+"&Email="+user+"&Passwd="+password+"&rmShown=1&signIn=Sign in&asts=";
            r = getHttpResponse(nextLocation,referer,false,true,paramString,false, false);
            // referer = r.getLastURL();
            // nextLocation = StringUtils.parseValue(r.getResponseBody(),"location.replace(\"","\")",false);
            // nextLocation = nextLocation.replaceAll("\\x3d","=").replaceAll("\\x3d","=");
            nextLocation = "http://www.google.com.bd/accounts/SetSID?ssdc=1&sidt=ALWU2cveHtZjrfgj0UZDhvkIVKJXyQ8gefqO4vDA1gnEEZH6U6WgkcE8JuFThJA6Wh0EQ2qe49tntedEx%2Fn5UOEn9aRfh2XrGCsV0IuyKWGk6sIxNaJA7Yj2oYzxo1WxJ4MdgbpuUZuWKkoXJ217WflRw%2FcnX2UGaB1d7bTl%2BO0KXrexu%2BVg2hGDXPRMHXD5Rw2csnyuKYOox9l5IgP7aIlJAkriUuDiqGo6vcjo40seMiVthVSbHvgF3SA21UKRdXVDgjbHWq4%2FTv%2FdiYv9K6jFN0PatB3GgV0%2BNjeInFplTecftu0skMOdBnaSdaVxQ00tw%2FCJVmY%2FFEQf3WA2HX%2BDEfz%2BysF%2FICMhAGOSQKBXFoHFblGIknmLNeJR4LKFa%2FPAEhbkmu1X&continue=http%3A%2F%2Fwww.google.co.in%2Faccounts%2FSetSID%3Fssdc%3D1%26sidt%3DALWU2cv65nLbhJaiab3d89ZMLcXSGeEXpAlynIxGC3E0ddPsAMyfiqTzp261s3AXMhWQNWmmVoqs05UCOly6w0M%252BsbnHrNpPBKjX5i6bfkOJVg4yMnbQuvxUPDGdifzsgO5oKKRIpKgBaYfS4Z4AlxrbZQzZIXYxL8Knfpen1s1YOMNFCz8fxIi7PJ6E%252F9%252FacMjXBWqAlLvPyvMq2m9sABVDEu5%252Fj%252B6pKeS5uAYgSwT50fnGm2fUJHzuZQ%252FBtXeAb7IvQq7I1gdeGGTZLfLqQLWW8P1lNhBI%252FEoo9Egbt4GExcQe61adukAqPqLQtZ6vGs92NPedAJgUR1KrGX84S7MAaj1hshuQfhpOQLxSIziAHraxFFFMlOhuwIK1gT%252Fp7qLNGD2%252Fv4Dl%26continue%3Dhttps%253A%252F%252Fwww.google.com%252Faccounts%252FServiceLogin%253Fpassive%253Dtrue%2526go%253Dtrue%2526continue%253Dhttps%25253A%25252F%25252Fmail.google.com%25252Fmail%25252F%25253F%2526hl%253Den%2526service%253Dmail%2526ss%253D1%2526ltmpl%253Ddefault%2526fss%253D1";
            r = getHttpResponse(nextLocation, "", false, "", false);
            /*
            if(nextLocation=="")
            {
                success = true;
            }
            else
            {
                nextLocation = nextLocation.replaceAll("&amp;","&");
                if(nextLocation.indexOf("https")!=-1)
                 {
                        nextLocation=nextLocation.replaceAll("https","https-expired");
                 }
                r = getHttpResponse(nextLocation,referer,false,false,"",false, false);
               
                //if(r.getResponseBody().indexOf("Loading... Gmail")!=-1 || r.getResponseBody().indexOf("Loading... Google Mail")!=-1 || r.getResponseBody().indexOf(">Loading&hellip;<")!=-1)
               // {
                 if(r.getResponseBody().indexOf(">basic HTML view<")!=-1)
                {
                    if(r.getResponseBody().indexOf("ID_KEY") != -1)
                    {
                        this.ik = StringUtils.parseValueBetween(r.getResponseBody(),"ID_KEY",",");
                        this.ik = StringUtils.stripTags(this.ik.replaceAll(":\"",""));
                        this.ik = StringUtils.stripTags(this.ik.replaceAll("\"",""));
                        this.ik = StringUtils.stripTags(this.ik.replaceAll("'","")); 
                        this.ik = StringUtils.stripTags(this.ik.replaceAll(":","")); 
                        this.isNew = true;
                    }
                   // else
                    //{
                   //     this.ik = StringUtils.parseValueBetween(r.getResponseBody(),"ID_KEY:'","'"); 
                   //     this.isNew = true;
                   // }
                }
                else
                    this.isNew = false;
                    aCurmBox = StringUtils.parseValue(r.getResponseBody(),"ver=","\"",false);
                if(aCurmBox.length()>0)*/
              if(r.getResponseBody().indexOf("errormsg")==-1)
                 success = true;
              else
                 success= false;
             //}
        }
        catch (Exception e)
        {
            e.printStackTrace();
            success = false;
        }
        this.loggedIn = success;
    }

    /* (non-Javadoc)
     * @see AbstractImporter#getContacts()
     */
 public Contact[] getContacts()
    {
        addressBookURL = "";
        String postString="dojo.transport=xmlhttp&automatic=false&requests=[{\"hash\":\"\",\"action\":\"AutoCompleteContacts\"},{\"action\":\"GetScreenNames\"}]";
        Contact contacts[] = null;
        Contact demoContacts[] = null;
        ArrayList list = new ArrayList();
        addressBookURL = "https://mail.google.com/mail/contacts/data/contacts?thumb=true&groups=true&show=ALL&psort=Name&max=100000&out=js&rf=&jsx=true";
        Response r;
        try
        {
            HttpSecureProtocol newprotocol = new HttpSecureProtocol();
            newprotocol.setCheckExpiry(false);
            newprotocol.setCheckCRL(false);
            newprotocol.setCheckHostname(false);
            newprotocol.setTrustMaterial(TrustMaterial.TRUST_ALL);
            ProtocolSocketFactory psf = newprotocol;
            Protocol trustHttps = new Protocol("https-expired", psf, 443);
            Protocol.registerProtocol("https-expired", trustHttps);

            r = getHttpResponse(addressBookURL,"",false,true,"",false, false);
            
            String htmlresponce = r.getResponseBody();
            
            htmlresponce = htmlresponce.substring(htmlresponce.indexOf("\"Contacts\":")+"\"Contacts\":".length());
            htmlresponce = htmlresponce.substring(0,htmlresponce.indexOf("\"Groups\":[{\"Count\":"));
            System.out.println(TranCharset.getEncoding(htmlresponce));
            htmlresponce = new String(htmlresponce.getBytes("UTF-8"));
            System.out.println(htmlresponce);
            list.addAll(parseAddresses(htmlresponce));
           
            Collections.sort(list);
            contacts = ImportUtils.getDemoContacts(list);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        
        
        
        return contacts;
    }
    
    private Collection parseAddresses(String response)
    {
        ArrayList list = new ArrayList();
        Character ch=new Character((char)1);
       
        List tmpList=StringUtils.StringToArrayList(response.split("\"Affinity\":"));//StringUtils.parseAllValueBetween(response,"addresses[addresses.length] = new context.ContactInfo(",");");
        tmpList.remove(0);
        Iterator i = tmpList.iterator();
        
        while(i.hasNext())
        {
            list.add(getContact((String)i.next()));
            
        }
        return list;
    }
    
    private Contact getContact(String ceString)
    {
        Contact c = new Contact();
        
        
        String fName="";
        String Name="";
        String sName1="";
        String sName2="";
        String eMail="";
     
        //get the email address
        if(ceString.indexOf("\"Address\":\"")!=-1)
        {
            eMail=ceString.substring(ceString.indexOf("\"Address\":\"")+"\"Address\":\"".length());
            eMail=eMail.substring(0,eMail.indexOf("\","));  
        }
        else
            eMail="";
      
//Extract name 
      
        if(ceString.indexOf("\"DisplayName\":\"")!=-1)
        {
            Name=ceString.substring(ceString.indexOf("\"DisplayName\":\"")+"\"DisplayName\":\"".length());
            Name=Name.substring(0,Name.indexOf("\","));
        }
        else if(ceString.indexOf("\"Name\":\"")!=-1)
        {
           Name=ceString.substring(ceString.indexOf("\"Name\":\"")+"\"Name\":\"".length());
           Name = Name.replaceAll("}","");
           Name=Name.substring(0,Name.indexOf("\",")); 
        }
        else
        {
         Name="";    
        }
        
      if(Name==""&& eMail!="")
        {
          Name=eMail.substring(0,eMail.indexOf("@"));
        }
      if(Name!="" || eMail!="") 
        {
         c.setEmailAddress(eMail);   
         c.setName(Name);   
         }
      if(debugOutput)
            System.out.println(c.toString());
        return c;

    }
    
}

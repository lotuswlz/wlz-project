/**
* @ ContactImporter.java
* @author Shihan,Sadik, Sumon  contact: info@improsys.com
* @history
*          created  : Improsys : Date : 08-01-2006
* @version 1.0
*
* Copyright Improsys.
*
* All rights reserved.
*
* This software is the confidential and proprietary information
* of Improsys. ("Confidential Information").
* You shall not disclose such Confidential Information and shall use
* it only in accordance with the terms of the license agreement
* you entered into with Improsys.
*/
import improsys.contactimport.common.Contact;
import improsys.contactimport.common.Importer;
import improsys.contactimport.common.util.StringUtils;
import improsys.contactimport.fastmail.FastmailAddressImporter;
import improsys.contactimport.in.InAddressImporter;
import improsys.contactimport.paracalls.ParacallsAddressImporter;
import improsys.contactimport.rediff.RediffMailAddressImporter;
import improsys.contactimport.indiatimes.IndiatimesAddressImporter;
import improsys.contactimport.rambler.RamblerAddressImporter;


/**
 * @author Shihan
 */
public class ContactImporter implements Importer
{
    private String type ="";
    private Importer importer = null;
    private String user = "",password="";
    
    
    public static void main(String args[])
    {
    	if(args.length != 2)
    	{
    		System.out.println("Usage: java ContactImporter email_addresss password");
    		System.exit(0);
    	}

    	String tokens[] = args[0].split("@");
    	if(tokens.length!=2)
    	{
    		System.out.println("Invalid email address provided.");
    		System.exit(0);
    	}
    	String type = tokens[1];
    	String email = args[0];
    	
    	if(type.toLowerCase().equals("in.com")){
                email = tokens[0];
                type = "IN";
        }else if(type.toLowerCase().equals("paracalls.com") ||
                type.toLowerCase().equals("paracalls") ){
                email = tokens[0];
                type = "PARACALLS";
        }else if(type.toLowerCase().equals("fastmail.fm")){
                email = tokens[0];
                type = "FASTMAIL";
        }
        else if(type.toLowerCase().equals("rediff.com"))
        {
                email = tokens[0];
                type = "REDIFF";
        }
        else if(type.toLowerCase().equals("indiatimes.com"))
        {
                email = tokens[0];
                type = "INDIATIMES";
        }
        else if(type.toLowerCase().equals("rambler.ru"))
        {
                email = tokens[0];
                type = "RAMBLER";
        }
		else
		{
			System.out.println("Email address of domain \""+type+"\" not supported");
    		System.exit(0);
		}

    	String pass = args[1];
    	ContactImporter app = new ContactImporter(email,pass);
    	app.setType(type);
    	app.doLogin();
    	if(!app.isLoggedIn())
    	{
    		System.out.println("Can not login to " + tokens[1]+ " as " + tokens[0]);
    	}
    	else
    	{
    		Contact contacts[] = app.getContacts();
    		
    		System.out.println(StringUtils.padCharToRight("Name",20,' ')+"\t"+StringUtils.padCharToLeft("Email",30,' '));
    		System.out.println(StringUtils.padCharToRight("",20,'-')+"\t"+StringUtils.padCharToLeft("",30,'-'));
    		for(int i = 0;i<contacts.length;i++)
    		{
    			System.out.println(StringUtils.padCharToRight(contacts[i].getName(),20,' ')+"\t"+StringUtils.padCharToLeft(contacts[i].getEmailAddress(),30,' '));
    		}
    	}
    	
    }
    

    /**
     * @param user
     * @param pass
     * @param type
     */
    public ContactImporter(String user, String pass)
    {
        this.user = user;
        this.password = pass;
    }

    /* (non-Javadoc)
     * @see improsys.contactimport.common.Importer#getContacts()
     */
    public Contact[] getContacts()
    {
        // TODO Auto-generated method stub
        if(importer == null)
            throw new IllegalStateException("Login not performed...");
        
        return importer.getContacts();
    }

    /* (non-Javadoc)
     * @see improsys.contactimport.common.Importer#doLogin()
     */
    public void doLogin()
    {
        if(importer == null)
            throw new IllegalStateException("Invalid Address Book type specified");
        else
            importer.doLogin();
    }
    
    private Importer createImporter(String type)
    {
        if(type==null)
            return null;
        if(type.compareToIgnoreCase("IN")==0){
            return new InAddressImporter(this.user,this.password);
        }else if(type.compareToIgnoreCase("PARACALLS")==0){
            return new ParacallsAddressImporter(this.user,this.password);  
        }else if(type.compareToIgnoreCase("FASTMAIL")==0){
            return new FastmailAddressImporter(this.user,this.password);
        }else if(type.compareToIgnoreCase("REDIFF")==0){
            return new RediffMailAddressImporter(this.user,this.password);
        }else if(type.compareToIgnoreCase("INDIATIMES")==0){
           return new IndiatimesAddressImporter(this.user,this.password);
        }else if(type.compareToIgnoreCase("RAMBLER")==0){
            return new RamblerAddressImporter(this.user,this.password);
        }
        
        return null;
    }

    /**
     * @return Returns the type.
     */
    public String getType()
    {
        return type;
    }
    /**
     * @param type The type to set.
     */
    public void setType(String type)
    {
        if(this.type.compareToIgnoreCase(type)!=0)
        {
            this.type = type;
            importer = createImporter(type);
        }
    }
    
    
    /* (non-Javadoc)
     * @see improsys.contactimport.common.Importer#isLoggedIn()
     */
    public boolean isLoggedIn()
    {
        if(importer!=null)
            return importer.isLoggedIn();
        return false;
    }
}

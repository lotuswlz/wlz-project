############################## Requirement ########################################


This Address grab tools require minimum JDK 1.4.2 installed. 


############################## How this works #######################################

1. It gets input from user
2. Browse index page of fastmail/in/paracalls/rediff/indiatimes/rambler
3. Save cookies in session variable. 
4.Use those cookies from variable to browse inside pages. If inside page require any new cookie, then it also
  save those variable.
5.Parse data from Address pages

===========================Describtion of folders:=============================================

lib: Containing all jar files.

===========================Describtion of files:=============================================


index.jsp: For demonstrating how to use it in JSP Pages.
ContactImporter.java: For demonstrating how to use it in JAVA.


===========================Update process:=============================================

When an webmail change then we have to update our importer, we update that webmail's contact importer engine and send that to client. During each update we will send updated contact-importer.jar file.

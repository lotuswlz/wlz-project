/*
 * History
 *   Version     Update Date 　　        Updater　　　　       Details
 *   1.0.00  2010-6-9      Cathy Wu        Create
 */

package optdoc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

/**
 * For Offerme.
 * <pre>
 * 2010-Jun-9, Ken ask me to list all the action which requires user to access with login status.
 * So I write this program to read all the action-*.xml and applicationContext-*.xml, get all the return page and link.
 * 
 * </pre>
 * @since 2010-6-9
 * @author  Cathy Wu
 * @version  1.1.00
 */
public class PageInfoUtil {
	
	public static Map<String, List<String>> actionList = new HashMap<String, List<String>>();
	public static Map<String, String> actionLinkMap = new HashMap<String, String>();
	public static Map<String, String> appList = new HashMap<String, String>();
	
	private static FileFilter fileFilter1 = new FileFilter(){
        public boolean accept(File pathname) {
            String tmp=pathname.getName().toLowerCase();
            if(pathname.isDirectory() || (tmp.endsWith(".xml") && tmp.startsWith("action"))){
                return true;
            }
            return false;
        }
    };
    
    private static FileFilter fileFilter2 = new FileFilter(){
        public boolean accept(File pathname) {
            String tmp=pathname.getName().toLowerCase();
            if(pathname.isDirectory() || (tmp.endsWith(".xml") && tmp.startsWith("application"))){
                return true;
            }
            return false;
        }
    };

	public static void getActionPageXml(String path) {
		List<File> actionFiles = getAllFilesForAction(path, 1);
		List<File> applicationFiles = getAllFilesForAction(path, 2);
		
		appList.clear();
		actionLinkMap.clear();
		actionList.clear();
		
		for (File f : applicationFiles) {
			readXml2(f.getPath());
		}
		
		for (File f : actionFiles) {
			readXml(f.getPath());
		}
	}
	
	public static void readXml(String xmlPath) {
		try {
			SAXBuilder sb = new SAXBuilder();
			Document doc = sb.build(xmlPath);
			Element root = doc.getRootElement();
			List options = root.getChildren("package");
			if (options == null || options.size() == 0) {
	            return;
	        }
			
			String name = null;
			String cls = null;
			String str = null;
			List<String> ls = null;
	        for (Object obj : options) {
	            Element e = (Element) obj;
	            List temp = e.getChildren("action");
	            for (Object o : temp) {
	            	Element ce = (Element) o;
	            	name = ce.getAttributeValue("name");
	            	cls = ce.getAttributeValue("class");
	            	List tmp = ce.getChildren("result");
	            	ls = new ArrayList<String>();
	            	for (Object co : tmp) {
	            		Element cce = (Element) co;
	            		List p = cce.getChildren("param");
	            		if (p == null || p.size() == 0) {
	            			str = cce.getAttributeValue("name") + ":" + cce.getValue().trim() + ";";
	            		} else {
	            			for (Object x : p) {
	            				Element xe = (Element) x;
	            				if (xe.getAttributeValue("name").equals("location")) {
	            					str = cce.getAttributeValue("name") + ":" + xe.getValue().trim() + ";";
	            				}
	            			}
	            		}
	            		ls.add(str);
	            	}
	            	String appCls = appList.get(cls);
	            	actionList.put(appCls, ls);
	            	actionLinkMap.put(appCls, name);
	            }
	        }
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void readXml2(String xmlPath) {
		try {
			SAXBuilder sb = new SAXBuilder();
			Document doc = sb.build(xmlPath);
			Element root = doc.getRootElement();
			List options = root.getChildren("bean");
			options = root.getChildren();
			if (options == null || options.size() == 0) {
	            return;
	        }
			
			String name = null;
			String cls = null;
			String str = null;
			List<String> ls = null;
	        for (Object obj : options) {
	            Element e = (Element) obj;
	            name = e.getAttributeValue("id");
	            cls = e.getAttributeValue("class");
	            appList.put(name, cls);
	        }
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static List<File> getAllFilesForAction(String path, int type) {
		File dir = new File(path);
		
        if (!dir.isDirectory()) {
            return new ArrayList<File>();
        }
        File[] files = null;
        if (type == 1) {
        	files = dir.listFiles(fileFilter1);
        } else {
        	files = dir.listFiles(fileFilter2);
        }
		
        
        List<File> list = new ArrayList<File>();
        
        for (File f : files) {
            if (f.isFile()) {
                list.add(f);
            }
            if (f.isDirectory()) {
                list.addAll(getAllFilesForAction(f.getPath(), type));
            }
        }
        
        return list;
	}
	
	public static void main(String[] args) {
		getActionPageXml("D:/projects/offerme/30_Coding/branch/om_web/web/WEB-INF");
		//System.out.println(actionList.size());
		//System.out.println(actionList.get("au.com.iwanttobuy.offerme.presentation.action.account.SendMobileValidateCodeAction"));
		readPropertiesFile();
	}
	
	public static void readPropertiesFile() {
		try {
			String filepath = "D:/projects/offerme/30_Coding/branch/om_web/src/resources/accesspower.properties";
			File file = new File(filepath);
			
			FileReader fr = null;
			BufferedReader br = null;
					
			List<String> list = new ArrayList<String>();
			
			String temp = null;
			StringBuffer sbf = new StringBuffer();
			int count = 0;
			
				if(!file.isFile()){
					return;
				}
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				
				String[] arr = null;
				do{
					temp = br.readLine();
					if (temp == null || temp.trim().startsWith("#") || temp.trim().equals("") || !temp.contains("=")) {
						continue;
					}
			        arr = temp.split("=");
			        String url = actionLinkMap.get(arr[0].trim());
					if (url == null) {
						continue;
					}
			        // url
					url = url.replaceAll("_", "/");
					url = "/" + url;
					sbf.append(url + ",");
					// action name
					sbf.append(arr[0] + ",");
					// page
					List<String> ls = actionList.get(arr[0].trim());
					
					String t1 = "";
					String t2 = "";
					if (ls != null && ls.size() > 0) {
						for (String t : ls) {
							if (t.contains(".jsp")) {
								t1 = t;
							} else {
								t2 = t;
							}
						}
						sbf.append(t1);
						sbf.append(",");
						sbf.append(t2);
					}
					sbf.append(",");
					// accesspower
					sbf.append(arr[1] + "\n");
				} while(temp != null);
				
				br.close();
				fr.close();
				
				File f = new File("D:/development_documents/temp_data/self_" + System.currentTimeMillis() + ".csv");
				OutputStream os = new FileOutputStream(f);
				os.write(sbf.toString().getBytes());
				os.close();
				System.out.println(f.getPath());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void readSettingFile() {
		
	}
}

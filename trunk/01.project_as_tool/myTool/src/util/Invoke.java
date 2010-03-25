package util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Invoke {

	public static void displayAllMethod(String className) throws Exception{
		Class cls = Class.forName(className);
		Method[] ms = cls.getMethods();
		displayMethods(ms);
	}
	
	public static void displayMethods(Method[] m){
		for(int i = 0; i < m.length; i++){
			System.out.print(m[i].getModifiers() + " " + m[i].getName() + "(");
			Class[] ps = m[i].getParameterTypes();
			if(ps != null && ps.length > 0){
				String temp = ps[0].getName();
				for(int j = 1; j < ps.length; j++){
					temp += "," + ps[j].getName();
				}
				System.out.print(temp);
			}
			System.out.println(")");
		}
	}
	
	public static void main(String[] args) {
		try {
			displayAllMethod("java.util.TimeZone");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

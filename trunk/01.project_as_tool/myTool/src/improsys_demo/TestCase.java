package improsys_demo;

import improsys.contactimport.common.util.StringUtils;

public class TestCase {

	public static void main(String[] args) {
		TestCase t = new TestCase();
		System.out.println(t.getNameEmailFormated());
	}
	
	public String getNameEmailFormated() {
		Aaaa a = new Aaaa("lotuswlz", "lotuswlz@163.com");
		return StringUtils.padCharToRight(a.getName(),20,' ')+"\t"+StringUtils.padCharToLeft(a.getEmail(),30,' ');
	}
	
	private class Aaaa {
		
		private String name;
		private String email;
		
		public String getName() {
			return name;
		}
		
		public String getEmail() {
			return email;
		}
		
		public Aaaa(String name, String email) {
			this.name = name;
			this.email = email;
		}
	}
}

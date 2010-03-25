package testStr;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class MainTest {
	private String getFormulas(String opt, String formula, HashMap regexMap, int l){
//		String baseOpt = ("-".equals(opt) || "+".equals(opt)) ? opt + "?[^" + opt + "]+" : "[^" + opt + "]+";
		if(formula.matches("[^" + opt + "]+")){
			return formula;
		}
		HashMap map = new HashMap();
		String regex = "\\([^\\)|\\(]+\\)";
		Pattern pn = Pattern.compile(regex);
		Matcher m = pn.matcher(formula);
		String temp = "";
		String str = "";
		while(m.find()){
			temp = "X" + l++;
			str = changeRegex(m.group());
			formula = formula.replaceAll(str, temp);
			map.put(temp, m.group());
		}
		if(formula.matches(".+\\(.+\\).+")){
			formula = getFormulas(opt, formula,regexMap, l);
		}else {
			formula = changeFormula(opt, formula, regexMap);
			if(opt.equals("-") || opt.equals("/")){
				formula = getFormulas(opt, formula, regexMap, l);
			}
		}
		String strtemp = null;
		for(Iterator it = map.entrySet().iterator(); it.hasNext();){
			Map.Entry e = (Entry) it.next();
			strtemp = (String)e.getValue();
			if(!strtemp.matches("\\([^/|\\+|\\-|\\*]*" + opt + "+[^/|\\+|\\-|\\*]*\\)")){
				strtemp = changeFormula(opt, strtemp, regexMap);
			}
			e.setValue(strtemp);
			formula = formula.replaceAll((String)e.getKey(), (String)e.getValue());
		}
		return formula;
	}
//	把要替换的公式转化为可以使用的正则表达式
	private String changeRegex(String str){
		String regex = "[\\+|\\*|-|\\(|\\)]";
		String strnew = str;
		strnew = strnew.replaceAll(regex, "\\\\$0");
		return strnew;
	}
	public String changeFormulaByRegex(String formula){
		HashMap regexMap = new HashMap();
		regexMap.put("_/", "[^\\(|\\)|\\+|\\-|\\*|\\/]+");
		regexMap.put("_-", "[^\\(|\\)|\\+|\\/|\\*|\\-]+");
		regexMap.put("_\\*", "[^\\(|\\)|\\+|\\-|\\/]+");
		regexMap.put("_\\+", "[^\\(|\\)|\\/|\\-|\\*]+");
		formula = dealNumber("-", formula);
		String f = getFormulas("/", formula, regexMap, 0);
		f = getFormulas("\\*", f, regexMap, 0);
		f = getFormulas("-", f, regexMap, 0);
		return f;
	}
//	给除法两端加括号
	private String changeFormula(String opt, String str, HashMap regexMap){
		String temp = (String)regexMap.get("_" + opt);
		String regex = temp + opt + temp;
		//String regex = "[^\\(|\\)|\\+|\\-|\\*]+" + opt + "[^\\(|\\)|\\+|\\-|\\*]+";
		str = str.replaceAll(regex, "($0)");
		return str;
	}

	private String dealNumber(String symbol, String formula){
		String regex = "(\\-|\\+|\\*|/)(" + symbol + "\\d+(\\.\\d+)?)";
		formula = formula.replaceAll("^(" + symbol + "\\d+(\\.\\d+)?)", "($0)");
		return formula.replaceAll(regex, "$1($2)");
	}

	public static void main(String[] args) {
		MainTest t = new MainTest();
		System.out.println(t.changeFormulaByRegex("(-100*-2)+10/2"));
	}
}


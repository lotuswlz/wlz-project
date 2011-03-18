/*
 * History
 *   Version     Update Date ¡¡¡¡        Updater¡¡¡¡¡¡¡¡       Details
 *   1.0.00  2010-7-5      Cathy Wu        Create
 */

package testTime;

public class ChineseTime {

	private static final String[] skys = { "¼×", "ÒÒ", "±û", "¶¡", "Îì", "¼º", "¸ý",
			"ÐÁ", "ÈÉ", "¹ï" };
	private static final String[] lands = { "×Ó", "³ó", "Òú", "Ã®", "³½", "ËÈ", "Îç",
			"Î´", "Éê", "ÓÏ", "Ðç", "º¥" };

	public String getYearByOffset(String original, int offset) {
		if (original.length() != 2) {
			return null;
		}
		
		if (!checkYear(original)) {
			System.out.println("wrong year");
			return null;
		}

		if (offset % 60 == 0) {
			return original;
		}
		
		if (offset < 0) {
			offset = 60 + (offset % 60);
		}

		String sky = original.substring(0, 1);
		String land = original.substring(1, 2);

		int skyIndex = getIndexOfSkys(sky);
		int landIndex = getIndexOfLands(land);

		skyIndex = ((offset + skyIndex) % skys.length);
		landIndex = ((offset + landIndex) % lands.length);

		return skys[skyIndex] + lands[landIndex];
	}

	private int getIndexOfSkys(String str) {
		if (str == null || str.length() != 1) {
			return -1;
		}
		for (int i = 0; i < skys.length; i++) {
			if (str.equals(skys[i])) {
				return i;
			}
		}

		return -1;
	}

	private int getIndexOfLands(String str) {
		if (str == null || str.length() != 1) {
			return -1;
		}
		for (int i = 0; i < lands.length; i++) {
			if (str.equals(lands[i])) {
				return i;
			}
		}

		return -1;
	}
	
	private boolean checkYear(String str) {
		if (str == null) {
			return false;
		}
		for (int i = 0; i < skys.length * lands.length; i++) {
			if (str.equals(skys[i%skys.length] + lands[i%lands.length])) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		ChineseTime ct = new ChineseTime();
		String yearName = "ÐÁ³ó";
		String year = ct.getYearByOffset(yearName, 15);
		if (ct.checkYear(year)) {
			System.out.println(year);
		} else {
			System.out.println("wrong year");
		}
	}
}

package myStudy;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudyBean {
	
	public static final int MAX_STUDY_COUNT = 300;
	public static final int FIRST_STUDY_COUNT = 150;
	public static final int[] orderArray = {2, 4, 7, 14, 28, 60, 90, 180};
	
	private List<WordBean> wordTaskList;

	public List<WordBean> getWordTaskList() {
		return wordTaskList;
	}

	public void setWordTaskList(List<WordBean> wordTaskList) {
		this.wordTaskList = wordTaskList;
	}
	
	public int[] calc(){
		int[] counts = new int[2];
		// if haven't study any words, can study 50
		if(wordTaskList == null || wordTaskList.size() == 0){
			counts[0] = FIRST_STUDY_COUNT;
			counts[1] = 0;
			return counts;
		}
		if(wordTaskList.size() >= MAX_STUDY_COUNT){
			counts[0] = 0;
			counts[1] = wordTaskList.size();
			return counts;
		}
		
		Calendar c = null;
		int count = 0;
		int minCount = 0;
		for(int i = 0; i < orderArray.length; i++){
			if(orderArray[i] >= 60){
				counts[1] = wordTaskList.size();
				counts[0] = getMinNum(new int[]{MAX_STUDY_COUNT - wordTaskList.size(), FIRST_STUDY_COUNT, MAX_STUDY_COUNT - minCount});
				break;
			}
			c = Calendar.getInstance();
			c.add(Calendar.DATE, orderArray[i]);
			count = calcStudyCountByDate(c.getTime());
			System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(c.getTime()) + ":" + count);
			if(count >= MAX_STUDY_COUNT){
				counts[0] = 0;
				counts[1] = wordTaskList.size();
				break;
			}else{
				minCount = minCount < count ? count : minCount;
			}
		}
		
		return counts;
	}
	
	public int calcStudyCountByDate(Date date){
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("20080826", 100);
		map.put("20080827", 50);
		map.put("20080828", 50);
		map.put("20080829", 50);
		map.put("20080830", 0);
		map.put("20080831", 50);
		map.put("20080901", 50);
		map.put("20080902", 0);
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		String temp = sf.format(date);
		if(map.get(temp) == null){
			return 0;
		}else{
			return map.get(temp);
		}
	}
	
	public static void main(String[] args) {
		StudyBean b = new StudyBean();
		List<WordBean> list = new ArrayList<WordBean>();
		for(int i = 0; i < 120; i++){
			list.add(new WordBean());
		}
		b.setWordTaskList(list);
		int[] counts = b.calc();
		System.out.println(counts[0] + ":" + counts[1]);
	}
	
	public int getMinNum(int[] counts){
		int min = counts[0];
		for(int i = 1; i < counts.length; i++){
			if(min > counts[i]){
				min = counts[i];
			}
		}
		System.out.println("min:" + min);
		return min;
	}
}

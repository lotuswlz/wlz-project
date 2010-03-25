package myStudy;

import java.util.Date;

public class WordBean {

	private String word;
	
	private String sound;
	
	private int power;
	
	private String description;
	
	private Date lastStudyDate;

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getSound() {
		return sound;
	}

	public void setSound(String sound) {
		this.sound = sound;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getLastStudyDate() {
		return lastStudyDate;
	}

	public void setLastStudyDate(Date lastStudyDate) {
		this.lastStudyDate = lastStudyDate;
	}
}

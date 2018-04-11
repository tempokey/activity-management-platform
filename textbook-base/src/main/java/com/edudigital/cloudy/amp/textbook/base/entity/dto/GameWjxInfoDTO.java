package com.edudigital.cloudy.amp.textbook.base.entity.dto;

import java.util.Date;

public class GameWjxInfoDTO extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3353638767460513264L;

	private int gameId;

	private int userId;

	private String name;

	private String school;

	private String gameName;

	private int type;

	private int gameGroupNo;

	private String url;

	private String teacher;

	private Date gameStartTime;

	private Date gameEndTime;

	private String rank;

	private String bestRank;

	private int score;

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getGameGroupNo() {
		return gameGroupNo;
	}

	public void setGameGroupNo(int gameGroupNo) {
		this.gameGroupNo = gameGroupNo;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public Date getGameStartTime() {
		return gameStartTime;
	}

	public void setGameStartTime(Date gameStartTime) {
		this.gameStartTime = gameStartTime;
	}

	public Date getGameEndTime() {
		return gameEndTime;
	}

	public void setGameEndTime(Date gameEndTime) {
		this.gameEndTime = gameEndTime;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getBestRank() {
		return bestRank;
	}

	public void setBestRank(String bestRank) {
		this.bestRank = bestRank;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}

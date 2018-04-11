package com.edudigital.cloudy.amp.textbook.base.entity.dto;

import java.util.Date;

public class GameWjxScoreDTO extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2648015643338102468L;

	private int id;

	private int gameId;

	private int userId;

	private int urgId;

	private String rank;

	private int score;

	private Date updateTime;

	private int flag;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public int getUrgId() {
		return urgId;
	}

	public void setUrgId(int urgId) {
		this.urgId = urgId;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

}

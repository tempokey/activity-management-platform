package com.edudigital.cloudy.amp.textbook.base.entity.ao;

import java.util.List;

/**
 * 问题Entity
 * 
 * @author chenzq
 * @date 2018/02/01
 */
public class QuestionAO extends BaseAO {
	private static final long serialVersionUID = 1L;
	// id
	private int id;
	// 问题
	private String question;
	// 分数
	private int score;
	// 类型
	private int type;
	// 节id
	private int chapterId;
	// 解析
	private String analysis;
	
	private List<AskAO> listAskAO;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getChapterId() {
		return chapterId;
	}

	public void setChapterId(int chapterId) {
		this.chapterId = chapterId;
	}

	public List<AskAO> getListAskAO() {
		return listAskAO;
	}

	public void setListAskAO(List<AskAO> listAskAO) {
		this.listAskAO = listAskAO;
	}

	public String getAnalysis() {
		return analysis;
	}

	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}

}

package com.edudigital.cloudy.amp.textbook.base.entity.ao;

/**
 * 答案Entity
 * 
 * @author chenzq
 * @date 2018/02/01
 */
public class AskAO extends BaseAO {
	
	private static final long serialVersionUID = 1L;
	// 内容
	private String option;
	// 答案
	private String answer;

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

}

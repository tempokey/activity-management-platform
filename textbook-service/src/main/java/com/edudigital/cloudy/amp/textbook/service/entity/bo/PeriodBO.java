package com.edudigital.cloudy.amp.textbook.service.entity.bo;

public class PeriodBO extends BaseBO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 编号
	private int periodCode;
	// 课时
	private String period;
	// 课时标记
	private int periodStamp;

	public int getPeriodCode() {
		return periodCode;
	}

	public void setPeriodCode(int periodCode) {
		this.periodCode = periodCode;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public int getPeriodStamp() {
		return periodStamp;
	}

	public void setPeriodStamp(int periodStamp) {
		this.periodStamp = periodStamp;
	}
}

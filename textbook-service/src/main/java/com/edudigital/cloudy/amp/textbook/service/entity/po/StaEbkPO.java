package com.edudigital.cloudy.amp.textbook.service.entity.po;

public class StaEbkPO {

	/**
	 * 统计的持久层
	 */
	// 下载量
	private int y;
	// 支付确认时间
	private String label;

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}

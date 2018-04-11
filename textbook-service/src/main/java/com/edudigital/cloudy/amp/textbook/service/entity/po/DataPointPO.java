package com.edudigital.cloudy.amp.textbook.service.entity.po;

import java.io.Serializable;

/**
 * 柱状图属性 DataPoint Entity
 * 
 * @author chenzq
 * @version 2017-9-27
 * 
 */
public class DataPointPO implements Serializable {

	private static final long serialVersionUID = 1L;

	// 横轴坐标
	private String label;
	// 纵轴坐标
	private int y;
	// 游标触碰图形提示
	private String toolTipContent;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getToolTipContent() {
		return toolTipContent;
	}

	public void setToolTipContent(String toolTipContent) {
		this.toolTipContent = toolTipContent;
	}
}

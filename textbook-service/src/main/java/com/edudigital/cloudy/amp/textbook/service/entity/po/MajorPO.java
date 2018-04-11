package com.edudigital.cloudy.amp.textbook.service.entity.po;

import java.util.Date;
import java.util.List;

public class MajorPO extends BasePO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	// 类别编码
	private String majorNum;
	// 系别专业
	private String major;
	// 类型1门类，2系，3专业
	private int type;
	//0是系，其他所属系
	private String parentNum;
	//	创建者
	private String createBy;
	//	更新者
	private String updateBy;
	// 创建时间
	private Date createTime;
	//更新时间
	private Date updateTime;
	//系
	private List<MajorPO> collegePOs;
	//专业
	private List<MajorPO> majorPOs;


	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getMajorNum() {
		return majorNum;
	}

	public void setMajorNum(String majorNum) {
		this.majorNum = majorNum;
	}

	public String getParentNum() {
		return parentNum;
	}

	public void setParentNum(String parentNum) {
		this.parentNum = parentNum;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public List<MajorPO> getMajorPOs() {
		return majorPOs;
	}

	public void setMajorPOs(List<MajorPO> majorPOs) {
		this.majorPOs = majorPOs;
	}

	public List<MajorPO> getCollegePOs() {
		return collegePOs;
	}

	public void setCollegePOs(List<MajorPO> collegePOs) {
		this.collegePOs = collegePOs;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


}

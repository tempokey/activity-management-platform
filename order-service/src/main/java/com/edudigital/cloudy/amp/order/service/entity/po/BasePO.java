package com.edudigital.cloudy.amp.order.service.entity.po;

import java.io.Serializable;
import java.util.Date;

import com.edudigital.cloudy.amp.util.base.util.CodeUtils;

/**
 * 
 * @author Tempo
 * @date 2017年7月21日 上午9:34:31
 *
 */
public abstract class BasePO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BasePO() {
		this.setUuId(CodeUtils.randomUUID());
	}

	private String uuId;

	private String createBy;

	private Date createDate;

	private String updateBy;

	private Date updateDate;

	private String remarks;

	private String delFlag;

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getUuId() {
		return uuId;
	}

	public void setUuId(String uuId) {
		this.uuId = uuId;
	}

}

package com.edudigital.cloudy.amp.camp.base.entity;

/****
 * 比赛项目种类
 * 
 * @author Yangs
 *
 */
public class MatchItemDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;

	// 主键
	private int id;
	// 赛项名称
	private String name;

	private int status;

	private int itemOrder;

	private int flag;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getItemOrder() {
		return itemOrder;
	}

	public void setItemOrder(int itemOrder) {
		this.itemOrder = itemOrder;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

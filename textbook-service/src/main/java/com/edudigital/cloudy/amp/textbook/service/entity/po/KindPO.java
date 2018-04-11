package com.edudigital.cloudy.amp.textbook.service.entity.po;

/**
 * 
 * @author Tempo
 * @date 2017年9月20日 上午11:19:26
 *
 */
public class KindPO extends BasePO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int kindId;

	private String kind;

	public int getKindId() {
		return kindId;
	}

	public void setKindId(int kindId) {
		this.kindId = kindId;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

}

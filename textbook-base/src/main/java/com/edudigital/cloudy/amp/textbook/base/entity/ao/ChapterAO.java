package com.edudigital.cloudy.amp.textbook.base.entity.ao;

import java.util.List;


public class ChapterAO extends BaseAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int contextId;
	
	private String bookId;

	private int bookCode;

	private String book;

	private String chapter;
	
	private int parentId;
	
	private List<SectionAO> sections;

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public int getBookCode() {
		return bookCode;
	}

	public void setBookCode(int bookCode) {
		this.bookCode = bookCode;
	}

	public String getBook() {
		return book;
	}

	public void setBook(String book) {
		this.book = book;
	}

	public String getChapter() {
		return chapter;
	}

	public void setChapter(String chapter) {
		this.chapter = chapter;
	}

	public List<SectionAO> getSections() {
		return sections;
	}

	public void setSections(List<SectionAO> sections) {
		this.sections = sections;
	}

	public int getContextId() {
		return contextId;
	}

	public void setContextId(int contextId) {
		this.contextId = contextId;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}	

}

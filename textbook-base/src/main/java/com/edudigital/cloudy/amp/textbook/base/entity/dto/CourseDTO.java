package com.edudigital.cloudy.amp.textbook.base.entity.dto;

public class CourseDTO extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 课程编号
	private int courseCode;
	// 课程名称
	private String course;

	public int getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(int courseCode) {
		this.courseCode = courseCode;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

}

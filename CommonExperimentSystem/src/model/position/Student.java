package model.position;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import model.Course;
import model.Experiment;
import model.User;
import model.UserType;

public class Student extends Position implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public UserType getUserType() {
		// TODO Auto-generated method stub
		return UserType.STUDENT;
	}

	@Override
	public void applyCourse(Course course) {
		// TODO Auto-generated method stub
		if(appliedCourses == null){
			appliedCourses = new LinkedHashSet<Course>();
		}
		course.addApplicationNum();
		appliedCourses.add(course);
	}

	@Override
	public void removeAppliedCourse(Course course){
		if(appliedCourses != null){
			appliedCourses.remove(course);
		}
	}
	
	@Override
	public Set<Course> getAppliedCourses() {
		// TODO Auto-generated method stub
		if(appliedCourses == null){
			appliedCourses = new LinkedHashSet<Course>();
		}
		return appliedCourses;
	}

	@Override
	public void setAppliedCourses(Set<Course> appliedCourses) {
		// TODO Auto-generated method stub
		this.appliedCourses = appliedCourses;
	}

	@Override
	public void submitReport(Experiment experiment, String reportLink) {
		// TODO Auto-generated method stub
		experiment.addReportLink(reportLink);
	}

	@Override
	public void createCourse(User user, Course course) {
		// TODO Auto-generated method stub
		
	}
	
	public void removeCreatedCourse(Course course){
		
	}
	
	@Override
	public Set<Course> getCreatedCourses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCreatedCourses(Set<Course> createdCourses) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createExperiment(Course course, Experiment experiment) {
		// TODO Auto-generated method stub
		
	}

}

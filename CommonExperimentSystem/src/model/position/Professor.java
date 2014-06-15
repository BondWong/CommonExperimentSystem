package model.position;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import utils.UserType;
import model.Course;
import model.Experiment;
import model.User;

public class Professor extends Position implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public UserType getUserType() {
		// TODO Auto-generated method stub
		return UserType.PROFESSOR;
	}

	@Override
	public void applyCourse(Course course, String id, String name) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void removeAppliedCourse(Course course){
		
	}

	@Override
	public Set<Course> getAppliedCourses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAppliedCourses(Set<Course> appliedCourses) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void submitReport(Experiment Experiment, String reportLink, String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createCourse(User user, Course course) {
		// TODO Auto-generated method stub
		if(this.createdCourses == null){
			this.createdCourses = new LinkedHashSet<Course>();
		}
		course.setOwner(user);
		this.createdCourses.add(course);
	}
	
	public void removeCreatedCourse(Course course){
		if(this.createdCourses != null){
			this.createdCourses.remove(course);
		}
	}
	
	@Override
	public Set<Course> getCreatedCourses() {
		// TODO Auto-generated method stub
		return this.createdCourses;
	}

	@Override
	public void setCreatedCourses(Set<Course> createdCourses) {
		// TODO Auto-generated method stub
		this.createdCourses = createdCourses;
	}

	@Override
	public void createExperiment(Course course, Experiment experiment) {
		// TODO Auto-generated method stub
		experiment.setCourseId(course.getId());
		course.addExperiment(experiment);
	}

	@Override
	public Set<Long> getReportedExpIds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setReportedExpIds(Set<Long> reportedIds) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addReportedExpId(Long id) {
		// TODO Auto-generated method stub
		
	}

}

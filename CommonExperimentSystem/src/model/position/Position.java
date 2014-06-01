package model.position;

import java.util.Set;

import model.Course;
import model.Experiment;
import model.User;
import model.UserType;

public abstract class Position {
	protected Set<Course> appliedCourses;
	protected Set<Course> createdCourses;
	protected Set<Long> reportExpIDs;
	
	public abstract UserType getUserType();
	
	public abstract void applyCourse(Course course);
	public abstract void removeAppliedCourse(Course course);
	public abstract Set<Course> getAppliedCourses();
	public abstract void setAppliedCourses(Set<Course> appliedCourses);
	public abstract void submitReport(Experiment Experiment, String reportLink, String id);
	public abstract Set<Long> getReportedExpIds();
	public abstract void setReportedExpIds(Set<Long> reportedIds);
	public abstract void addReportedExpId(Long id);
	
	public abstract void createCourse(User user, Course course);
	public abstract void removeCreatedCourse(Course course);
	public abstract Set<Course> getCreatedCourses();
	public abstract void setCreatedCourses(Set<Course> createdCourses);
	public abstract void createExperiment(Course course, Experiment experiment);
}

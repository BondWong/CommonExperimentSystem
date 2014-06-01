package model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.persistence.Version;

import model.position.Admin;
import model.position.Position;
import model.position.Professor;
import model.position.Student;

@Entity
@Access(AccessType.FIELD)
@NamedQueries(value = { @NamedQuery(name = "User.getById", query = "SELECT u FROM User u WHERE u.id = ?1"),
		 @NamedQuery(name = "User.getByUserType", query = "SELECT u FROM User u WHERE u.userType = ?1"), 
		 @NamedQuery(name = "User.getByIdAndPassword", query = "SELECT u FROM User u WHERE u.id = ?1 AND u.password = ?2"), 
		 @NamedQuery(name = "User.deleteById", query = "DELETE FROM User u WHERE u.id = ?1"), 
		 @NamedQuery(name = "User.getAllClassifications", query = "SELECT DISTINCT u.classification FROM User u"), 
		 @NamedQuery(name = "User.getByClassification", query = "SELECT u FROM User u WHERE u.classification = ?1"),
		 @NamedQuery(name = "User.deleteByClassification", query = "DELETE FROM User u WHERE u.classification = ?1")})
public class User implements Serializable{
	
	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	@Version
	private Integer version;
	private String name;
	private String password;
	private String classification;
	
	@Transient
	private Position position;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}
	
	@Access(AccessType.PROPERTY)
	@Enumerated(EnumType.STRING)
	public UserType getUserType(){
		return this.position.getUserType();
	}
	
	public void setUserType(UserType userType){
		if(userType.equals(UserType.STUDENT)){
			this.position = new Student();
		} else if(userType.equals(UserType.PROFESSOR)){
			this.position = new Professor();
		} else{
			this.position = new Admin();
		}
	}
	
	public void sendLetter(User user, Letter letter){
		letter.setOwner(this);
		letter.setReceiver(user);
	}
	
	public void applyCourse(Course course){
		this.position.applyCourse(course);
	}
	
	public void removeAppliedCourse(Course course){
		this.position.removeAppliedCourse(course);
	}
	
	@Access(AccessType.PROPERTY)
	@OneToMany(cascade={CascadeType.MERGE}, fetch=FetchType.LAZY, orphanRemoval=true)
	@JoinTable(name="USER_APPLIEDCOURSES",
	joinColumns=@JoinColumn(name="USER_ID"),
	inverseJoinColumns=@JoinColumn(name="APPLIEDCOURSE_ID"))
	public Set<Course> getAppliedCourses(){
		return this.position.getAppliedCourses();
	}
	
	public void setAppliedCourses(Set<Course> courses){
		this.position.setAppliedCourses(courses);
	}
	
	public void submitReport(Experiment experiment, String link){
		this.position.submitReport(experiment, link, this.id);
		this.position.addReportedExpId(experiment.getId());
	}
	
	@Access(AccessType.PROPERTY)
	@ElementCollection
	public Set<Long> getReportedExpIds() {
		return this.position.getReportedExpIds();
	}

	public void setReportedExpIds(Set<Long> reportedExpIds) {
		this.position.setReportedExpIds(reportedExpIds);
	}

	public void createCourse(Course course) {
		this.position.createCourse(this, course);
	}
	
	public void removeCreatedCourse(Course course) {
		this.position.removeCreatedCourse(course);
	}
	
	public void createExperiment(Course course, Experiment experiment) {
		this.position.createExperiment(course, experiment);
	}
	
	@Access(AccessType.PROPERTY)
	@OneToMany(cascade={CascadeType.MERGE}, fetch=FetchType.LAZY, orphanRemoval=true,mappedBy="owner")
	public Set<Course> getCreatedCourses() {
		return this.position.getCreatedCourses();
	}
	
	public void setCreatedCourses(Set<Course> courses) {
		this.position.setCreatedCourses(courses);
	}
	
	public boolean equals(Object object){
		if(object instanceof User){
			User other = (User) object;
			if(other.getId()!=null&&other.getId().equals(this.id)){
				return true;
			}
		}
		
		return false;
	}
	
	public int hashCode(){
		if(this.id==null)
			return super.hashCode();
		return this.id.hashCode();
	}
}

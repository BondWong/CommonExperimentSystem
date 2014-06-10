package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.persistence.Version;

@Entity
@Access(AccessType.FIELD)
@NamedQueries(value = { @NamedQuery(name = "Course.getById", query = "SELECT c FROM Course c WHERE c.id = ?1"), 
		@NamedQuery(name = "Course.getAll", query = "SELECT c FROM Course c"), 
		@NamedQuery(name = "Course.getOpen", query = "SELECT c FROM Course c WHERE c.isOpen = 1 AND c NOT IN (SELECT uac FROM User u JOIN u.appliedCourses uac WHERE u.id = ?1)"),
		@NamedQuery(name = "Course.getByUserID", query = "SELECT c FROM Course c WHERE c IN (SELECT uac FROM User u JOIN u.appliedCourses uac WHERE u.id = ?1)")})
public class Course implements Serializable{
	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;
	@Version
	private Integer version;
	private String name;
	private String duration;
	private String major;
	private String classTime;
	private int applicationNum;
	private boolean isOpen;
	private String description;
	@OneToOne
	private User owner;
	@OneToMany(cascade={CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval=true, fetch=FetchType.EAGER)
	private Set<Experiment> experiments;
	@ElementCollection
	private List<String> applicantNames;
	@ElementCollection
	private Set<String> applicantIDs;
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDuration() {
		return this.duration;
	}
	
	public void setDuration(String duration) {
		this.duration = duration;
	}
	
	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getClassTime() {
		return classTime;
	}
	
	public void setClassTime(String classTime) {
		this.classTime = classTime;
	}
	
	public void addApplicationNum() {
		this.applicationNum ++;
	}
	
	public int getApplicationNum() {
		return applicationNum;
	}
	
	public void setApplicationNum(int applicationNum) {
		this.applicationNum = applicationNum;
	}
	
	public boolean isOpen() {
		return isOpen;
	}
	
	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public User getOwner() {
		return owner;
	}
	
	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	public void addExperiment(Experiment experiment){
		if(this.experiments == null){
			this.experiments = new LinkedHashSet<Experiment>();
		}
		this.experiments.add(experiment);
	}
	
	public void removeExperiment(Experiment experiment){
		if(this.experiments != null){
			this.experiments.remove(experiment);
		}
	}
	
	public Set<Experiment> getExperiments() {
		if(this.experiments == null){
			this.experiments = new LinkedHashSet<Experiment>();
		}
		return experiments;
	}
	
	public void setExperiments(Set<Experiment> experiments) {
		this.experiments = experiments;
	}
	
	public List<String> getApplicantNames() {
		if(this.applicantNames == null)
			this.applicantNames = new ArrayList<String>();
		return applicantNames;
	}

	public void setApplicantNames(List<String> applicantNames) {
		this.applicantNames = applicantNames;
	}
	
	public void addApplicantName(String name) {
		if(this.applicantNames == null)
			this.applicantNames = new ArrayList<String>();
		this.applicantNames.add(name);
	}

	public Set<String> getApplicantIDs() {
		if(this.applicantIDs==null)
			this.applicantIDs = new LinkedHashSet<String>();
		return applicantIDs;
	}

	public void setApplicantIDs(Set<String> applicantIDs) {
		this.applicantIDs = applicantIDs;
	}
	
	public void addApplicationID(String applicantID) {
		if(this.applicantIDs==null)
			this.applicantIDs = new LinkedHashSet<String>();
		this.applicantIDs.add(applicantID);
	}
	
	public boolean equals(Object object){
		if(object instanceof Course){
			Course other = (Course) object;
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

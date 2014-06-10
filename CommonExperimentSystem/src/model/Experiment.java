package model;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;
import javax.persistence.Version;

@Entity
@Access(AccessType.FIELD)
@NamedQueries(value = { @NamedQuery(name = "Experiment.getAll", query = "SELECT e FROM Experiment e"),
		@NamedQuery(name = "Experiment.getById", query = "SELECT e FROM Experiment e WHERE e.id = ?1"),
		@NamedQuery(name = "Experiment.getByCourseId", query = "SELECT e FROM Experiment e WHERE e.courseId = ?1"),
		@NamedQuery(name = "Experiment.deleteById", query = "DELETE FROM Experiment e WHERE e.id = ?1"),
		@NamedQuery(name = "Experiment.getReports", query = "SELECT r FROM Experiment e JOIN e.reportLinks r WHERE e.id = ?1"),
		@NamedQuery(name = "Experiment.getAllReportLinks", query = "SELECT rl FROM Experiment e JOIN e.reportLinks rl")})
public class Experiment implements Serializable{
	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;
	@Transient
	private String stringId;
	@Version
	private Integer version;
	private String name;
	private Long courseId;
	private String type;
	private String duration;
	private String purpose;
	private String demand;
	private String description;
	@ElementCollection
	private Set<String> reportLinks;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStringId() {
		return this.id.toString();
	}
	
	public void setStringId() {
		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	
	public String getType(){
		return this.type;
	}
	
	public void setType(String type){
		this.type = type;
	}
	
	public String getDuration(){
		return this.duration;
	}
	
	public void setDuration(String duration){
		this.duration = duration;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getDemand() {
		return demand;
	}

	public void setDemand(String demand) {
		this.demand = demand;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public void addReportLink(String link) {
		if(this.reportLinks == null){
			this.reportLinks = new LinkedHashSet<String>();
		}
		this.reportLinks.add(link);
	}
	
	public Set<String> getReportLinks() {
		if(this.reportLinks == null){
			this.reportLinks = new LinkedHashSet<String>();
		}
		return reportLinks;
	}

	public void setReportLinks(Set<String> reportLinks) {
		this.reportLinks = reportLinks;
	}
	
	public boolean equals(Object object){
		if(object instanceof Experiment){
			Experiment other = (Experiment) object;
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

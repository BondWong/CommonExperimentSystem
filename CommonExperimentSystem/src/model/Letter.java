package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

@Entity
@Access(AccessType.FIELD)
@NamedQueries(value = { @NamedQuery(name = "Letter.getUnread",
query = "SELECT l FROM Letter l WHERE l.receiver.id = ?1 AND l.isRead = false"), 
@NamedQuery(name = "Letter.getAll", query = "SELECT l FROM Letter l")})
public class Letter implements Serializable{
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
	private String content;
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	private boolean isRead;
	
	@OneToOne
	private User owner;
	@OneToOne
	private User receiver;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isRead() {
		return isRead;
	}

	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}

	public User getOwner() {
		return owner;
	}
	
	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	public User getReceiver() {
		return receiver;
	}
	
	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}
	
	public boolean equals(Object object){
		if(object instanceof Letter){
			Letter other = (Letter) object;
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

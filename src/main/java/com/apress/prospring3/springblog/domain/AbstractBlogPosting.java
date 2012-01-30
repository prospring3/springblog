/**
 * Created on Nov 1, 2011
 */
package com.apress.prospring3.springblog.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import org.springframework.data.domain.Auditable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

/**
 * @author Clarence
 *
 */
@MappedSuperclass
@Audited
public abstract class AbstractBlogPosting implements BlogPosting, Auditable<String, Long>, Serializable {

	protected Long id;	
    protected String subject;
    protected String body;
    protected DateTime postDate;
    protected String createdBy;
    protected DateTime createdDate;
    protected String lastModifiedBy;
    protected DateTime lastModifiedDate;	
    protected int version;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID")
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}	
	
	@JsonIgnore
	@NotEmpty(message="{validation.posting.body.NotEmpty.message}")
	@Size(min=10, max=2000, message="{validation.posting.body.Size.message}")
	@Column(name = "BODY")
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

	@Column(name = "POST_DATE")
	@Type(type="org.joda.time.contrib.hibernate.PersistentDateTime")
	@DateTimeFormat(iso=ISO.DATE)
    public DateTime getPostDate() {
        return postDate;
    }
	
	@Transient
	public String getPostDateString() {
		return org.joda.time.format.DateTimeFormat.forPattern("yyyy-MM-dd").print(postDate);
	}	

    public void setPostDate(DateTime postDate) {
        this.postDate = postDate;
    }

	@NotEmpty(message="{validation.posting.subject.NotEmpty.message}")
	@Size(min=10, max=50, message="{validation.posting.subject.Size.message}")    
	@Column(name = "SUBJECT")
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

	@Column(name = "CREATED_BY")
	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "CREATED_DATE")
	@Type(type="org.joda.time.contrib.hibernate.PersistentDateTime")
	@DateTimeFormat(iso=ISO.DATE_TIME)
	public DateTime getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(DateTime createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "LAST_MODIFIED_BY")
	public String getLastModifiedBy() {
		return this.lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	@Column(name = "LAST_MODIFIED_DATE")
	@Type(type="org.joda.time.contrib.hibernate.PersistentDateTime")	
	@DateTimeFormat(iso=ISO.DATE_TIME)	
	public DateTime getLastModifiedDate() {
		return this.lastModifiedDate;
	}
	
	@Transient
	public String getLastModifiedDateString() {
		return org.joda.time.format.DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").print(lastModifiedDate);
	}	

	public void setLastModifiedDate(DateTime lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}    
    
	@Version
	@Column(name = "VERSION")
	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	} 
	
	@Transient
	public final boolean isNew() {
		if (id == null) {
			return true;
		} else {
			return false;
		}
	}		
	
}

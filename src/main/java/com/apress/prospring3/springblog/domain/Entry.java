/**
 * Created on Oct 21, 2011
 */
package com.apress.prospring3.springblog.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Clarence
 *
 */
@Entity
@Audited
@Table(name = "entry")
public class Entry extends AbstractBlogPosting implements Serializable {

    private static final int MAX_BODY_LENGTH = 80;
    private static final String THREE_DOTS = "...";	
	
	private String categoryId;
	private String subCategoryId;
	private Set<EntryAttachment> attachments = new HashSet<EntryAttachment>();
	private Set<Comment> comments = new HashSet<Comment>();

	public Entry() {
	}
	
	@Transient
    public String getShortBody() {
        if (body.length() <= MAX_BODY_LENGTH)
            return body;
        StringBuffer result = new StringBuffer(MAX_BODY_LENGTH + 3);
        result.append(body.substring(0, MAX_BODY_LENGTH));
        result.append(THREE_DOTS);

        return result.toString();
    }

	@NotEmpty
	@Column(name = "CATEGORY_ID")
	public String getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	@Column(name = "SUB_CATEGORY_ID")
	public String getSubCategoryId() {
		return this.subCategoryId;
	}

	public void setSubCategoryId(String subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	@JsonIgnore
	@NotAudited
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "entry", cascade = CascadeType.ALL)
	public Set<EntryAttachment> getAttachments() {
		return this.attachments;
	}
	
    public void setAttachments(Set<EntryAttachment> attachments) {
        this.attachments = attachments;
    }		

	@JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "entry", cascade = CascadeType.ALL)
	public Set<Comment> getComments() {
		return this.comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}
	
	public void addComment(Comment comment) {
		comment.setEntry(this);
		getComments().add(comment);
	}
	
	public void addAttachment(EntryAttachment attachment) {
		getAttachments().add(attachment);
	}	
	
	public String toString() {
		return "Entry id: " + id + " - subject: " + subject + " - category: " + categoryId
				+ " - post date: " + postDate
				+ " - created by: " + createdBy + " - created date: " + createdDate
				+ " - last modified by: " + lastModifiedBy + " - last modified date: " + lastModifiedDate
				+ " - version: " + version;
	}
}

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

/**
 * @author Clarence
 *
 */
@Entity
@Audited
@Table(name = "comment")
@NamedQueries({
	@NamedQuery(name="Comment.findReplyToByEntryId", 
                query="select distinct c.postBy from Comment c where c.entry.id = :entryId")
})
public class Comment extends AbstractBlogPosting implements Serializable {

	private Entry entry;
	private String replyTo;
	private String postBy;
	private Set<CommentAttachment> attachments = new HashSet<CommentAttachment>();

	public Comment() {
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ENTRY_ID")
	public Entry getEntry() {
		return this.entry;
	}

	public void setEntry(Entry entry) {
		this.entry = entry;
	}

	@Column(name = "REPLY_TO")
	public String getReplyTo() {
		return this.replyTo;
	}

	public void setReplyTo(String replyTo) {
		this.replyTo = replyTo;
	}

	@Column(name = "POST_BY")
	public String getPostBy() {
		return this.postBy;
	}

	public void setPostBy(String postBy) {
		this.postBy = postBy;
	}

	@NotAudited
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "comment", cascade = CascadeType.ALL)
	public Set<CommentAttachment> getAttachments() {
		return this.attachments;
	}

	public void setAttachments(Set<CommentAttachment> attachments) {
		this.attachments = attachments;
	}	
	
	public void addAttachment(CommentAttachment attachment) {
		getAttachments().add(attachment);
	}		
}

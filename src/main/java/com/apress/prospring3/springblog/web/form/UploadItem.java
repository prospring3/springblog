/**
 * Created on Dec 15, 2011
 */
package com.apress.prospring3.springblog.web.form;


/**
 * @author Clarence
 *
 */
public class UploadItem {

	private Long blogId;
	
	private Long commentId;
	
	private String uploadType;

	public Long getBlogId() {
		return blogId;
	}

	public void setBlogId(Long blogId) {
		this.blogId = blogId;
	}

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public String getUploadType() {
		return uploadType;
	}

	public void setUploadType(String uploadType) {
		this.uploadType = uploadType;
	}
	
}

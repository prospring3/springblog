/**
 * Created on Oct 21, 2011
 */
package com.apress.prospring3.springblog;

import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.apress.prospring3.springblog.domain.Comment;
import com.apress.prospring3.springblog.service.CommentService;

/**
 * @author Clarence
 *
 */
public class JpaCommentTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:jpa-app-context.xml");
		ctx.refresh();
		
		System.out.println("App context initialized successfully");
		
		CommentService commentService = ctx.getBean("commentService", CommentService.class);
		
		List<Comment> comments = commentService.findByEntryId(1l);
		
		System.out.println("No of comments: " + comments.size());
		
		for (Comment comment: comments) {
			System.out.println(comment.getPostBy());
		}
		
		List<String> replyTos = commentService.findReplyToByEntryId(3l);
		
		for (String replyTo: replyTos) {
			System.out.println(replyTo);
		}
	}

}

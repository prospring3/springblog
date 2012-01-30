/**
 * Created on Dec 12, 2011
 */
package com.apress.prospring3.springblog.web.blogapp.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.apress.prospring3.springblog.auditor.AuditorAwareBean;
import com.apress.prospring3.springblog.domain.Comment;
import com.apress.prospring3.springblog.domain.Entry;
import com.apress.prospring3.springblog.service.CommentService;
import com.apress.prospring3.springblog.service.EntryService;
import com.apress.prospring3.springblog.web.form.Message;
import com.apress.prospring3.springblog.web.util.UrlUtil;

/**
 * @author Clarence
 *
 */
@RequestMapping("/blogs/{blogid}/comments")
@Controller
public class CommentController {

	final Logger logger = LoggerFactory.getLogger(CommentController.class);	

	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private EntryService entryService;
	
	@Autowired
	private AuditorAwareBean auditorAwareBean;
	
	@RequestMapping(method = RequestMethod.POST)
    public String create(@Valid Comment comment, BindingResult bindingResult, @PathVariable("blogid") Long blogid, Model uiModel, 
    		HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale) {
		logger.info("Creating comment for entry id: " + blogid);
        if (bindingResult.hasErrors()) {
			uiModel.addAttribute("message", new Message("error", messageSource.getMessage("comment_save_fail", new Object[]{}, locale)));
            uiModel.addAttribute("comment", comment);
            populateSelectBox(uiModel, blogid);
            return "blogs/" + UrlUtil.encodeUrlPathSegment(blogid.toString(), httpServletRequest) + "/comments/create";
        }
        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("comment_save_success", new Object[]{}, locale)));
        
        Entry entry = entryService.findById(blogid);
        logger.info("Comment id: " + comment.getId());
        comment.setId(null);
        comment.setEntry(entry);
        commentService.save(comment);
        return "redirect:/blogs/" + UrlUtil.encodeUrlPathSegment(blogid.toString(), httpServletRequest);
    }

	@RequestMapping(params = "form", method = RequestMethod.GET)
    public String createForm(Model uiModel, @PathVariable("blogid") Long blogid, HttpServletRequest httpServletRequest) {
		Entry entry = entryService.findById(blogid);
        uiModel.addAttribute("entry", entry);
        
        Comment comment = new Comment();
		comment.setPostDate(new DateTime());
		comment.setPostBy(auditorAwareBean.getCurrentAuditor());
        uiModel.addAttribute("comment", comment);
        populateSelectBox(uiModel, blogid);
        return "blogs/" + UrlUtil.encodeUrlPathSegment(blogid.toString(), httpServletRequest) + "/comments/create";
    }	
	
	@RequestMapping(value = "/{id}", params = "form", method = RequestMethod.POST)
    public String update(@Valid Comment comment, BindingResult bindingResult, @PathVariable("blogid") Long blogid, Model uiModel, 
    		HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale) {
		logger.info("Updating comment");
        if (bindingResult.hasErrors()) {
        	uiModel.addAttribute("message", new Message("error", messageSource.getMessage("comment_save_fail", new Object[]{}, locale)));        	
            uiModel.addAttribute("comment", comment);
            populateSelectBox(uiModel, blogid);
            return "blogs/" + UrlUtil.encodeUrlPathSegment(blogid.toString(), httpServletRequest) + "/comments/update";
        }
        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("comment_save_success", new Object[]{}, locale)));        
        
        Entry entry = entryService.findById(blogid);
        comment.setEntry(entry);
        commentService.save(comment);
        return "redirect:/blogs/" + UrlUtil.encodeUrlPathSegment(blogid.toString(), httpServletRequest);
    }

	@RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") Long id, @PathVariable("blogid") Long blogid, Model uiModel, 
    		HttpServletRequest httpServletRequest) {
		logger.info("Update comment with id: " + id);
        uiModel.addAttribute("comment", commentService.findById(id));
        populateSelectBox(uiModel, blogid);
        return "blogs/" + UrlUtil.encodeUrlPathSegment(blogid.toString(), httpServletRequest) + "/comments/update";
	}	
	
	private void populateSelectBox(Model uiModel, Long entryId) {
		
		// Retrieve possible reply to for select
		List<String> replyTos = commentService.findReplyToByEntryId(entryId);
		uiModel.addAttribute("replyTos", replyTos);
		
	}
	
}

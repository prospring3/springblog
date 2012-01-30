/**
 * Created on Dec 15, 2011
 */
package com.apress.prospring3.springblog.web.blogapp.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.apress.prospring3.springblog.domain.Comment;
import com.apress.prospring3.springblog.domain.CommentAttachment;
import com.apress.prospring3.springblog.domain.Entry;
import com.apress.prospring3.springblog.domain.EntryAttachment;
import com.apress.prospring3.springblog.service.CommentService;
import com.apress.prospring3.springblog.service.EntryService;
import com.apress.prospring3.springblog.web.form.Message;
import com.apress.prospring3.springblog.web.form.UploadItem;
import com.apress.prospring3.springblog.web.util.UrlUtil;

/**
 * @author Clarence
 * 
 */
@RequestMapping("/blogs/fileupload")
@Controller
public class FileUploadController {

	final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

	@Autowired
	EntryService entryService;

	@Autowired
	CommentService commentService;

	@RequestMapping(method = RequestMethod.POST)
	public String processUpload(UploadItem uploadItem, BindingResult result,
			Model uiModel, HttpServletRequest request,
			HttpServletResponse response,
			RedirectAttributes redirectAttributes,
			@RequestParam("file") Part file) throws IOException {

		Long blogId = uploadItem.getBlogId();
		Long commentId = uploadItem.getCommentId();
		// MultipartFile file = uploadItem.getFileData();
		String uploadType = uploadItem.getUploadType();
		Message message;

		if (file.getSize() > 0) {
			if (uploadType.equalsIgnoreCase("entry")) {
				// Construct Attachment object
				EntryAttachment entryAttachment = new EntryAttachment();
				entryAttachment.setFileName(getFileName(file));
				entryAttachment.setFileData(IOUtils.toByteArray(file.getInputStream()));
				entryAttachment.setContentType(file.getContentType());
				
				Entry entry = entryService.findById(blogId);
				entryAttachment.setEntry(entry);
				entry.addAttachment(entryAttachment);
				entryService.save(entry);
			} else {
				// Construct Attachment object
				CommentAttachment commentAttachment = new CommentAttachment();
				commentAttachment.setFileName(getFileName(file));
				commentAttachment.setFileData(IOUtils.toByteArray(file.getInputStream()));
				commentAttachment.setContentType(file.getContentType());	
				
				Comment comment = commentService.findById(commentId);
				commentAttachment.setComment(comment);
				comment.addAttachment(commentAttachment);
				commentService.save(comment);
			}

			message = new Message("success", "File '" + getFileName(file)
					+ "' uploaded successfully");
			redirectAttributes.addFlashAttribute("message", message);
		} else {
			redirectAttributes.addFlashAttribute("validationResult", "error");
			message = new Message("error", "Please select a file for upload");
			redirectAttributes.addFlashAttribute("message", message);
		}

		return "redirect:/blogs/"
				+ UrlUtil.encodeUrlPathSegment(blogId.toString(), request);
	}

	private String getFileName(Part part) {
		String partHeader = part.getHeader("content-disposition");
		logger.info("Part Header = " + partHeader);
		for (String cd : part.getHeader("content-disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				return cd.substring(cd.indexOf('=') + 1).trim()
						.replace("\"", "");
			}
		}
		return null;
	}

}

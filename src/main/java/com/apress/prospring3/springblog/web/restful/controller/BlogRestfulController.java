/**
 * Created on Dec 12, 2011
 */
package com.apress.prospring3.springblog.web.restful.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apress.prospring3.springblog.domain.Entries;
import com.apress.prospring3.springblog.service.EntryService;

/**
 * @author Clarence
 *
 */
@Controller
@RequestMapping(value="/blog")
public class BlogRestfulController {

	final Logger logger = LoggerFactory.getLogger(BlogRestfulController.class);
	
	@Autowired
	private EntryService entryService;
	
	@RequestMapping(value = "/listdata", method = RequestMethod.GET)
	@ResponseBody
	public Entries listData() {
		return new Entries(entryService.findAll());
	}
	
}

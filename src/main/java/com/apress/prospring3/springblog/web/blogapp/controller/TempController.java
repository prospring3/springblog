/**
 * Created on Dec 18, 2011
 */
package com.apress.prospring3.springblog.web.blogapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Clarence
 *
 */
@RequestMapping("/temp")
@Controller
public class TempController {

	@RequestMapping(method = RequestMethod.GET)
	public String list(Model uiModel) {
		return "temp/index";
	}	
	
}

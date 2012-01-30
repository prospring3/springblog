/**
 * Created on Dec 9, 2011
 */
package com.apress.prospring3.springblog.web.blogapp.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.apress.prospring3.springblog.domain.Category;
import com.apress.prospring3.springblog.domain.Entry;
import com.apress.prospring3.springblog.domain.SearchCriteria;
import com.apress.prospring3.springblog.service.CategoryService;
import com.apress.prospring3.springblog.service.EntryService;
import com.apress.prospring3.springblog.web.form.EntryGrid;
import com.apress.prospring3.springblog.web.form.Message;
import com.apress.prospring3.springblog.web.form.UploadItem;
import com.apress.prospring3.springblog.web.util.UrlUtil;
import com.google.common.collect.Lists;

/**
 * @author Clarence
 *
 */
@RequestMapping("/blogs")
@Controller
public class EntryController {

	final Logger logger = LoggerFactory.getLogger(EntryController.class);	
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private EntryService entryService;
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model uiModel) {
		logger.info("Listing blog entries");
		
		List<Entry> entries = entryService.findAll();
		uiModel.addAttribute("entries", entries);
		
		logger.info("No. of blog entries: " + entries.size());
		
		return "blogs/list";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") Long id, Model uiModel) {
        Entry entry = entryService.findById(id);
		uiModel.addAttribute("entry", entry);
        UploadItem uploadItem = new UploadItem();
        uploadItem.setBlogId(entry.getId());
        uiModel.addAttribute("uploadItem", uploadItem);
        return "blogs/show";
    }	
	
	@RequestMapping(method = RequestMethod.POST)
    public String create(@Valid Entry entry, BindingResult bindingResult, Model uiModel, 
    		HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale) {
		logger.info("Creating entry");
        if (bindingResult.hasErrors()) {
			uiModel.addAttribute("message", new Message("error", messageSource.getMessage("entry_save_fail", new Object[]{}, locale)));
            uiModel.addAttribute("entry", entry);
            populateSelectBox(uiModel, entry);
            return "blogs/create";
        }
        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("entry_save_success", new Object[]{}, locale)));

        logger.info("Entry id: " + entry.getId());
        entryService.save(entry);
        return "redirect:/blogs/" + UrlUtil.encodeUrlPathSegment(entry.getId().toString(), httpServletRequest);
    }

	@PreAuthorize("isAuthenticated()")
	@RequestMapping(params = "form", method = RequestMethod.GET)
    public String createForm(Model uiModel) {
		Entry entry = new Entry();
        uiModel.addAttribute("entry", entry);
        populateSelectBox(uiModel, entry);
        return "blogs/create";
    }
	
	@RequestMapping(value = "/{id}", params = "form", method = RequestMethod.POST)
    public String update(@Valid Entry entry, BindingResult bindingResult, Model uiModel, 
    		HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale) {
		logger.info("Updating entry");
        if (bindingResult.hasErrors()) {
        	uiModel.addAttribute("message", new Message("error", messageSource.getMessage("entry_save_fail", new Object[]{}, locale)));        	
            uiModel.addAttribute("entry", entry);
            populateSelectBox(uiModel, entry);
            return "blogs/update";
        }
        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("entry_save_success", new Object[]{}, locale)));
        entryService.save(entry);
        return "redirect:/blogs/" + UrlUtil.encodeUrlPathSegment(entry.getId().toString(), httpServletRequest);
    }

	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("entry", entryService.findById(id));
        populateSelectBox(uiModel, entryService.findById(id));
        return "blogs/update";
	}
	
	/**
	 * Support pagination for front-end grid
	 * @param uiModel
	 * @return
	 */
	@RequestMapping(value = "/listgrid", method = RequestMethod.GET, produces="application/json")
	@ResponseBody
	public EntryGrid listGrid(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows,
			@RequestParam(value = "sidx", required = false) String sortBy,
			@RequestParam(value = "sord", required = false) String order,
			@RequestParam(value = "_search", required = false) boolean isSearch,
			@RequestParam(value = "subject", required = false) String subject,
			@RequestParam(value = "categoryId", required = false) String categoryId,
			@RequestParam(value = "fromPostDate", required = false) String fromPostDateString,
			@RequestParam(value = "toPostDate", required = false) String toPostDateString) {
		
		logger.info("Listing blog entries for grid with page: {}, rows: {}", page, rows);
		logger.info("Listing blog entries for grid with sort: {}, order: {}", sortBy, order);
		logger.info("Is search: {}", isSearch);
		logger.info("Search field subject: {}, categoryId: {}", subject, categoryId);
		logger.info("Search field from post date: {}, to post date: {}", fromPostDateString, toPostDateString);
		
		// Process search criteria
		DateTime fromPostDate;
		DateTime toPostDate;
		// subject
		if (subject == null) {
			subject = "%";
		} else {
			subject = "%" + subject + "%";
		}
		// category
		if (categoryId == null) {
			categoryId = "%";
		} else {
			categoryId = categoryId + "%";
		}
		// From post date
		if (fromPostDateString == null) {
			fromPostDate = DateTimeFormat.forPattern("yyyy-MM-dd").parseDateTime("1900-01-01");
		} else {
			fromPostDate = DateTimeFormat.forPattern("yyyy-MM-dd").parseDateTime(fromPostDateString);
		}
		// To post date
		if (toPostDateString == null) {
			toPostDate = DateTimeFormat.forPattern("yyyy-MM-dd").parseDateTime("2200-12-31");
		} else {
			toPostDate = DateTimeFormat.forPattern("yyyy-MM-dd").parseDateTime(toPostDateString);
		}	
		SearchCriteria searchCriteria = new SearchCriteria();
		searchCriteria.setSubject(subject);
		searchCriteria.setCategoryId(categoryId);
		searchCriteria.setFromPostDate(fromPostDate);
		searchCriteria.setToPostDate(toPostDate);
		
		// Process order by
		Sort sort = null;
		String orderBy = sortBy;
		if (orderBy != null && orderBy.equals("postDateString")) orderBy = "postDate";
		
		if (orderBy != null && order != null) {
			if (order.equals("desc")) {
				sort = new Sort(Sort.Direction.DESC, orderBy);
			} else
				sort = new Sort(Sort.Direction.ASC, orderBy);
		}
		
		// Constructs page request for current page
		// Note: page number for Spring Data JPA starts with 0, while jqGrid starts with 1
		PageRequest pageRequest = null;
		
		if (sort != null) {
			pageRequest = new PageRequest(page - 1, rows, sort);
		} else {
			pageRequest = new PageRequest(page - 1, rows);
		}
				
		//Page<Entry> entryPage = entryService.findAllByPage(pageRequest);
		Page<Entry> entryPage = entryService.findEntryByCriteria(searchCriteria, pageRequest);
		
		// Construct the grid data that will return as JSON data
		EntryGrid entryGrid = new EntryGrid();
		
		entryGrid.setCurrentPage(entryPage.getNumber() + 1);
		entryGrid.setTotalPages(entryPage.getTotalPages());
		entryGrid.setTotalRecords(entryPage.getTotalElements());
		entryGrid.setEntryData(Lists.newArrayList(entryPage.iterator()));
		
		return entryGrid;
	}	
	
	private void populateSelectBox(Model uiModel, Entry entry) {
		
        // Category codes
        List<Category> categories = categoryService.findAllParentCategory();
        uiModel.addAttribute("categories", categories);		
        
        // Sub Category codes
        if (entry.getCategoryId() != null) {
        	List<Category> subCategories = categoryService.findAllSubCategory(entry.getCategoryId());
        	uiModel.addAttribute("subCategories", subCategories);	
        }
		
	}
}

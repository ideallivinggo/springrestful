package com.how2java.springboot.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.how2java.springboot.dao.CategoryDAO;
import com.how2java.springboot.pojo.Category;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CategoryController {
	@Autowired CategoryDAO categoryDAO;


	@GetMapping("/category")
	public List<Category> listCategory(@RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
		start = start<0?0:start;
		Sort sort = new Sort(Sort.Direction.DESC, "id");
		Pageable pageable = new PageRequest(start, size, sort);
		Page<Category> page =categoryDAO.findAll(pageable);
		return page.getContent();
	}

	@GetMapping("/category/{id}")
	public Category getCategory(@PathVariable("id") int id) throws Exception {
		Category c= categoryDAO.getOne(id);
		System.out.println(c);
		return c;
	}
	@PutMapping("/category")
	public void addCategory(@RequestBody Category category) throws Exception {
		System.out.println("springboot接受到浏览器以JSON格式提交的数据："+category);
	}
	
   /* @GetMapping("/categories")
    public String listCategory(Model m,@RequestParam(value = "start", defaultValue = "0") int start,@RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
    	start = start<0?0:start;
    	Sort sort = new Sort(Sort.Direction.DESC, "id");
    	Pageable pageable = new PageRequest(start, size, sort);
    	Page<Category> page =categoryDAO.findAll(pageable);
    	m.addAttribute("page", page);
        return "listCategory";
    }

	@PostMapping("/categories")
    public String addCategory(@Valid Category c) throws Exception {
    	categoryDAO.save(c);
    	return "redirect:/categories";
    }
    @DeleteMapping("/categories/{id}")
    public String deleteCategory(Category c) throws Exception {
    	categoryDAO.delete(c);
    	return "redirect:/categories";
    }
    @PutMapping("/categories/{id}")
    public String updateCategory(Category c) throws Exception {
    	categoryDAO.save(c);
    	return "redirect:/categories";
    }
    @GetMapping("/categories/{id}")
    public String getCategory(@PathVariable("id") int id,Model m) throws Exception {
    	Category c= categoryDAO.getOne(id);
    	m.addAttribute("c", c);
    	return "editCategory";
    }*/
}


/*
 * Copyright (C) 2020 Andre Kessler (https://github.com/goblingift)
 * All rights reserved
 */
package gift.goblin.quizgenerator.controller;

import gift.goblin.quizgenerator.database.model.Category;
import gift.goblin.quizgenerator.database.repo.CategoryRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.util.StringUtils;

/**
 *
 * @author andre
 */
@Controller
public class CategoryController {
    
    @Autowired
    private BuildProperties buildProperties;
    
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    @GetMapping(value = {"/category"})
    public String renderAddCategoriesPage(Model model) {
        
        logger.info("User opened category-editing-menu.");
        
        model.addAttribute("newCategory", new Category());
        model.addAttribute("categories", loadCategories());
        model.addAttribute("build_artifact", buildProperties.getArtifact());
        model.addAttribute("build_version", buildProperties.getVersion());
        return "edit_categories";
    }
    
    public List<Category> loadCategories() {
        List<Category> allCategories = categoryRepository.findAll();
        logger.info("Found {} categories in database.", allCategories.size());
        return allCategories;
    }
    
    @PostMapping(value = "/category/add")
    public String addNewCategory(@ModelAttribute("newCategory") Category category, BindingResult bindingResult, Model model) {
        
        if (StringUtils.isEmptyOrWhitespace(category.getName())) {
            logger.warn("No category name was entered- dont create em.");
        } else {
            logger.info("Will add new category: {}", category);
            categoryRepository.save(category);
        }
        
        return renderAddCategoriesPage(model);
    }
    
    @GetMapping(value = "/category/delete/{id}")
    public String deleteCategory(@PathVariable("id") String id, Model model) {
        logger.info("Will delete category: {}", id);
        categoryRepository.deleteById(Integer.parseInt(id));
        
        return renderAddCategoriesPage(model);
    }
        
}

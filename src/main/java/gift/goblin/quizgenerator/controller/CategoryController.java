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
import org.springframework.web.bind.annotation.GetMapping;

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
    public String renderMainMenu(Model model) {
        
        logger.info("User opened category-menu.");
        
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
    
    
}

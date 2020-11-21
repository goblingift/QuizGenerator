/*
 * Copyright (C) 2020 Andre Kessler (https://github.com/goblingift)
 * All rights reserved
 */
package gift.goblin.quizgenerator.controller;

import gift.goblin.quizgenerator.database.repo.CategoryRepository;
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
public class GameController {
    
    @Autowired
    private BuildProperties buildProperties;
    
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    @GetMapping(value = {"/play"})
    public String renderAddCategoriesPage(Model model) {
        
        logger.info("User started game.");
        
        model.addAttribute("build_artifact", buildProperties.getArtifact());
        model.addAttribute("build_version", buildProperties.getVersion());
        return "edit_categories";
    }
    
        
}

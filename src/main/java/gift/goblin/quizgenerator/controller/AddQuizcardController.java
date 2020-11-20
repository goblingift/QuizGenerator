/*
 * Copyright (C) 2020 Andre Kessler (https://github.com/goblingift)
 * All rights reserved
 */
package gift.goblin.quizgenerator.controller;

import gift.goblin.quizgenerator.database.model.Category;
import gift.goblin.quizgenerator.database.model.Quizcard;
import gift.goblin.quizgenerator.database.repo.CategoryRepository;
import gift.goblin.quizgenerator.database.repo.QuizcardRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author andre
 */
@Controller
public class AddQuizcardController {
    
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    CategoryRepository categoryRepository;
    
    @Autowired
    QuizcardRepository quizcardRepository;
    
    @GetMapping(value = {"/quizcard/create"})
    public String renderMainMenu(Model model) {
        
        logger.info("User opened add-quizcard.");
        
        List<Category> categories = categoryRepository.findAll();
        logger.debug("Found {} categories- will add them to available category dropdown.", categories.size());
        
        model.addAttribute("newQuizcard", new Quizcard());
        model.addAttribute("categories", categories);
        return "add_quizcard";
    }
    
    @PostMapping(value = {"/quizcard/add"})
    public String addQuizcard(@ModelAttribute("quizcard") Quizcard quizcard, BindingResult bindingResult, Model model) {
        
        logger.info("Create new quizcard in database: {}", quizcard);
        quizcardRepository.save(quizcard);
        
        model.addAttribute("display_success", true);
        
        return renderMainMenu(model);
    }
      
    
}

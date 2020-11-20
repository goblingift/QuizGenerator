/*
 * Copyright (C) 2020 Andre Kessler (https://github.com/goblingift)
 * All rights reserved
 */
package gift.goblin.quizgenerator.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author andre
 */
@Controller
public class AddQuizcardController {
    
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    
    @GetMapping(value = {"/quizcard/add"})
    public String renderMainMenu(Model model) {
        
        logger.info("User opened add-quizcard.");
        
        
        return "add_quizcard";
    }
    
    
}

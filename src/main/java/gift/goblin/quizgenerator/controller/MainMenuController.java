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
public class MainMenuController {
    
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    
    @GetMapping(value = {"/"})
    public String renderMainMenu(Model model) {
        
        logger.info("User opened main-menu.");
        
        
        return "main_menu";
    }
    
    
}

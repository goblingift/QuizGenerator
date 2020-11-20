/*
 * Copyright (C) 2020 Andre Kessler (https://github.com/goblingift)
 * All rights reserved
 */
package gift.goblin.quizgenerator.controller;

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
public class MainMenuController {
    
    @Autowired
    private BuildProperties buildProperties;
    
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    
    @GetMapping(value = {"/home", "/"})
    public String renderMainMenu(Model model) {
        
        logger.info("User opened main-menu.");
        
        model.addAttribute("build_artifact", buildProperties.getArtifact());
        model.addAttribute("build_version", buildProperties.getVersion());
        return "main_menu";
    }
    
    
}

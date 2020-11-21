/*
 * Copyright (C) 2020 Andre Kessler (https://github.com/goblingift)
 * All rights reserved
 */
package gift.goblin.quizgenerator.controller;

import gift.goblin.quizgenerator.WebSecurityConfig;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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
    public String renderMainMenu(Model model, Authentication authentication) {
        
        logger.info("User opened main-menu.");
        
        if (authentication != null && authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(WebSecurityConfig.ROLE_PREFIX + WebSecurityConfig.ROLE_ADMIN))) {
            logger.info("User has admin-role, display more options for em.");
            model.addAttribute("isAdmin", true);
        }
        
        model.addAttribute("build_artifact", buildProperties.getArtifact());
        model.addAttribute("build_version", buildProperties.getVersion());
        return "main_menu";
    }
    
    
}

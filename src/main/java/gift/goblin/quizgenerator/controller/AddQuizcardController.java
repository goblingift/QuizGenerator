/*
 * Copyright (C) 2020 Andre Kessler (https://github.com/goblingift)
 * All rights reserved
 */
package gift.goblin.quizgenerator.controller;

import gift.goblin.quizgenerator.WebSecurityConfig;
import gift.goblin.quizgenerator.database.model.Category;
import gift.goblin.quizgenerator.database.model.Quizcard;
import gift.goblin.quizgenerator.database.repo.CategoryRepository;
import gift.goblin.quizgenerator.database.repo.QuizcardRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author andre
 */
@Controller
@RequestMapping("/quizcard")
public class AddQuizcardController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    QuizcardRepository quizcardRepository;

    @GetMapping(value = {"/create"})
    public String renderMainMenu(Model model) {

        logger.info("User opened add-quizcard.");

        List<Category> categories = categoryRepository.findAll();
        logger.debug("Found {} categories- will sort them by name asc and add them to available category dropdown.", categories.size());
        
        categories.sort((Category c1, Category c2) -> c1.getName().compareTo(c2.getName()));

        model.addAttribute("newQuizcard", new Quizcard());
        model.addAttribute("categories", categories);
        return "add_quizcard";
    }

    @PostMapping(value = {"/add"})
    public String addQuizcard(@ModelAttribute("quizcard") Quizcard quizcard, BindingResult bindingResult, Model model) {

        logger.info("Create new quizcard in database: {}", quizcard);
        quizcardRepository.save(quizcard);

        model.addAttribute("display_success", true);

        return renderMainMenu(model);
    }

    @GetMapping(value = {"/edit"})
    public String renderEditQuizcards(Model model, Authentication authentication) {
        if (!isUserAdmin(authentication)) {
            logger.info("User opened edit-quizcards, without the required rights- redirect to startpage.");
            return "redirect:/home";
        } else {
            List<Quizcard> allQuizcards = quizcardRepository.findAll();
            logger.debug("Found {} quizcards.", allQuizcards.size());

            model.addAttribute("allQuizcards", allQuizcards);
            return "edit_quizcards";
        }
    }

    @GetMapping(value = "/delete/{id}")
    public String removeQuizcard(@PathVariable("id") String id, Authentication authentication, Model model) {
        if (!isUserAdmin(authentication)) {
            logger.info("User tried to delete quizcards, without the required rights- redirect to startpage.");
            return "redirect:/home";
        } else {
            logger.info("Will remove quizcard with id {}", id);
            quizcardRepository.deleteById(Integer.parseInt(id));
            return "redirect:/quizcard/edit";
        }
    }

    private boolean isUserAdmin(Authentication authentication) {
        if (authentication != null && authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(WebSecurityConfig.ROLE_PREFIX + WebSecurityConfig.ROLE_ADMIN))) {
            return true;
        } else {
            return false;
        }
    }

}

/*
 * Copyright (C) 2020 Andre Kessler (https://github.com/goblingift)
 * All rights reserved
 */
package gift.goblin.quizgenerator.controller;

import gift.goblin.quizgenerator.WebSecurityConfig;
import gift.goblin.quizgenerator.bean.GameMaster;
import gift.goblin.quizgenerator.database.model.Quizcard;
import gift.goblin.quizgenerator.database.repo.QuizcardRepository;
import gift.goblin.quizgenerator.dto.GameProgress;
import gift.goblin.quizgenerator.dto.QuizcardAnswer;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author andre
 */
@Controller
@RequestMapping("/game")
public class GameController {

    @Autowired
    private BuildProperties buildProperties;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    GameMaster gameMaster;

    @Autowired
    QuizcardRepository quizcardRepository;

    @GetMapping(path = "/question")
    public String renderAddCategoriesPage(HttpSession session, Model model, Authentication authentication) {

        GameProgress gameProgress = getGameProgressFromSession(session);
        logger.info("User {} started game.", gameProgress.getUsername());

        Quizcard newCard = gameMaster.getNewQuizcard(gameProgress);

        model.addAttribute("quizcard", newCard);
        model.addAttribute("correct_answer", false);
        model.addAttribute("answer", new QuizcardAnswer(newCard.getId()));
        model.addAttribute("build_artifact", buildProperties.getArtifact());
        model.addAttribute("build_version", buildProperties.getVersion());
        return "game";
    }

    @PostMapping(path = "/answer")
    public String answerQuizcard(HttpSession session, @ModelAttribute("answer") QuizcardAnswer quizcardAnswer, Model model, Authentication authentication) {
        logger.info("ANSWERED ");

        GameProgress gameProgress = getGameProgressFromSession(session);

        boolean correctAnswer = gameMaster.answerQuizcard(gameProgress, quizcardAnswer);

        if (correctAnswer) {
            model.addAttribute("correct_answer", true);
        } else {
            model.addAttribute("faulty_answer", true);
            model.addAttribute("correct_answer", false);
        }

        model.addAttribute("answer", quizcardAnswer);
        model.addAttribute("quizcard", quizcardRepository.findById(quizcardAnswer.getId()).get());
        model.addAttribute("build_artifact", buildProperties.getArtifact());
        model.addAttribute("build_version", buildProperties.getVersion());
        return "game";
    }

    private GameProgress getGameProgressFromSession(HttpSession session) {
        GameProgress gameProgress = (GameProgress) session.getAttribute(WebSecurityConfig.SESSION_FIELD_GAMEPROGRESS);
        return gameProgress;
    }

}

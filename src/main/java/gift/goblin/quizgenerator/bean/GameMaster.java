/*
 * Copyright (C) 2020 Andre Kessler (https://github.com/goblingift)
 * All rights reserved
 */
package gift.goblin.quizgenerator.bean;

import gift.goblin.quizgenerator.database.model.Quizcard;
import gift.goblin.quizgenerator.database.repo.QuizcardRepository;
import gift.goblin.quizgenerator.dto.GameProgress;
import gift.goblin.quizgenerator.dto.QuizcardAnswer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service bean, which gives the users new quizcard and stores their results.
 *
 * @author andre
 */
@Service
public class GameMaster {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    QuizcardRepository quizcardRepository;

    /**
     * Will give a new quizcard to the user and also add this card to the list
     * of played cards.
     *
     * @param gameProgress contains the information which cards the user already
     * got.
     * @return a new quizcard.
     */
    public Quizcard getNewQuizcard(GameProgress gameProgress) {

        List<Quizcard> playedCards = gameProgress.getPlayedCards();

        List<Quizcard> allCards = quizcardRepository.findAll();
        Collections.shuffle(allCards);
        allCards.removeAll(playedCards);

        if (allCards.isEmpty()) {
            logger.info("User {} has answered all questions- will reset their played cards and give em some of the already answered ones.", gameProgress.getUsername());

            ArrayList<Quizcard> newPlayedCards = new ArrayList<Quizcard>();

            allCards = quizcardRepository.findAll();
            Collections.shuffle(allCards);

            Quizcard newCard = allCards.get(0);
            newPlayedCards.add(newCard);
            gameProgress.setPlayedCards(newPlayedCards);
            return newCard;
        } else {
            Quizcard newCard = allCards.get(0);
            logger.info("Will give the following card to user {}: {}", gameProgress.getUsername(), newCard);
            gameProgress.addCardToPlayedStaple(newCard);
            return newCard;
        }
    }
    
    /**
     * Verify if the answer of the user was successful and log the entry as successful/faulty answer.
     * @param gameProgress contains the gameProgress of the user.
     * @param answer contains the answer of the user.
     * @return true if answer was correct, false if otherwise.
     */
    public boolean answerQuizcard(GameProgress gameProgress, QuizcardAnswer answer) {
        
        boolean correctAnswer = false;
        logger.info("User {} answered the question: {}- will evaluate it now.", gameProgress.getUsername(), answer);
        
        Optional<Quizcard> optQuizcard = quizcardRepository.findById(answer.getId());
        if (!optQuizcard.isPresent()) {
            logger.warn("User {} answered a question for a quizcard which cant get found in database (id was: {})", gameProgress.getUsername(), answer.getId());
        } else {
            if (optQuizcard.get().getCorrectAnswer() == answer.getAnswer()) {
                logger.info("User {} had successful answered the question: {}", gameProgress.getUsername(), optQuizcard.get().getQuestion());
                correctAnswer = true;
            } else {
                logger.info("User {} failed at the question: {}", gameProgress.getUsername(), optQuizcard.get().getQuestion());
                correctAnswer = false;
            }
        }
        
        gameProgress.logAnswer(correctAnswer);
        return correctAnswer;
    }

}

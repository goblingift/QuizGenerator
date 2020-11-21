/*
 * Copyright (C) 2020 Andre Kessler (https://github.com/goblingift)
 * All rights reserved
 */
package gift.goblin.quizgenerator.dto;

import gift.goblin.quizgenerator.database.model.Quizcard;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andre
 */
public class GameProgress {

    private String username;
    private List<Quizcard> playedCards;
    private int correctAnswers;
    private int wrongAnswers;
    private int createdCards;

    public GameProgress() {
        playedCards = new ArrayList<>();
    }

    public GameProgress(String username) {
        this.username = username;
        playedCards = new ArrayList<>();
    }

    /**
     * Will add this card to the list of played cards.
     *
     * @param quizcard the new quizcard.
     */
    public void addCardToPlayedStaple(Quizcard quizcard) {
        playedCards.add(quizcard);
    }
    
    public void logAnswer(boolean answerWasCorrect) {
        if (answerWasCorrect) {
            correctAnswers++;
        } else {
            wrongAnswers++;
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Quizcard> getPlayedCards() {
        return playedCards;
    }

    public void setPlayedCards(List<Quizcard> playedCards) {
        this.playedCards = playedCards;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public int getWrongAnswers() {
        return wrongAnswers;
    }

    public void setWrongAnswers(int wrongAnswers) {
        this.wrongAnswers = wrongAnswers;
    }

    public int getCreatedCards() {
        return createdCards;
    }

    public void setCreatedCards(int createdCards) {
        this.createdCards = createdCards;
    }

    @Override
    public String toString() {
        return "GameProgress{" + "playedCards=" + playedCards + ", correctAnswers=" + correctAnswers + ", wrongAnswers=" + wrongAnswers + ", createdCards=" + createdCards + '}';
    }

}

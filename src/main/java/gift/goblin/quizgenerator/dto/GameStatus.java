/*
 * Copyright (C) 2020 Andre Kessler (https://github.com/goblingift)
 * All rights reserved
 */
package gift.goblin.quizgenerator.dto;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

/**
 * Contains important settings for the game- e.g. if its already started or not.
 * @author andre
 */
public class GameStatus {
    
    private boolean gameActivated;

    public void startGame() {
        setGameActivated(true);
    }
    
    public void stopGame() {
        setGameActivated(false);
    }
    
    public boolean isGameActivated() {
        return gameActivated;
    }

    public void setGameActivated(boolean gameActivated) {
        this.gameActivated = gameActivated;
    }

    @Override
    public String toString() {
        return "GameStatus{" + "gameActivated=" + gameActivated + '}';
    }
    
}

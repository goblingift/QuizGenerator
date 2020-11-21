/*
 * Copyright (C) 2020 Andre Kessler (https://github.com/goblingift)
 * All rights reserved
 */
package gift.goblin.quizgenerator.dto;

/**
 *
 * @author andre
 */
public class QuizcardAnswer {
    
    /**
     * Contains the id of the quizcard.
     */
    private int id;
    
    private int answer;

    public QuizcardAnswer(int id) {
        this.id = id;
    }

    public QuizcardAnswer() {
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "QuizcardAnswer{" + "id=" + id + ", answer=" + answer + '}';
    }
    
}

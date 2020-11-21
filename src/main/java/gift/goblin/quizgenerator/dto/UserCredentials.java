/*
 * Copyright (C) 2020 Andre Kessler (https://github.com/goblingift)
 * All rights reserved
 */
package gift.goblin.quizgenerator.dto;

/**
 *
 * @author andre
 */
public class UserCredentials {
    
    private String username;

    public UserCredentials() {
    }

    public UserCredentials(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserCredentials{" + "username=" + username + '}';
    }
    
}

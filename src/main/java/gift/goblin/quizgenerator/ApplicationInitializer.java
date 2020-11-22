/*
 * Copyright (C) 2020 Andre Kessler (https://github.com/goblingift)
 * All rights reserved
 */
package gift.goblin.quizgenerator;

import gift.goblin.quizgenerator.dto.GameStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author andre
 */
@Service
class ApplicationInitializer implements InitializingBean{

    @Autowired
    GameStatus gameStatus;

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("Initially created gameStatus: {}", gameStatus);
    }
}

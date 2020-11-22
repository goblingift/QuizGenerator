package gift.goblin.quizgenerator;

import gift.goblin.quizgenerator.dto.GameStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

@SpringBootApplication
public class QuizGeneratorApplication {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(QuizGeneratorApplication.class, args);
    }

    @Bean(name = "gameStatus")
    @Scope(value = WebApplicationContext.SCOPE_APPLICATION)
    public GameStatus getGameStatus() {
        logger.info("Created new GameStatus-object.");
        return new GameStatus();
    }

}

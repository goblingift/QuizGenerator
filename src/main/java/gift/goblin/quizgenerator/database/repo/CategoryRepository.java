/*
 * Copyright (C) 2020 Andre Kessler (https://github.com/goblingift)
 * All rights reserved
 */
package gift.goblin.quizgenerator.database.repo;

import gift.goblin.quizgenerator.database.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author andre
 */
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    
}

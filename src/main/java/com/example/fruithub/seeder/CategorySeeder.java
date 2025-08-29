package com.example.fruithub.seeder;

import com.example.fruithub.entity.Category;
import com.example.fruithub.entity.Status;
import com.example.fruithub.repository.CategoryRepository;
import com.example.fruithub.repository.StatusRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategorySeeder {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private StatusRepository statusRepository;

    @PostConstruct
    public void seedCategory() {

        if (categoryRepository.count() == 0) {
            Status activeStatus = statusRepository.findByName("Active");
            Category freshCategory = Category.builder()
                    .name("Fresh Fruits")
                    .description("Fruits that are fresh and picked in season.")
                    .parentCategory(null)
                    .status(activeStatus)
                    .build();

            Category driedCategory = Category.builder()
                    .name("Dried Fruits")
                    .description("Fruits that are dried and last a long time.")
                    .parentCategory(null)
                    .status(activeStatus)
                    .build();

            Category citrusFruits = Category.builder()
                    .name("Citrus Fruits")
                    .description("Fruits like orange and lemon with vitamin C.")
                    .parentCategory(null)
                    .status(activeStatus)
                    .build();

            Category exoticFruits = Category.builder()
                    .name("Exotic Fruits")
                    .description("Special fruits from other countries.")
                    .parentCategory(null)
                    .status(activeStatus)
                    .build();

            Category organicFruits = Category.builder()
                    .name("Organic Fruits")
                    .description("Fruits grown without chemicals.")
                    .parentCategory(null)
                    .status(activeStatus)
                    .build();

            categoryRepository.save(freshCategory);
            categoryRepository.save(driedCategory);
            categoryRepository.save(citrusFruits);
            categoryRepository.save(exoticFruits);
            categoryRepository.save(organicFruits);

        }
    }

}

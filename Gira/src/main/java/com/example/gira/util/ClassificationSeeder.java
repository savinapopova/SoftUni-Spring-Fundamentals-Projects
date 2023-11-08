package com.example.gira.util;

import com.example.gira.model.entity.Classification;
import com.example.gira.model.enums.ClassificationName;
import com.example.gira.repository.ClassificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClassificationSeeder implements CommandLineRunner {

    private ClassificationRepository classificationRepository;

    @Autowired
    public ClassificationSeeder(ClassificationRepository classificationRepository) {
        this.classificationRepository = classificationRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (this.classificationRepository.count() == 0) {
            List<Classification> classifications = Arrays.stream(ClassificationName.values())
                    .map(Classification::new)
                    .collect(Collectors.toList());
            this.classificationRepository.saveAll(classifications);
        }
    }
}

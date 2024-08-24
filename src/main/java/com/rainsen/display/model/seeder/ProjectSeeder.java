package com.rainsen.display.model.seeder;

import com.rainsen.display.model.entity.Project;
import com.rainsen.display.model.repository.ProjectRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Component
@DependsOn("userSeeder")
public class ProjectSeeder {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectSeeder(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @PostConstruct
    @Transactional
    public void seedProjects() {

        if (projectRepository.count() > 0) return;

        Faker faker = new Faker();
        IntStream.range(0, 100).forEach(i -> {
            Project project = new Project();
            project.setUserId((long) i + 1);
            project.setInitiator(faker.name().firstName());
            project.setTitle(faker.company().name());
            project.setDescription(faker.lorem().paragraph(1));
            project.setDetail(faker.lorem().paragraph(1));
            project.setProgress(faker.random().nextInt(0, 100));
            projectRepository.save(project);
        });
    }
}

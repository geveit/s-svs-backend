package com.geveit.s_svs.use_case.create_project;

import com.geveit.s_svs.entity.Project;
import com.geveit.s_svs.entity.Version;
import com.geveit.s_svs.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class CreateProjectUseCase {
    private final ProjectRepository projectRepository;

    @Transactional
    public CreateProjectResponse execute(CreateProjectRequest request) {
        Project project = Project.builder().title(request.title()).versions(new ArrayList<>()).build();
        Version version = Version.builder().versionNumber(1).build();
        project.getVersions().add(version);
        projectRepository.save(project);

        return new CreateProjectResponse(project.getId(), version.getId(), project.getTitle(), project.getCreatedAt());
    }
}

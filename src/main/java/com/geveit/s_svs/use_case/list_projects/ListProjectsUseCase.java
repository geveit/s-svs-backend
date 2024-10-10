package com.geveit.s_svs.use_case.list_projects;

import com.geveit.s_svs.entity.Project;
import com.geveit.s_svs.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ListProjectsUseCase {
    private final ProjectRepository projectRepository;

    public List<ListProjectItemResponse> execute() {
        List<Project> projects = projectRepository.findAll();

        return projects.stream()
                .map(project -> new ListProjectItemResponse(project.getId(), project.getTitle(), project.getCreatedAt()))
                .toList();
    }
}

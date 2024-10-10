package com.geveit.s_svs.use_case.delete_project;

import com.geveit.s_svs.entity.Project;
import com.geveit.s_svs.expection.ProjectNotFoundException;
import com.geveit.s_svs.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteProjectUseCase {
    private final ProjectRepository projectRepository;

    public void execute(long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException("Project not found"));

        projectRepository.delete(project);
    }
}

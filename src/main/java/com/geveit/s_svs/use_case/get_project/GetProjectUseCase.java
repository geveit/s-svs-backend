package com.geveit.s_svs.use_case.get_project;

import com.geveit.s_svs.entity.Version;
import com.geveit.s_svs.expection.ProjectNotFoundException;
import com.geveit.s_svs.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetProjectUseCase {
    private final ProjectRepository projectRepository;

    public GetProjectResponse execute(long projectId) {
        return projectRepository.findById(projectId)
                .map(project -> new GetProjectResponse(project.getId(), project.getTitle(), project.getCreatedAt(),
                        project.getVersions().stream().map(this::getVersionItemResponse).toList()))
                .orElseThrow(() -> new ProjectNotFoundException("Project not found"));
    }

    private VersionItemResponse getVersionItemResponse(Version version) {
        return new VersionItemResponse(version.getId(), version.getVersionNumber());
    }
}

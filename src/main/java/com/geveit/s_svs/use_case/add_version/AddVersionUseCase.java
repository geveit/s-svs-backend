package com.geveit.s_svs.use_case.add_version;

import com.geveit.s_svs.entity.Version;
import com.geveit.s_svs.expection.ProjectNotFoundException;
import com.geveit.s_svs.repository.ProjectRepository;
import com.geveit.s_svs.repository.VersionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class AddVersionUseCase {
    private final ProjectRepository projectRepository;
    private final VersionRepository versionRepository;

    @Transactional
    public AddVersionResponse execute(long projectId) {
        var project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException("Project not found"));

        int lastVersionNumber = versionRepository.findLastVersionNumberByProjectId(project.getId());

        var version = Version.builder().versionNumber(lastVersionNumber + 1).build();

        version.setProject(project);

        versionRepository.save(version);

        return new AddVersionResponse(project.getId(), version.getId());
    }
}

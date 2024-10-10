package com.geveit.s_svs.use_case;

import com.geveit.s_svs.entity.Project;
import com.geveit.s_svs.entity.Version;
import com.geveit.s_svs.expection.ProjectNotFoundException;
import com.geveit.s_svs.repository.ProjectRepository;
import com.geveit.s_svs.repository.VersionRepository;
import com.geveit.s_svs.use_case.add_version.AddVersionResponse;
import com.geveit.s_svs.use_case.add_version.AddVersionUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AddVersionUseCaseTest {

    @Mock
    private VersionRepository versionRepository;

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private AddVersionUseCase addVersionUseCase;

    @Test
    void execute_shouldThrowExeptionWhenProjectNotFound() {

        when(projectRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ProjectNotFoundException.class, () -> addVersionUseCase.execute(1L));
    };

    @Test
    void execute_shouldAddANewVersion() {
        Project project = Project.builder().id(1L).title("New Project").versions(new ArrayList<>()).build();
        Version newVersion = Version.builder().id(2L).versionNumber(1).build();

        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));
        when(versionRepository.findLastVersionNumberByProjectId(1L)).thenReturn(1);
        when(versionRepository.save(any(Version.class))).thenReturn(newVersion);

        AddVersionResponse response = addVersionUseCase.execute(1L);

        assertEquals(1L, response.projectId());
        assertEquals(2L, response.versionId());
    };
}

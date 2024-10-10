package com.geveit.s_svs.use_case;


import com.geveit.s_svs.entity.Project;
import com.geveit.s_svs.entity.Version;
import com.geveit.s_svs.expection.ProjectNotFoundException;
import com.geveit.s_svs.repository.ProjectRepository;
import com.geveit.s_svs.use_case.get_project.GetProjectResponse;
import com.geveit.s_svs.use_case.get_project.GetProjectUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetProjectUseCaseTest {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private GetProjectUseCase getProjectUseCase;

    @Test
    void execute_shouldThrowExeptionWhenProjectNotFound() {

        when(projectRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ProjectNotFoundException.class, () -> getProjectUseCase.execute(1L));
    }

    @Test
    void execute_shouldReturnProject() {
        Project project = Project.builder().id(1L).title("New Project").versions(new ArrayList<>()).build();
        Version version = Version.builder().id(1L).versionNumber(1).build();
        project.getVersions().add(version);

        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));

        GetProjectResponse response = getProjectUseCase.execute(1L);

        assertEquals(1L, response.id());
        assertEquals("New Project", response.title());
        assertEquals(1, response.versions().size());
    }
}

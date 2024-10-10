package com.geveit.s_svs.use_case;

import com.geveit.s_svs.entity.Project;
import com.geveit.s_svs.entity.Version;
import com.geveit.s_svs.repository.ProjectRepository;
import com.geveit.s_svs.use_case.create_project.CreateProjectRequest;
import com.geveit.s_svs.use_case.create_project.CreateProjectResponse;
import com.geveit.s_svs.use_case.create_project.CreateProjectUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateProjectUseCaseTest {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private CreateProjectUseCase createProjectUseCase;

    @Test
    void execute_shouldCreateProjectWithCorrectValues() {
        // Arrange
        CreateProjectRequest request = new CreateProjectRequest("New Project");
        Project project = Project.builder()
                .id(1L)
                .title(request.title())
                .versions(new ArrayList<>())
                .build();
        Version version = Version.builder().id(1L).versionNumber(1).build();

        when(projectRepository.save(any(Project.class))).thenReturn(project);

        CreateProjectResponse response = createProjectUseCase.execute(request);

        assertNotNull(response);
        assertEquals(1, response.id());
        assertEquals("New Project", response.title());

        verify(projectRepository, times(1)).save(argThat(savedProject ->
                savedProject.getTitle().equals("New Project") &&
                    savedProject.getVersions().size() == 1 &&
                    savedProject.getVersions().get(0).getVersionNumber() == 1
        ));
    }
}

package com.geveit.s_svs.use_case;

import com.geveit.s_svs.entity.Project;
import com.geveit.s_svs.entity.Version;
import com.geveit.s_svs.expection.ProjectNotFoundException;
import com.geveit.s_svs.repository.ProjectRepository;
import com.geveit.s_svs.use_case.add_version.AddVersionResponse;
import com.geveit.s_svs.use_case.delete_project.DeleteProjectUseCase;
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
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeleteProjectUseCaseTest {
    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private DeleteProjectUseCase deleteProjectUseCase;

    @Test
    void execute_shouldThrowExeptionWhenProjectNotFound() {

        when(projectRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ProjectNotFoundException.class, () -> deleteProjectUseCase.execute(1L));
    };

    @Test
    void execute_shouldDeleteProject() {
        Project project = Project.builder().id(1L).title("New Project").versions(new ArrayList<>()).build();

        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));

       deleteProjectUseCase.execute(1L);

        verify(projectRepository, times(1)).delete(argThat(deletedProject ->
                deletedProject.getTitle().equals("New Project") && deletedProject.getId() == 1L
        ));
    };
}

package com.geveit.s_svs.use_case;

import com.geveit.s_svs.entity.Project;
import com.geveit.s_svs.repository.ProjectRepository;
import com.geveit.s_svs.use_case.list_projects.ListProjectItemResponse;
import com.geveit.s_svs.use_case.list_projects.ListProjectsUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ListProjectsUseCaseTest {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ListProjectsUseCase listProjectsUseCase;

    @Test
    void execute_shouldReturnCorrectList() {
        Project project1 = Project.builder().id(1L).title("Sample Project").createdAt(LocalDate.of(2024, 10, 10)).build();
        Project project2 = Project.builder().id(2L).title("Another Project").createdAt(LocalDate.of(2025, 10, 10)).build();

        when(projectRepository.findAll()).thenReturn(List.of(project1, project2));

        List<ListProjectItemResponse> result = listProjectsUseCase.execute();

        assertThat(result).hasSize(2);
        ListProjectItemResponse response1 = result.get(0);
        assertThat(response1.id()).isEqualTo(1L);
        assertThat(response1.title()).isEqualTo("Sample Project");
        assertThat(response1.createdAt()).isEqualTo(LocalDate.of(2024, 10, 10));
        ListProjectItemResponse response2 = result.get(1);
        assertThat(response2.id()).isEqualTo(2L);
        assertThat(response2.title()).isEqualTo("Another Project");
        assertThat(response2.createdAt()).isEqualTo(LocalDate.of(2025, 10, 10));
    }
}

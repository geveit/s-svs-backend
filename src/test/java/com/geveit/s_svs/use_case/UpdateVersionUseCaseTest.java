package com.geveit.s_svs.use_case;

import com.geveit.s_svs.entity.Project;
import com.geveit.s_svs.entity.Version;
import com.geveit.s_svs.expection.VersionNotFoundException;
import com.geveit.s_svs.repository.VersionRepository;
import com.geveit.s_svs.use_case.update_version.UpdateVersionRequest;
import com.geveit.s_svs.use_case.update_version.UpdateVersionResponse;
import com.geveit.s_svs.use_case.update_version.UpdateVersionUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.StatusResultMatchersExtensionsKt.isEqualTo;

@ExtendWith(MockitoExtension.class)
public class UpdateVersionUseCaseTest {

    @Mock
    private VersionRepository versionRepository;

    @InjectMocks
    private UpdateVersionUseCase updateVersionUseCase;

    @Test
    void execute_shouldThrowExeptionWhenVersionNotFound() {
        UpdateVersionRequest request = new UpdateVersionRequest("title", "notes", "lyrics");

        when(versionRepository.findByIdAndProjectId(1L, 1L)).thenReturn(Optional.empty());

        assertThrows(VersionNotFoundException.class, () -> updateVersionUseCase.execute(1L, 1L, request));
    }

    @Test
    void execute_shouldUpdateVersion() {
        UpdateVersionRequest request = new UpdateVersionRequest("new title", "new notes", "new lyrics");
        Version version = Version.builder().id(1L).versionNumber(1).notes("notes").title("title").lyrics("lyrics").build();
        Project project = Project.builder().id(1L).title("New Project").versions(new ArrayList<>()).build();
        version.setProject(project);

        when(versionRepository.findByIdAndProjectId(1L, 1L)).thenReturn(Optional.of(version));
        when(versionRepository.save(any(Version.class))).thenReturn(version);

        UpdateVersionResponse response = updateVersionUseCase.execute(1L, 1L, request);

        assertThat(response.projectId()).isEqualTo(1L);
        assertThat(response.versionId()).isEqualTo(1L);
        assertThat(response.versionNumber()).isEqualTo(1);
        assertThat(response.title()).isEqualTo("new title");
        assertThat(response.notes()).isEqualTo("new notes");
        assertThat(response.lyrics()).isEqualTo("new lyrics");
    }
}

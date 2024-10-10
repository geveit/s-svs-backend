package com.geveit.s_svs.use_case;

import com.geveit.s_svs.entity.Version;
import com.geveit.s_svs.expection.VersionNotFoundException;
import com.geveit.s_svs.repository.VersionRepository;
import com.geveit.s_svs.use_case.get_version.GetVersionResponse;
import com.geveit.s_svs.use_case.get_version.GetVersionUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetVersionUseCaseTest {

    @Mock
    private VersionRepository versionRepository;

    @InjectMocks
    private GetVersionUseCase getVersionUseCase;

    @Test
    void execute_shouldThrowExeptionWhenVersionNotFound() {

        when(versionRepository.findByIdAndProjectId(1L, 1L)).thenReturn(Optional.empty());

        assertThrows(VersionNotFoundException.class, () -> getVersionUseCase.execute(1L, 1L));
    }

    @Test
    void execute_shouldReturnVersion() {
        Version version = Version.builder().id(1L).versionNumber(1).notes("notes").title("title").lyrics("lyrics").build();

        when(versionRepository.findByIdAndProjectId(1L, 1L)).thenReturn(Optional.of(version));

        GetVersionResponse response = getVersionUseCase.execute(1L, 1L);

        assertEquals(1L, response.versionId());
        assertEquals(1, response.versionNumber());
        assertEquals("title", response.title());
        assertEquals("lyrics", response.lyrics());
        assertEquals("notes", response.notes());
    }
}

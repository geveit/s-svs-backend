package com.geveit.s_svs.use_case.get_version;

import com.geveit.s_svs.entity.Version;
import com.geveit.s_svs.expection.VersionNotFoundException;
import com.geveit.s_svs.repository.VersionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetVersionUseCase {
    private final VersionRepository versionRepository;

    public GetVersionResponse execute(long projectId, long versionId) {
        return versionRepository.findByIdAndProjectId(versionId, projectId)
                .map(version -> new GetVersionResponse(version.getId(), version.getVersionNumber(), version.getTitle(), version.getNotes(), version.getLyrics()))
                .orElseThrow(() -> new VersionNotFoundException("Version not found"));
    }
}

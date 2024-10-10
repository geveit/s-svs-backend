package com.geveit.s_svs.use_case.update_version;

import com.geveit.s_svs.entity.Version;
import com.geveit.s_svs.expection.VersionNotFoundException;
import com.geveit.s_svs.repository.VersionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateVersionUseCase {
    private final VersionRepository versionRepository;

    public UpdateVersionResponse execute(long projectId, long versionId, UpdateVersionRequest request) {
        Version version = versionRepository.findByIdAndProjectId(versionId, projectId)
                .orElseThrow(() -> new VersionNotFoundException("Version not found"));

        version.setTitle(request.title());
        version.setNotes(request.notes());
        version.setLyrics(request.lyrics());

        versionRepository.save(version);

        return new UpdateVersionResponse(version.getProject().getId(), version.getId(), version.getVersionNumber(), version.getTitle(), version.getLyrics(), version.getNotes());
    }
}

package com.geveit.s_svs.use_case.update_version;

public record UpdateVersionResponse(
        long projectId,
        long versionId,
        int versionNumber,
        String title,
        String lyrics,
        String notes
) {
}

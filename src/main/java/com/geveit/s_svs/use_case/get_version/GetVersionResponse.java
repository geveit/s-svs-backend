package com.geveit.s_svs.use_case.get_version;

public record GetVersionResponse(
        long versionId,
        int versionNumber,
        String title,
        String notes,
        String lyrics
) {
}

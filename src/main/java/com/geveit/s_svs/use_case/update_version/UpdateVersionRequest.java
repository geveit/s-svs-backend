package com.geveit.s_svs.use_case.update_version;

public record UpdateVersionRequest(
        String title,
        String notes,
        String lyrics
) {
}

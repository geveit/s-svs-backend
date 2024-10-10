package com.geveit.s_svs.use_case.create_project;

import java.time.LocalDate;

public record CreateProjectResponse(
        Long id,
        Long versionId,
        String title,
        LocalDate craetedAt

) {
}

package com.geveit.s_svs.use_case.list_projects;

import java.time.LocalDate;

public record ListProjectItemResponse(
        long id,
        String title,
        LocalDate createdAt
) {
}
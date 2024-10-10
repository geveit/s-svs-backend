package com.geveit.s_svs.use_case.list_projects;

import java.time.LocalDate;
import java.util.List;

public record ListProjectItemResponse(
        long id,
        String title,
        LocalDate createdAt
) {
}
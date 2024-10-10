package com.geveit.s_svs.use_case.get_project;

import java.time.LocalDate;
import java.util.List;

public record GetProjectResponse(
        Long id,
        String title,
        LocalDate createdAt,
        List<VersionItemResponse> versions
){}

package com.geveit.s_svs.use_case.create_project;

import jakarta.validation.constraints.NotBlank;

public record CreateProjectRequest(
        @NotBlank String title
) {
}

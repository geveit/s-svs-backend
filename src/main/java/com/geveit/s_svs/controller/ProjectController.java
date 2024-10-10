package com.geveit.s_svs.controller;

import com.geveit.s_svs.use_case.add_version.AddVersionResponse;
import com.geveit.s_svs.use_case.add_version.AddVersionUseCase;
import com.geveit.s_svs.use_case.create_project.CreateProjectRequest;
import com.geveit.s_svs.use_case.create_project.CreateProjectResponse;
import com.geveit.s_svs.use_case.create_project.CreateProjectUseCase;
import com.geveit.s_svs.use_case.delete_project.DeleteProjectUseCase;
import com.geveit.s_svs.use_case.get_project.GetProjectResponse;
import com.geveit.s_svs.use_case.get_project.GetProjectUseCase;
import com.geveit.s_svs.use_case.get_version.GetVersionResponse;
import com.geveit.s_svs.use_case.get_version.GetVersionUseCase;
import com.geveit.s_svs.use_case.list_projects.ListProjectItemResponse;
import com.geveit.s_svs.use_case.list_projects.ListProjectsUseCase;
import com.geveit.s_svs.use_case.update_version.UpdateVersionRequest;
import com.geveit.s_svs.use_case.update_version.UpdateVersionResponse;
import com.geveit.s_svs.use_case.update_version.UpdateVersionUseCase;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@AllArgsConstructor
public class ProjectController {
    private final CreateProjectUseCase createProjectUseCase;
    private final ListProjectsUseCase listProjectsUseCase;
    private final DeleteProjectUseCase deleteProjectUseCase;
    private final GetProjectUseCase getProjectUseCase;
    private final AddVersionUseCase addVersionUseCase;
    private final UpdateVersionUseCase updateVersionUseCase;
    private final GetVersionUseCase getVersionUseCase;

    @PostMapping
    public ResponseEntity<CreateProjectResponse> createProject(@Valid @RequestBody CreateProjectRequest request) {
        return ResponseEntity.ok(createProjectUseCase.execute(request));
    }

    @GetMapping
    public ResponseEntity<List<ListProjectItemResponse>> getProjects() {
        return ResponseEntity.ok(listProjectsUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetProjectResponse> getProject(@PathVariable long id) {
        return ResponseEntity.ok(getProjectUseCase.execute(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable long id) {
        deleteProjectUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/versions")
    public ResponseEntity<AddVersionResponse> createVersion(@PathVariable long id) {
        return ResponseEntity.ok(addVersionUseCase.execute(id));
    }

    @GetMapping("/{id}/versions/{versionId}")
    public ResponseEntity<GetVersionResponse> getVersion(@PathVariable long id, @PathVariable long versionId) {
        return ResponseEntity.ok(getVersionUseCase.execute(id, versionId));
    }

    @PutMapping("/{id}/versions/{versionId}")
    public ResponseEntity<UpdateVersionResponse> updateVersion(@PathVariable long id, @PathVariable long versionId, @RequestBody UpdateVersionRequest request) {
        return ResponseEntity.ok(updateVersionUseCase.execute(id, versionId, request));
    }
}

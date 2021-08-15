package io.github.talelin.latticy.service;

import io.github.talelin.latticy.dto.book.CreateOrUpdateBookDTO;
import io.github.talelin.latticy.model.ProjectDO;
import io.github.talelin.latticy.request.CreateOrUpdateProjectRequest;

import java.util.List;

public interface ProjectService {

    ProjectDO createProject(CreateOrUpdateProjectRequest createOrUpdateProjectRequest);

    List<ProjectDO> getProjectPage(int pageNum, String category, String searchContent);

    List<ProjectDO> getProjectList(String category, String searchContent);

    ProjectDO updateProject(CreateOrUpdateProjectRequest createOrUpdateProjectRequest);

    ProjectDO getProjectById(Integer id);

    boolean deleteProjectById(Integer id);
}

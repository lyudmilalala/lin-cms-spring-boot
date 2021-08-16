package io.github.talelin.latticy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.talelin.latticy.model.ProjectDO;
import io.github.talelin.latticy.request.CreateOrUpdateProjectRequest;

import java.util.List;

/**
 * @description project service interface
 * @author Yuchen Sun
 * @createDate 2021-08-15
 */
public interface ProjectService {

    ProjectDO createProject(CreateOrUpdateProjectRequest createOrUpdateProjectRequest);

    List<ProjectDO> getProjectPage2(int pageNum, String category, String searchContent);

    IPage getProjectPage(int pageNum, String category, String searchContent);

    List<ProjectDO> getProjectList(String category, String searchContent);

    ProjectDO updateProject(CreateOrUpdateProjectRequest createOrUpdateProjectRequest);

    ProjectDO getProjectById(Integer id);

    void deleteProjectById(Integer id);
}

package io.github.talelin.latticy.service.impl;

import io.github.talelin.latticy.dto.book.CreateOrUpdateBookDTO;
import io.github.talelin.latticy.model.ProjectDO;
import io.github.talelin.latticy.request.CreateOrUpdateProjectRequest;
import io.github.talelin.latticy.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private static int AUTHOR_ID = 1;
    @Override
    public ProjectDO createProject(CreateOrUpdateProjectRequest createOrUpdateProjectRequest) {
        
        ProjectDO project = new ProjectDO();
        return null;
    }

    @Override
    public List<ProjectDO> getProjectPage(int pageNum, String category, String searchContent) {
        return null;
    }

    @Override
    public List<ProjectDO> getProjectList(String category, String searchContent) {
        return null;
    }

    @Override
    public ProjectDO updateProject(CreateOrUpdateProjectRequest createOrUpdateProjectRequest) {
        return null;
    }

    @Override
    public ProjectDO getProjectById(Integer id) {
        return null;
    }

    @Override
    public boolean deleteProjectById(Integer id) {
        return false;
    }
}

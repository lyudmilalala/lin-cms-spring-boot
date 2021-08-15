package io.github.talelin.latticy.service.impl;

import io.github.talelin.latticy.dto.book.CreateOrUpdateBookDTO;
import io.github.talelin.latticy.mapper.BookMapper;
import io.github.talelin.latticy.mapper.ProjectMapper;
import io.github.talelin.latticy.model.ProjectDO;
import io.github.talelin.latticy.request.CreateOrUpdateProjectRequest;
import io.github.talelin.latticy.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class ProjectServiceImpl implements ProjectService {

    private static int AUTHOR_ID = 1;
    private static int PROJECT_PAGE_SIZE = 5;

    @Autowired
    private ProjectMapper projectMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProjectDO createProject(CreateOrUpdateProjectRequest createOrUpdateProjectRequest) {

        ProjectDO project = new ProjectDO(createOrUpdateProjectRequest.getTitle(), AUTHOR_ID, createOrUpdateProjectRequest.getContent(), createOrUpdateProjectRequest.getCategory());
        int res = projectMapper.insert(project);
        if (res > 0) {
            project = projectMapper.selectById(res);
            return project;
        } else {
            log.error("Insert project failed.");
        }
        return null;
    }

    @Override
    public List<ProjectDO> getProjectPage(int pageNum, String category, String searchContent) {
        int pageStartIdx = (pageNum-1)*PROJECT_PAGE_SIZE;
        if (category != null && searchContent != null) {
            return projectMapper.selectActivePageByCategoryAndTitleLike(pageStartIdx, PROJECT_PAGE_SIZE, category, searchContent);
        } else if (category != null) {
            return projectMapper.selectActivePageByCategory(pageStartIdx, PROJECT_PAGE_SIZE, category);
        } else if (searchContent != null) {
            return projectMapper.selectActivePageByTitleLike(pageStartIdx, PROJECT_PAGE_SIZE, searchContent);
        } else {
            return projectMapper.selectActivePage(pageStartIdx, PROJECT_PAGE_SIZE);
        }
    }

    @Override
    public List<ProjectDO> getProjectList(String category, String searchContent) {
        if (category != null && searchContent != null) {
            return projectMapper.selectActiveByCategoryAndTitleLike(category, searchContent);
        } else if (category != null) {
            return projectMapper.selectActiveByCategory(category);
        } else if (searchContent != null) {
            return projectMapper.selectActiveByTitleLike(searchContent);
        } else {
            return projectMapper.selectActive();
        }
    }

    @Override
    public ProjectDO updateProject(CreateOrUpdateProjectRequest createOrUpdateProjectRequest) {
        ProjectDO project = projectMapper.selectById(createOrUpdateProjectRequest.getId());
        project.setTitle(createOrUpdateProjectRequest.getTitle());
        project.setCategory(createOrUpdateProjectRequest.getCategory());
        project.setContent(createOrUpdateProjectRequest.getContent());
        int res = projectMapper.updateById(project);
        if (res > 0) {
            project = projectMapper.selectById(res);
            return project;
        } else {
            log.error("Update project failed.");
        }
        return null;
    }

    @Override
    public ProjectDO getProjectById(Integer id) {
        return projectMapper.selectById(id);
    }

    @Override
    public void deleteProjectById(Integer id) {
        int res = projectMapper.deleteById(id);
    }
}

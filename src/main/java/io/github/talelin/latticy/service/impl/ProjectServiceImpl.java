package io.github.talelin.latticy.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.talelin.latticy.common.constant.CategoryConstant;
import io.github.talelin.latticy.common.exception.InvalidProjectRequestException;
import io.github.talelin.latticy.mapper.ProjectMapper;
import io.github.talelin.latticy.model.ProjectDO;
import io.github.talelin.latticy.request.CreateOrUpdateProjectRequest;
import io.github.talelin.latticy.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * @description project service implementation
 * @author Yuchen Sun
 * @createDate 2021-08-15
 */
@Slf4j
@Service
public class ProjectServiceImpl implements ProjectService {

    private static int AUTHOR_ID = 1;
    public static int PROJECT_PAGE_SIZE = 5;

    @Autowired
    private ProjectMapper projectMapper;

    /**
    * @description: 插入项目
    * @param: [createOrUpdateProjectRequest] - 创建或更新项目的请求体
    * @return: io.github.talelin.latticy.model.ProjectDO 项目对象
    * @throws: [InvalidProjectRequestException] - 创建或更新项目的请求体不合法
    *          [code 10023] - 标题过长
    *          [code 10024] - 类别不存在
    * @author: Yuchen Sun
    * @createDate: 2021/8/16
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProjectDO createProject(CreateOrUpdateProjectRequest createOrUpdateProjectRequest) {
        validationCreateOrUpdateProjectRequest(createOrUpdateProjectRequest);
        ProjectDO project = new ProjectDO(createOrUpdateProjectRequest.getTitle(), AUTHOR_ID, createOrUpdateProjectRequest.getContent(), createOrUpdateProjectRequest.getCategory());
        int res = projectMapper.insert(project);
        if (res > 0) {
            log.info("project = " + project + " with id = " + project.getId());
            project = projectMapper.selectById(project.getId());
            return project;
        } else {
            log.error("Insert project failed.");
        }
        return null;
    }

    @Override
    public List<ProjectDO> getProjectPage2(int pageNum, String category, String searchContent) {
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
    public IPage getProjectPage(int pageNum, String category, String searchContent) {
        Page<ProjectDO> page = new Page<>(pageNum,PROJECT_PAGE_SIZE);
        if (category != null && searchContent != null) {
            return  projectMapper.selectActivePageByPageAndCategoryAndTitleLike(page,category, searchContent);
        } else if (category != null) {
            return projectMapper.selectActivePageByPageAndCategory(page,category);
        } else if (searchContent != null) {
            return projectMapper.selectActivePageByPageAndTitleLike(page,searchContent);
        } else {
            return projectMapper.selectActivePageByPage(page);
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

    /**
     * @description: 更新项目
     * @param: [createOrUpdateProjectRequest] - 创建或更新项目的请求体
     * @return: io.github.talelin.latticy.model.ProjectDO 项目对象
     * @throws: [InvalidProjectRequestException] - 创建或更新项目的请求体不合法
     *          [code 10023] - 标题过长
     *          [code 10024] - 类别不存在
     * @author: Yuchen Sun
     * @createDate: 2021/8/16
     */
    @Override
    public ProjectDO updateProject(CreateOrUpdateProjectRequest createOrUpdateProjectRequest) {
        validationCreateOrUpdateProjectRequest(createOrUpdateProjectRequest);
        ProjectDO project = projectMapper.selectById(createOrUpdateProjectRequest.getId());
        log.info("old project = " + project);
        if (project == null) {
            log.error("No project with id = "+ createOrUpdateProjectRequest.getId() +" to update.");
        } else {
            project.setTitle(createOrUpdateProjectRequest.getTitle());
            project.setCategory(createOrUpdateProjectRequest.getCategory());
            project.setContent(createOrUpdateProjectRequest.getContent());
            int res = projectMapper.updateById(project);
            if (res > 0) {
                project = projectMapper.selectById(createOrUpdateProjectRequest.getId());
                return project;
            } else {
                log.error("Update project failed.");
            }
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

    /**
    * @description: 检查创建或更新项目的请求体是否合法的验证方法
    * @param: [createOrUpdateProjectRequest] - 创建或更新项目的请求体
    * @return: void
    * @throws: [InvalidProjectRequestException] - 创建或更新项目的请求体不合法
    *          [code 10023] - 标题过长
    *          [code 10024] - 类别不存在
    * @author: Yuchen Sun
    * @createDate: 2021/8/16
    */
    private void validationCreateOrUpdateProjectRequest(CreateOrUpdateProjectRequest createOrUpdateProjectRequest) {
        if (createOrUpdateProjectRequest.getTitle().length() > 255) {
            throw new InvalidProjectRequestException(10023);
        } else if (!CategoryConstant.CATEGORY_LIST.contains(createOrUpdateProjectRequest.getCategory())) {
            throw new InvalidProjectRequestException(10024);
        }
    }
}

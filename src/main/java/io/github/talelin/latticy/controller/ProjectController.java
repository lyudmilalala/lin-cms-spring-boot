package io.github.talelin.latticy.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.talelin.autoconfigure.exception.NotFoundException;
import io.github.talelin.latticy.common.constant.CategoryConstant;
import io.github.talelin.latticy.common.exception.InvalidCategoryException;
import io.github.talelin.latticy.common.exception.InvalidProjectRequestException;
import io.github.talelin.latticy.model.ProjectDO;
import io.github.talelin.latticy.request.CreateOrUpdateProjectRequest;
import io.github.talelin.latticy.service.ProjectService;
import io.github.talelin.latticy.service.impl.ProjectServiceImpl;
import io.github.talelin.latticy.vo.PageResponseVO;
import io.github.talelin.latticy.vo.ProjectVO;
import io.github.talelin.latticy.vo.DeletedVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Positive;
import java.util.List;

/**
 * @description project controller
 * @author Yuchen Sun
 * @createDate 2021-08-16
 */
@RestController
@RequestMapping("/project")
@Validated
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    /**
     * Admin Interfaces
     */
    @RequestMapping(value="/admin/create", method= RequestMethod.POST)
    public ProjectVO createProject(HttpServletRequest request, @RequestBody CreateOrUpdateProjectRequest requestBody) {
        ProjectDO p = projectService.createProject(requestBody);
        if (p == null) {
            throw new NotFoundException(10022);
        }
        return new ProjectVO(7, p);
    }

    @RequestMapping(value="/admin/update", method= RequestMethod.POST)
    public ProjectVO updateProject(HttpServletRequest request, @RequestBody CreateOrUpdateProjectRequest requestBody) {
        ProjectDO p = projectService.updateProject(requestBody);
        if (p == null) {
            throw new NotFoundException(10022);
        }
        return new ProjectVO(8, p);
    }

    @RequestMapping(value="/admin/delete", method= RequestMethod.DELETE)
    public DeletedVO deleteProject(@RequestParam("projectId") @Positive(message = "{projectId.positive}") Integer projectId) {
        projectService.deleteProjectById(projectId);
        return new DeletedVO(3);
    }

    /**
     * User Interfaces
     */
    @RequestMapping(value="/user/getById", method= RequestMethod.GET)
    public ProjectVO updateProject(@RequestParam("projectId") @Positive(message = "{projectId.positive}") Integer projectId) {
        ProjectDO p = projectService.getProjectById(projectId);
        if (p == null) {
            throw new NotFoundException(10022);
        }
        return new ProjectVO(p);
    }

    @RequestMapping(value="/user/getPage", method= RequestMethod.GET)
    public PageResponseVO<ProjectDO> getProjectPage(@RequestParam("page") @Positive(message = "{page.number.min}") int pageNum, @RequestParam(value = "category", required = false)String category, @RequestParam(value = "searchContent", required = false)String searchContent) {
        if (category != null && !CategoryConstant.CATEGORY_LIST.contains(category)) {
            throw new InvalidCategoryException(10024);
        }
        IPage ipage = projectService.getProjectPage(pageNum, category, searchContent);
        PageResponseVO projectPage = new PageResponseVO(ipage);
        return projectPage;
    }


    @RequestMapping(value="/user/getList", method= RequestMethod.GET)
    public List<ProjectDO> getProjectList(@RequestParam(value = "category", required = false)String category, @RequestParam(value = "searchContent", required = false)String searchContent) {
        if (category != null && !CategoryConstant.CATEGORY_LIST.contains(category)) {
            throw new InvalidCategoryException(10024);
        }
        List<ProjectDO> projectList = projectService.getProjectList(category, searchContent);
        return projectList;
    }
}

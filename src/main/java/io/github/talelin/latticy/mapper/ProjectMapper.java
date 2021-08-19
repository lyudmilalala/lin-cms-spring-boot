package io.github.talelin.latticy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.talelin.latticy.model.ProjectDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author pedro@TaleLin
 */
@Repository
public interface ProjectMapper extends BaseMapper<ProjectDO> {

    List<ProjectDO> selectActive();

    List<ProjectDO> selectActiveByTitleLike(@Param("searchContent") String searchContent);

    List<ProjectDO> selectActiveByCategory(@Param("category") String category);

    List<ProjectDO> selectActiveByCategoryAndTitleLike(@Param("category") String category, @Param("searchContent") String searchContent);

    IPage selectActivePage(Page<ProjectDO> page);

    IPage selectActivePageByTitleLike(Page<ProjectDO> page, @Param("searchContent") String searchContent);

    IPage selectActivePageByCategory(Page<ProjectDO> page, @Param("category") String category);

    IPage selectActivePageByCategoryAndTitleLike(Page<ProjectDO> page, @Param("category") String category, @Param("searchContent") String searchContent);
}

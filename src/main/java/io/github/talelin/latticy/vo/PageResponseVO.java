package io.github.talelin.latticy.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页数据统一 view object
 *
 * @author pedro@TaleLin
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageResponseVO<T> {

    private Integer total;

    private List<T> items;

    private Integer page;

    private Integer count;

    private Integer pages;

    public PageResponseVO(IPage<T> ipage) {
        total = Math.toIntExact(ipage.getTotal());
        items = ipage.getRecords();
        page = Math.toIntExact(ipage.getCurrent());
        count = Math.toIntExact(ipage.getSize());
        pages = Math.toIntExact(ipage.getPages());
    }
}

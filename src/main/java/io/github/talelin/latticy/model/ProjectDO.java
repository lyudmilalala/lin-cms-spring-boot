package io.github.talelin.latticy.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author pedro@TaleLin
 * @author Juzi@TaleLin
 */
@Data
@TableName("project")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProjectDO extends BaseModel implements Serializable {

    private String title;
    private Integer author;
    private String content;
    private String category;

    public ProjectDO (String title, Integer author, String content, String category) {
        this.title = title;
        this.author = author;
        this.content = content;
        this.category = category;
    }
}

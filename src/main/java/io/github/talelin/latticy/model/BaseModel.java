package io.github.talelin.latticy.model;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

/**
 * @author Juzi@TaleLin
 */
@Data
public class BaseModel {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

//    @JsonIgnore
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;

//    @JsonIgnore
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableLogic
    @JsonIgnore
    private Date deleteTime;
}

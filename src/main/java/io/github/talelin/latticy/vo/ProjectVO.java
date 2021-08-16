package io.github.talelin.latticy.vo;

import io.github.talelin.autoconfigure.bean.Code;
import io.github.talelin.latticy.common.util.ResponseUtil;
import io.github.talelin.latticy.model.ProjectDO;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @author colorful@TaleLin
 */
@Data
public class ProjectVO extends UnifyResponseVO<String> {

    private ProjectDO project;

    public ProjectVO() {
        super(Code.SUCCESS.getCode());
        ResponseUtil.setCurrentResponseHttpStatus(HttpStatus.OK.value());
    }

    public ProjectVO(int code) {
        super(code);
        ResponseUtil.setCurrentResponseHttpStatus(HttpStatus.OK.value());
    }

    public ProjectVO(ProjectDO project) {
        super(Code.SUCCESS.getCode());
        this.project = project;
        ResponseUtil.setCurrentResponseHttpStatus(HttpStatus.OK.value());
    }

    public ProjectVO(int code, ProjectDO project) {
        super(code);
        this.project = project;
        ResponseUtil.setCurrentResponseHttpStatus(HttpStatus.OK.value());
    }

    public ProjectVO(String message) {
        super(message);
        ResponseUtil.setCurrentResponseHttpStatus(HttpStatus.OK.value());
    }

    public ProjectVO(int code, String message) {
        super(code, message);
        ResponseUtil.setCurrentResponseHttpStatus(HttpStatus.OK.value());
    }

    public ProjectVO(int code, String message, ProjectDO project) {
        super(code, message);
        this.project = project;
        ResponseUtil.setCurrentResponseHttpStatus(HttpStatus.OK.value());
    }
}

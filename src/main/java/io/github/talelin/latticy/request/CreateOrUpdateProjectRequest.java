package io.github.talelin.latticy.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
public class CreateOrUpdateProjectRequest {
    private Integer id;
    private String title;
    private String content;
    private String category;

    public CreateOrUpdateProjectRequest(String title, String content, String category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }

    public CreateOrUpdateProjectRequest(Integer id, String title, String content, String category) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.category = category;
    }
}

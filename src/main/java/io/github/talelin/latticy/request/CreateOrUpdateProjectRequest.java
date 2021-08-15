package io.github.talelin.latticy.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateOrUpdateProjectRequest {
    private String title;
    private String content;
    private String category;

    public CreateOrUpdateProjectRequest(String title, String content, String category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }
}

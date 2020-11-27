package org.jianchunchen.model.media.dtos;

import org.jianchunchen.model.annotation.IdEncrypt;
import lombok.Data;

@Data
public class CommentReplytDto {

    @IdEncrypt
    private Integer commentId;
    private String content;

}

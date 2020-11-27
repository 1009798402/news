package org.jianchunchen.model.article.dtos;

import org.jianchunchen.model.annotation.IdEncrypt;
import lombok.Data;

@Data
public class ArticleInfoDto {

    // 设备ID
    @IdEncrypt
    Integer equipmentId;
    // 文章ID
    @IdEncrypt
    Integer articleId;
    // 作者ID
    @IdEncrypt
    Integer authorId;

}

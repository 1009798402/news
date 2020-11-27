package org.jianchunchen.model.behavior.dtos;


import org.jianchunchen.model.annotation.IdEncrypt;
import org.jianchunchen.model.article.pojos.ApArticle;
import lombok.Data;

import java.util.List;

@Data
public class ShowBehaviorDto {

    // 设备ID
    @IdEncrypt
    Integer equipmentId;
    List<ApArticle> articleIds;

}

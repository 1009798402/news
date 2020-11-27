package org.jianchunchen.model.media.dtos;

import org.jianchunchen.model.annotation.IdEncrypt;
import lombok.Data;

@Data
public class WmMaterialDto {

    @IdEncrypt
    private Integer id;

//    private String url;
}

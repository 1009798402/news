package org.jianchunchen.model.user.dtos;

import org.jianchunchen.model.common.dtos.PageRequestDto;
import lombok.Data;

@Data
public class UserFansPageReqDto extends PageRequestDto {
    private String fansName;
    private Short level;
}

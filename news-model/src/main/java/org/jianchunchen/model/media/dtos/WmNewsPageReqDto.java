package org.jianchunchen.model.media.dtos;

import org.jianchunchen.model.annotation.IdEncrypt;
import org.jianchunchen.model.common.dtos.PageRequestDto;
import lombok.Data;

import java.util.Date;

@Data
public class WmNewsPageReqDto extends PageRequestDto {

    private Short status;
    private Date beginPubdate;
    private Date endPubdate;
    @IdEncrypt
    private Integer channelId;
    private String keyWord;
}

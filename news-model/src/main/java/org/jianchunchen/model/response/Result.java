package org.jianchunchen.model.response;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author hcrj
 * @version 1.0
 * @date 2020/8/27 11:12 上午
 */
@Data
public class Result<T> {

  private boolean success = false;
  private String msg;
  private String errMsg;
  private T data;
  private LocalDateTime timestamp;

  public Result(T data) {
    this.data = data;
  }

  public Result() {
  }

  public static <T> Result<T> ofSuccess(T data) {
    Result<T> result = new Result<T>(data);
    result.setSuccess(true);
    result.setTimestamp(LocalDateTime.now());
    return result;
  }
}

package com.quocchung.webbando.bandocongnghe.dto.response.generals;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseStatus {

  public static final String SUCCESS_LABEL = "Success";
  public static final String PERMISSION_DENIED_LABEL = "Permission Denied";
  public static String SUCCESS_CODE = "200";
  public static String PERMISSION_DENIED_CODE = "403";
  public static String PERMISSION_DENIED_MESSAGES = "Không có quyền thực hiện chức năng này";
  public static String SUCCESS_MESSAGE = "Thành công";
  public static final ResponseStatus SUCCESS_STATUS = new ResponseStatus(SUCCESS_CODE,
      SUCCESS_MESSAGE, SUCCESS_LABEL);
  public static String GENERAL_ERROR_MESSAGE = "Error happened";
  public static String GENERAL_ERROR_CODE = "40000";

  private String code;
  private String message;
  private String label;

}


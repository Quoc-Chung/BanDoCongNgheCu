package com.quocchung.webbando.bandocongnghe.dto.response.generals;

import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseFactory {
  public static <T> ResponseEntity<GeneralResponse<T>> success() {
    GeneralResponse<T> responseObject = new GeneralResponse<>();
    responseObject.setStatus(ResponseStatus.SUCCESS_STATUS);
    return ResponseEntity.ok(responseObject);
  }


  public static <T> GeneralResponse<T> getResponseObject(T data) {
    GeneralResponse<T> responseObject = new GeneralResponse<>();
    ResponseStatus responseStatus = new ResponseStatus();
    responseStatus.setCode(ResponseStatus.SUCCESS_CODE);
    responseStatus.setMessage(ResponseStatus.SUCCESS_MESSAGE);
    responseStatus.setLabel(ResponseStatus.SUCCESS_LABEL);
    responseObject.setStatus(responseStatus);
    responseObject.setData(data);
    return responseObject;
  }


  public static <T> ResponseEntity<GeneralResponse<T>> success(T data) {
    return ResponseEntity.ok(getResponseObject(data));
  }


  public static <T> ResponseEntity<GeneralResponse<T>> success(Object data, Class<T> clazz) {
    GeneralResponse<T> responseObject = new GeneralResponse<>();
    ResponseStatus responseStatus = new ResponseStatus();
    responseStatus.setCode(ResponseStatus.SUCCESS_CODE);
    responseStatus.setMessage(ResponseStatus.SUCCESS_MESSAGE);
    responseStatus.setLabel(ResponseStatus.SUCCESS_LABEL);
    responseObject.setStatus(responseStatus);
    responseObject.setData(clazz.cast(data));
    return ResponseEntity.ok(responseObject);
  }


  public static <T> ResponseEntity<GeneralResponse<T>> success(T data,
      Map<String, Object> extraData) {
    GeneralResponse<T> res = getResponseObject(data);
    res.setExtraData(extraData);
    return ResponseEntity.ok(res);
  }




  public static <T> ResponseEntity<GeneralResponse<T>> permissionDenied(String messages) {
    GeneralResponse<T> responseObject = new GeneralResponse<>();
    ResponseStatus responseStatus = new ResponseStatus();
    responseStatus.setCode(ResponseStatus.PERMISSION_DENIED_CODE);
    responseStatus.setLabel(ResponseStatus.PERMISSION_DENIED_LABEL);
    String messDisplay = ResponseStatus.PERMISSION_DENIED_MESSAGES;
    if (messages != null) {
      messDisplay = messDisplay + "." + messages;
    }
    responseStatus.setMessage(messDisplay);
    responseObject.setStatus(responseStatus);
    return new ResponseEntity<>(responseObject, HttpStatus.FORBIDDEN);
  }

  public static <T> ResponseEntity<GeneralResponse<T>> permissionDenied() {
    return permissionDenied(null);
  }
}

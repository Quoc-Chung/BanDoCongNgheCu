package com.quocchung.webbando.bandocongnghe.dto.response.generals;

import java.io.Serializable;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GeneralResponse<T> implements Serializable {
  private ResponseStatus status;
  private T data;
  private Map<String, Object> extraData;
}

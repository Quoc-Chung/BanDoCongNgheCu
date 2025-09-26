package com.quocchung.webbando.bandocongnghe.utils.CompositeKey;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostReactionId implements Serializable {
  private Long post;
  private Long user;
}

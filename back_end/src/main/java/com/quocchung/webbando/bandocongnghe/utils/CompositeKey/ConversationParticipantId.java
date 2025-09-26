package com.quocchung.webbando.bandocongnghe.utils.CompositeKey;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConversationParticipantId implements Serializable {
  private Long conversationId;
  private Long userId;
}

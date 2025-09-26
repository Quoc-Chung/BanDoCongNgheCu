package com.quocchung.webbando.bandocongnghe.entity;

import com.quocchung.webbando.bandocongnghe.utils.CompositeKey.ConversationParticipantId;
import com.quocchung.webbando.bandocongnghe.utils.enums.RoleConversation;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "conversation_participants")
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(ConversationParticipantId.class)
public class ConversationParticipant {

  @Id
  @Column(name = "conversation_id")
  private Long conversationId;

  @Id
  @Column(name = "user_id")
  private Long userId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "conversation_id", insertable = false, updatable = false)
  private Conversation conversation;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", insertable = false, updatable = false)
  private User user;

  @Enumerated(EnumType.STRING)
  private RoleConversation role;

  private LocalDateTime joinedAt;
}

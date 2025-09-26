package com.quocchung.webbando.bandocongnghe.entity;

import com.quocchung.webbando.bandocongnghe.utils.CompositeKey.PostReactionId;
import com.quocchung.webbando.bandocongnghe.utils.enums.ReactionType;
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
@Table(name = "post_reactions")
@IdClass(PostReactionId.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostReaction {

  @Id
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "post_id", nullable = false)
  private Post post;

  @Id
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Enumerated(EnumType.STRING)
  private ReactionType type;

  private LocalDateTime createdAt;
}

package com.quocchung.webbando.bandocongnghe.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

@Entity
@Table(name = "moderation_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ModerationLog {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "post_id", nullable = false)
  private Post post;


  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "moderator_id", nullable = false)
  private User moderator;

  private String action;

  private String reason;

  private LocalDateTime createdAt;
}

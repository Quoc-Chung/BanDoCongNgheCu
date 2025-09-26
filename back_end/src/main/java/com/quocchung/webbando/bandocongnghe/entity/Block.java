package com.quocchung.webbando.bandocongnghe.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "blocks")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Block {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "blocker_id", nullable = false)
  private User blocker;

  @ManyToOne
  @JoinColumn(name = "blocked_id", nullable = false)
  private User blocked;

  private LocalDateTime createdAt;
}

package com.quocchung.webbando.bandocongnghe.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "follows")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Follow {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "follower_id", nullable = false)
  private User follower;


  @ManyToOne
  @JoinColumn(name = "followee_id", nullable = false)
  private User followee;

  private LocalDateTime createdAt;
}

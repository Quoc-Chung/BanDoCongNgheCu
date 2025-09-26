package com.quocchung.webbando.bandocongnghe.entity;
import com.quocchung.webbando.bandocongnghe.utils.enums.MediaType;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "post_media")
public class PostMedia {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  private MediaType type;

  private String url;
  private String thumbnailUrl;
  private Long width;
  private Long height;
  private Long durationSeconds;
  private Long position;
  private LocalDateTime createdAt;

  @ManyToOne
  @JoinColumn(name = "post_id", nullable = false)
  private Post post;
}

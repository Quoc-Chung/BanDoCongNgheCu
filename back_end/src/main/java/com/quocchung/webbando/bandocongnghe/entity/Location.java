package com.quocchung.webbando.bandocongnghe.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "locations")
public class Location {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String country;
  private String state;
  private String city;

  @Column(name = "address_line")
  private String addressLine;

  private Double latitude;
  private Double longitude;

  private LocalDateTime createdAt = LocalDateTime.now();

  @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = false)
  private Set<Post> posts;
}

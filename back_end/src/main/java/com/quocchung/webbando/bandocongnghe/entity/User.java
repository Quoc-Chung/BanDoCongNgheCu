package com.quocchung.webbando.bandocongnghe.entity;

import com.quocchung.webbando.bandocongnghe.utils.enums.Gender;
import com.quocchung.webbando.bandocongnghe.utils.enums.UserStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
/**
 * Khi đăng kí và đăng nhập ta lưu username, password
 */
public class User  implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String email;

  /*- time confirmation -*/
  private LocalDateTime emailVarifiedAt;

  private String phone;
  private String displayName;
  private String username;
  private String passwordHard;
  private String avatarUrl;
  private String bio;

  @Enumerated(EnumType.STRING)
  private Gender gender;

  private LocalDate dob;
  private String countryCode;
  private LocalDateTime createdAt;
  private LocalDateTime updateAt;
  private LocalDateTime deletedAt;


  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private UserStatus status = UserStatus.ACTIVE;
  private Integer violationCount = 0;
  private LocalDateTime lastSuspendedAt;

  @ManyToMany(fetch= FetchType.EAGER)
  @JoinTable(
      name = "users_roles",
      joinColumns = @JoinColumn(name="user_id"),
      inverseJoinColumns = @JoinColumn(name="role_id")
  )
  private Set<Role> roles;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<AuthAccount> authAccounts = new HashSet<>();


  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<EmailVerificationToken> emailVerificationTokens = new HashSet<>();

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<PasswordResetOtp> passwordResetOtps = new HashSet<>();

  @OneToMany(mappedBy = "follower")
  private Set<Follow> following = new HashSet<>();

  @OneToMany(mappedBy = "followee")
  private Set<Follow> followers = new HashSet<>();

  @OneToMany(mappedBy = "blocker")
  private Set<Block> blocking = new HashSet<>();

  @OneToMany(mappedBy = "blocked")
  private Set<Block> blockedBy = new HashSet<>();

  @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Comment> comments = new HashSet<>();

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<PostReaction> postReactions = new HashSet<>();

  @OneToMany(mappedBy = "reporter", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Report> reportsMade = new HashSet<>();

  @OneToMany(mappedBy = "moderator", cascade = CascadeType.ALL)
  private Set<ModerationLog> moderationActions = new HashSet<>();

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private Set<Notification>  notifications = new HashSet<>();


  @OneToMany(mappedBy = "fromUser", cascade = CascadeType.ALL)
  private Set<Notification>  notificationss = new HashSet<>();

  /**
   *
   * @return tập hợp quyền (authorities/ roles) của User
   */
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return roles.stream().map(role -> new SimpleGrantedAuthority(role.getCode().name())).collect(
        Collectors.toSet());
  }

  /**
   *
   * @return mật khẩu đã được mã hóa
   */
  @Override
  public String getPassword() {
    return passwordHard;
  }

  /**
   * true nghĩa là tài khoản chưa hết hạn.
   * @return
   */
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  /**
   * Nếu false → login bị từ chối với lỗi account locked.
   * @return
   */
  @Override
  public boolean isAccountNonLocked() {
    return status != UserStatus.SUSPENDED;
  }

  /**
   * true nếu mật khẩu chưa hết hạn
   * @return
   */
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  /**
   * Xác định tài khoản đã được kích hoạt hay chưa
   * @return
   */
  @Override
  public boolean isEnabled() {
    return emailVarifiedAt != null;
  }
}

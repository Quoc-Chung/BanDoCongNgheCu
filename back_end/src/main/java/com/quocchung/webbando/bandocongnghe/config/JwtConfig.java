package com.quocchung.webbando.bandocongnghe.config;

import com.quocchung.webbando.bandocongnghe.exceptions.PrivateKeyInitializationException;
import jakarta.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/**
 * Configuration class responsible for loading and providing RSA private and public keys used for
 * signing and validating JWT tokens.
 * <p>
 * It reads PEM-formatted key files and converts them into {@link PrivateKey} and {@link PublicKey}
 * objects.
 * </p>
 */
@Component
public class JwtConfig {

  @Value("${jwt.private-key-path}")
  private Resource privateKeyResource;

  @Value("${jwt.public-key-path}")
  private Resource publicKeyResource;

  /**
   * JWT expiration time in milliseconds.
   */
  @Getter
  @Value("${jwt.expiration}")
  private long expirationTime;

  /**
   * Loaded RSA private key.
   */
  @Getter
  private PrivateKey privateKey;

  /**
   * Loaded RSA public key.
   */
  @Getter
  private PublicKey publicKey;

  /**
   * Initializes the RSA key pair after the bean is constructed.
   *
   * @throws PrivateKeyInitializationException if any key fails to load.
   */
  @PostConstruct
  public void init() {
    try {
      this.privateKey = readPrivateKey(privateKeyResource);
      this.publicKey = readPublicKey(publicKeyResource);
    } catch (Exception e) {
      throw new PrivateKeyInitializationException("Failed to initialize RSA keys", e);
    }
  }
  /**
   * Reads a private RSA key from a PEM file.
   *
   * @param resource the resource pointing to the PEM file
   * @return the {@link PrivateKey} object
   */
  private PrivateKey readPrivateKey(Resource resource) {
    try {
      String key = readKeyFromPem(resource);
      byte[] decoded = Base64.getDecoder().decode(key);
      PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decoded);
      return KeyFactory.getInstance("RSA").generatePrivate(keySpec);
    } catch (Exception e) {
      throw new PrivateKeyInitializationException("Failed to parse private key", e);
    }
  }

  /**
   * Reads a public RSA key from a PEM file.
   *
   * @param resource the resource pointing to the PEM file
   * @return the {@link PublicKey} object
   */
  private PublicKey readPublicKey(Resource resource) {
    try {
      String key = readKeyFromPem(resource);
      byte[] decoded = Base64.getDecoder().decode(key);
      X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decoded);
      return KeyFactory.getInstance("RSA").generatePublic(keySpec);
    } catch (Exception e) {
      throw new PrivateKeyInitializationException("Failed to parse public key", e);
    }
  }

  /**
   * Reads and extracts base64 content from a PEM file, ignoring header and footer lines.
   *
   * @param resource the resource pointing to the PEM file
   * @return the base64 encoded key string
   */
  private String readKeyFromPem(Resource resource) {
    try (BufferedReader reader = new BufferedReader(
        new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))) {

      StringBuilder keyBuilder = new StringBuilder();
      String line;
      while ((line = reader.readLine()) != null) {
        if (line.contains("BEGIN") || line.contains("END")) {
          continue;
        }
        keyBuilder.append(line.trim());
      }
      return keyBuilder.toString();

    } catch (IOException e) {
      throw new PrivateKeyInitializationException("Failed to read key from PEM file", e);
    }
  }
}

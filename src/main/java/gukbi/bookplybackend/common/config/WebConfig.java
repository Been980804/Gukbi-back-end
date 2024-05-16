package gukbi.bookplybackend.common.config;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Value("${jasypt.encryptor.password}")
  private String password;
  
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
            .allowedOrigins("http://localhost:3000")
            .allowedMethods("GET", "POST", "PUT", "DELETE")
            .maxAge(3000);
  }

  @Bean
  public StringEncryptor stringEncryptor() {
    PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
    SimpleStringPBEConfig config = new SimpleStringPBEConfig();
    config.setPassword(password); // 사용할 기본 비밀번호 설정
    config.setAlgorithm("PBEWITHHMACSHA512ANDAES_256"); // 사용할 암호화 알고리즘 설정
    config.setPoolSize("1"); // 인스턴스 풀 사이즈 설정
    config.setStringOutputType("base64"); // 인코딩 방식 설정
    config.setKeyObtentionIterations("1000");
    config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
    config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
    encryptor.setConfig(config);

    return encryptor;
  }
}

package com.example.demo.util.config;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Component
@ConfigurationProperties(prefix = "custom")
public class CustomPropertis {

  private CustomPropertis.ShiroProperties shiro = new CustomPropertis.ShiroProperties();

  private CustomPropertis.CaptchaProperties captcha = new CustomPropertis.CaptchaProperties();

  @Data
  public class ShiroProperties {
    private String anonUrl;

    private String loginUrl = "/login";

    private String successUrl = "/index";

    private String logoutUrl = "/logout";

    private String unauthorizedUrl = "/403";
  }

  @Data
  public class CaptchaProperties {

    /**
     * 验证码图片宽度
     */
    private int width = 146;

    /**
     *验证码图片高度
     */
    private int height = 33;

    /**
     *验证码字符位数
     */
    private int length = 4;
  }

}

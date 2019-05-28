package com.example.demo.util.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.util.Date;

/**
 * 用来进行签名和效验Token
 *
 * @author wenfs
 * @create 2018-09-30 14:19
 **/
public class JwtUtil {

  /**
   * 一小时
   */
  private static final long ONE_HOUR = 60 * 1000;

  /**
   * 一天
   */
  private static final long ONE_DATE = 24 * ONE_HOUR;

  /**
   * 过期时间 2天
   */
  private static final long EXPIRE_TIME = 2 * ONE_DATE;

  /**
   * 用户名
   */
  private static final String USERNAME = "username";



  /**
   * 生成签名 token
   *
   * @param userName 用户名
   * @param passWord   密码
   * @return 加密的TOKEN
   */
  public static String sign(String userName, String passWord) {
    try {
      Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);

      Algorithm algorithm = Algorithm.HMAC256(passWord);
      // 附带用户信息
      return JWT.create().withClaim(USERNAME, userName).withExpiresAt(date).sign(algorithm);
    } catch (Exception e) {
      return null;
    }
  }

  /**
   * 校验token是否正确
   *
   * @param token    密钥
   * @param userName 用户名
   * @param passWord   用户的密码
   * @return 正确: true；不正确：false
   */
  public static boolean verify(String token, String userName, String passWord) {

    try {
      // 根据密码生成JWT校验器
      Algorithm algorithm = Algorithm.HMAC256(passWord);

      JWTVerifier verifier = JWT.require(algorithm).withClaim(USERNAME, userName).build();
      // 校验TOKEN
      DecodedJWT jwt = verifier.verify(token);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * 获取用户名
   *
   * @param token token中包含了用户名
   * @return
   */
  public static String getUserName(String token) {
    try {
      DecodedJWT jwt = JWT.decode(token);
      return jwt.getClaim(USERNAME).asString();
    } catch (Exception e) {
      return null;
    }
  }


}

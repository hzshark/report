package cn.xp.hashpower.util;

import cn.xp.hashpower.common.CommonConstants;
import cn.xp.hashpower.common.exception.BizException;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * JWT身份认证创建器
 * Created by wanghf on 2017/2/12.
 */
public class JwtHelper {

    private static Logger logger = LoggerFactory.getLogger(JwtHelper.class);

    /**
     * JWT身份认证检查器
     * @param jsonWebToken
     * @return
     */
    public static String verifyAccessTokenFromJWT(String jsonWebToken) throws BizException {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(CommonConstants.BESTSIGN_APPSECRET))
                    .parseClaimsJws(jsonWebToken).getBody();
            if (claims != null){
                String accessToken = (String)claims.get("access_token");
                logger.debug("claims access_token ={}",accessToken);
                return accessToken;
            }
        } catch (ExpiredJwtException e) {
            logger.error("verifyAccessTokenFromJWT expired  jwt={} error={}",jsonWebToken,e.getMessage());
            throw new BizException(110002, "verifyAccessTokenFromJWT expired  jwt="+jsonWebToken+" error={"+e.getMessage()+"}");
        } catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException e) {
            logger.error("verifyAccessTokenFromJWT error  jwt={} error={}",jsonWebToken,e.getMessage());
            throw new BizException(110005, "verifyAccessTokenFromJWT error jwt="+jsonWebToken+" error={"+e.getMessage()+"}");
        } catch (SignatureException e) {
            logger.error("verifyAccessTokenFromJWT error  jwt={} error={}",jsonWebToken,e.getMessage());
            throw new BizException(110007, "verifyAccessTokenFromJWT error jwt="+jsonWebToken+" error={"+e.getMessage()+"}");
        }
        return null;
    }

    /**
     * 创建JWT
     * @param accessToken 该JWT的通行证ID
     * @param audience 该JWT的接收方
     * @param issuer  该JWT的签发者
     * @param ttlSecondss 该JWT的有效期，单位秒
     * @return
     */
    public static String createJWT(String accessToken,
                                   String audience, String issuer, long ttlSecondss)
    {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //生成签名密钥
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(CommonConstants.BESTSIGN_APPSECRET);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //添加构成JWT的参数
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
                .claim("access_token", accessToken)
                .setIssuer(issuer)
                .setAudience(audience)
                .signWith(signatureAlgorithm, signingKey);
        //添加Token过期时间
        if (ttlSecondss >= 0) {
            long expMillis = nowMillis + ttlSecondss*1000;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp).setNotBefore(now);
        }

        //生成JWT
        return builder.compact();
    }

}
package cn.xp.hashpower.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户登录信息封装
 * 
 */
@Data
public class SessionUser  implements Serializable{

	private String token;


	private int userId;

    private String nickname;

    private String loginpwd  ;

    private String email  ;

    private String phone  ;

    private String paypwd;


}

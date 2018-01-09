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

    private String login_name = "";

    private String loginpwd = "";

    private String email = "";

    private String phone = "";

	/*public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLogin_name() {
		return login_name;
	}

	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}

	public String getLoginpwd() {
		return loginpwd;
	}

	public void setLoginpwd(String loginpwd) {
		this.loginpwd = loginpwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}*/
}

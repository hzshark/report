package cn.xp.hashpower.dao;

import cn.xp.hashpower.model.SessionUser;
import org.apache.ibatis.annotations.*;

@Mapper
public interface SysAccountMapper extends BaseMapper<SessionUser, Long> {

    @Select("select * from u_account where userid = #{0}")
    SessionUser selectByUserId(int userId);

    @Select("select * from u_account where nickname=#{0} limit 1")
    SessionUser selectByAccount(String account);

    @Select("select count(1) from u_account where nickname=#{0}")
    int countByAccount(String account);

    @Update(" update u_account set loginpwd=#{arg1} where userid=#{arg0}  ")
    void modifyLoginUserPwd(int userId, String pwd);

    @Update(" update u_account set paypwd=#{arg1} where userid=#{arg0}  ")
    void modifyPayUserPwd(int userId, String pwd);

    @Insert("insert into u_account(email,nickname,loginpwd) values(#{user.email},#{user.nickname},#{user.loginpwd})")
    @Options(useGeneratedKeys = true, keyProperty = "user.userId")
   //boolean adduser(String email,String nickname,String pwd);
    boolean adduser(@Param("user") SessionUser user);

    @Insert("INSERT INTO u_wallet (uid,wallet_address) VALUES (#{arg0},#{arg1}")
    boolean setUserBtwallet(int userid,String address);

    @Select("select wallet_address from u_wallet where uid =#{arg0} and type=#{arg1}")
    String getUserWalletAddress(int userId, int i);

    @Select("select auth_status from u_account where userid = #{arg0}")
    String getUserAuthStatus(int userId);

    @Update(" update u_account set gauthsecret=#{arg1} where userid=#{arg0} ")
    void setGoogleAuthSecret(int userId, String secret);


}
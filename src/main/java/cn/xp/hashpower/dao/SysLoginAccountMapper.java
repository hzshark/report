package cn.xp.hashpower.dao;

import cn.xp.hashpower.model.SessionUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface SysLoginAccountMapper extends BaseMapper<SessionUser, Long> {

    @Select("select * from u_account where userid = #{0}")
    SessionUser selectByUserId(int userId);

    @Select("select * from u_account where login_name=#{0} limit 1")
    SessionUser selectByAccount(String account);

    @Select("select count(1) from u_account where login_name=#{0}")
    int countByAccount(String account);

    @Update(" update u_account set loginpwd=#{arg1} where userid=#{arg1})  ")
    void modifyUserPwd(int userId,String pwd);

   @Insert("insert into u_account(phone,loginpwd) values(#{arg0},#{arg1})")
   boolean adduser(String phone,String pwd);

}
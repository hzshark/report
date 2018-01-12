package cn.xp.hashpower.dao;

import cn.xp.hashpower.model.SessionUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserTradeMapper extends BaseMapper<SessionUser, Integer>  {

    @Update(" update u_account set loginpwd=#{arg1} where userid=#{arg1})  ")
    void setUserTradePwd(int userId,String pwd);

}

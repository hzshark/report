package cn.xp.report.dao;

import cn.xp.report.model.CoinInfo;
import cn.xp.report.model.MachineInfo;
import cn.xp.report.model.SessionUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
public interface WealthMapper extends BaseMapper<SessionUser, Integer> {

    @Select("select * from u_wealth where userid= #{userId}")
    List<CoinInfo> selectUserWealthList0(SessionUser user);

    @Select("select * from u_wealth where userid= #{0}")
    List<CoinInfo> selectUserWealthList(Integer uid);

    @Select("SELECT c.name, w.amount FROM coin_item AS c ,u_wealth AS w where w.userid = #{userId} and c.ID = #{1}")
    CoinInfo selectUserWealthByCoinId(SessionUser user,Integer conid);


    //INSERT replace `u_wealth` (`userid`, `coinid`, `amount`) VALUES ('100', '1', '0.0004')

    @Transactional
    @Insert("UPDATE u_wealth SET amount=amount+#{2} WHERE (userid=#{userId}) AND (coinid=#{1}) LIMIT 1")
    boolean increatUserCoin(SessionUser usr,Integer coinId,Double amount);

    @Transactional
    @Insert("INSERT INTO u_wealth_log (userid,coinid, amount) VALUES ( #{userId},#{1},#{2} )")
    boolean AddUserCoinTransction(SessionUser usr,Integer coinId,Double amount);


    @Select("select count(*) from u_wealth where coinid = #{0}")
    Integer countUserCoinByCid(Integer coinid);
/*
    @Select("select * from machine_item")
    List<MachineInfo> selectList();
*/
}
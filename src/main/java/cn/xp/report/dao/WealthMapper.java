package cn.xp.report.dao;

import cn.xp.report.model.CoinInfo;
import cn.xp.report.model.CoinItem;
import cn.xp.report.model.MachineInfo;
import cn.xp.report.model.SessionUser;
import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
public interface WealthMapper extends BaseMapper<SessionUser, Integer> {

/*    @Select("select * from u_wealth where userid= #{userId}")
    List<CoinInfo> selectUserWealthList(SessionUser user);*/

    @Select("call QUserCoins(#{0},null) ;")
    List<CoinItem> selectUserWealthList(Integer uid);

    //@Select("call QUserCoins(#{param1},#{param2}) ;")
    @Select("call QUserCoins(#{arg0},#{arg1}) ;")
    List<CoinItem> selectUserWealthListByCid(int  uid,int  coind);

    @Select("SELECT * FROM coin_category  where id =#{1} and enable=1")
    CoinInfo getConinInfo(int coinId);

    @Select("SELECT * FROM coin_category  where  enable=1")
    List<CoinInfo> getConinInfoList();

    @Select("call QUserCoinLog(#{arg0},#{arg1})  ")
    List<CoinItem> selectUserWealthLog(int  uid,int  coinId);


    //selectUserWealthDetaLst

      //      selectUserWealthDetaLstByCid
    //@Select("select * from dual where 1=#{uid} and 2=#{cid}")
    //List<CoinInfo> selectUserWealthListByCid(@Param("uid") int  uid,@Param("cid") long  coind);

   /* @Select("SELECT c.name, w.amount FROM coin_item AS c ,u_wealth AS w where w.userid = #{userId} and c.ID = #{1}")
    CoinInfo selectUserWealthByCoinId(SessionUser user,Integer conid);*/


    //INSERT replace `u_wealth` (`userid`, `coinid`, `amount`) VALUES ('100', '1', '0.0004')

    @Transactional
    @Insert("UPDATE u_wealth SET amount=amount+#{2} WHERE (userid=#{userId}) AND (coinid=#{1}) LIMIT 1")
    boolean increatUserCoin(SessionUser usr,Integer coinId,Double amount);

    @Transactional
    @Insert("INSERT INTO u_wealth_log (userid,coinid, amount) VALUES ( #{userId},#{1},#{2} )")
    boolean AddUserCoinTransction(SessionUser usr,Integer coinId,Double amount);


    /*@Select("select count(*) from u_wealth where coinid = #{0}")
    Integer countUserCoinByCid(Integer coinid);*/
/*
    @Select("select * from machine_item")
    List<MachineInfo> selectList();
*/
}
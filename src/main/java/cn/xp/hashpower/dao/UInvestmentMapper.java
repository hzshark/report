package cn.xp.hashpower.dao;

import cn.xp.hashpower.model.MachineInfo;
import cn.xp.hashpower.model.MachineItem;
import cn.xp.hashpower.model.UInvestment;
import cn.xp.hashpower.model.UMachine;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface UInvestmentMapper extends BaseMapper{



 /*   @Select("CALL QuserInvestMentItem(#{arg0},#{arg1}) ")
    List<UInvestment> selectUserInvestmentListByConid(int userId, int coninid);*/

    @Select("CALL QuserI(#{0},null) ")
    List<UInvestment> selectUserMachineList(int uid);

    @Select("SELECT c.id,c.name,t.amount,t.trade_id,t.saledesc FROM investment_item AS t, investment_category AS c" +
            " WHERE t.enable = 1 AND c.enable=1 AND t.id = c.id")
    List<UInvestment> selectSaleInvestmentList();

 /*   //arg3 批次先不用
    @Select("Call SBuyMachine(#{arg0},#{arg1},#{arg2},0 )")
    int BuyInvestment(int userId, int mid, BigDecimal amount);*/

    @Select("SELECT * from u_investment where userid= #{arg0}")
    List<UInvestment> selectUInvestment(int uid);

    @Select("SELECT * from u_investment where userid= #{arg0} and ")
    List<UInvestment> selectUInvestmentByCid(int uid, int coinId);

    @Select("Call SBuyInvestment(#{arg0},#{arg1},#{arg2} )")
    int BuyInvestMnet(int userId,  Double amount, int trad_id);
}
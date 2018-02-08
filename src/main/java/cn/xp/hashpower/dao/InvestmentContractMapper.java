package cn.xp.hashpower.dao;

import cn.xp.hashpower.model.InvestMentContract;
import cn.xp.hashpower.model.UInvestment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface InvestmentContractMapper extends BaseMapper{



 /*   @Select("CALL QuserInvestMentItem(#{arg0},#{arg1}) ")
    List<UInvestment> selectUserInvestmentListByConid(int userId, int coninid);*/

    /*@Select("CALL QuserI(#{0},null) ")
    List<UInvestment> selectUserMachineList(int uid);
*/
    @Select(" SELECT ic.contractId, ic.`name`, ic.duration,ic.interest,ic.coinId FROM coin_category AS cc , investment_contract AS ic" +
            " WHERE cc.`enable` = 1 AND ic.`enable` = 1  AND cc.ID = ic.coinId ")
    List<InvestMentContract> selectInvestmentContract();

 /*   //arg3 批次先不用
    @Select("Call SBuyMachine(#{arg0},#{arg1},#{arg2},0 )")
    int BuyInvestment(int userId, int mid, BigDecimal amount);*/

    @Select("SELECT * from u_investment where uid= #{arg0}")
    List<UInvestment> selectUInvestment(int uid);

    @Select("SELECT * from u_investment where uid= #{arg0} and ")
    List<UInvestment> selectUInvestmentByCid(int uid, int coinId);
}
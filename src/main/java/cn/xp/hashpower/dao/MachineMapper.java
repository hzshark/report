package cn.xp.hashpower.dao;

import cn.xp.hashpower.model.MachineInfo;
import cn.xp.hashpower.model.MachineItem;
import cn.xp.hashpower.model.SessionUser;
import cn.xp.hashpower.model.UMachine;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MachineMapper extends BaseMapper{

    /*@Select("select * from machine_item where machine_id = #{0}")
    SessionUser selectByMachinerId(String userId);

    @Select("select * from machine_item where machine_name=#{0} limit 1")
    SessionUser selectByMachineName(String name);
*/
    @Select("select count(1) from machine_item where machine_name=#{0}")
    int countByMachineName(String account);

    @Select("CALL QuserMachines(#{arg0},#{arg1}) ")
    List<MachineInfo> selectUserMachineListByMid(int userId,int mid);

    @Select("CALL QuserMachines(#{0},null) ")
    List<MachineInfo> selectUserMachineList(int uid);

    @Select("SELECT c.machine_id,c.machine_name,t.amount,t.trade_id,t.saledesc FROM machine_item AS t, machine_category AS c" +
            " WHERE t.enable = 1 AND c.enable=1 AND t.machine_id = c.machine_id")
    List<MachineItem> selectSaleMachineList();

    //arg3 批次先不用
    @Select("Call SBuyMachine(#{arg0},#{arg1},#{arg2},0 )")
    int BuyMachine(int userId, int mid, double amount);

    @Select("SELECT * from u_machine")
    List<UMachine> selectUMachine();
}
package cn.xp.report.dao;

import cn.xp.report.model.MachineInfo;
import cn.xp.report.model.MachineItem;
import cn.xp.report.model.SessionUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MachineMapper extends BaseMapper<SessionUser, Long> {

    /*@Select("select * from machine_item where machine_id = #{0}")
    SessionUser selectByMachinerId(String userId);

    @Select("select * from machine_item where machine_name=#{0} limit 1")
    SessionUser selectByMachineName(String name);
*/
    @Select("select count(1) from machine_item where machine_name=#{0}")
    int countByMachineName(String account);

    @Select("CALL QuserMachines(#{0},#{1}) ")
    List<MachineInfo> selectUserMachineListByMid(int userId,int mid);

    @Select("CALL QuserMachines(#{0},null) ")
    List<MachineInfo> selectUserMachineList(int uid);

    @Select("SELECT c.machine_id,c.machine_name,t.amount,t.tradeid,t.saledesc FROM machine_item AS t, machine_category AS c" +
            " WHERE t.enable = 1 AND c.enable=1 AND t.machine_id = c.machine_id")
    List<MachineItem> selectSaleMachineList();

}
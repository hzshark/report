package cn.xp.hashpower.dao;

import cn.xp.hashpower.model.UOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UOrderMapper extends BaseMapper<UOrder, Integer>  {

    /*@Select("select  pay_time,status`,order_id,userid,pay_type,pay_amount,capture_Set from u_order where userid=#{arg0} and status=#{arg2}")
    public  List<UOrder> listUorderByStatus(int userid,int status);*/

    public UOrder  findByOid(int id);
}

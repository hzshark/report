package cn.xp.hashpower.service;


import cn.xp.hashpower.dao.UOrderMapper;
import cn.xp.hashpower.model.UOrder;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class UOrderManagerService {

    UOrderMapper mapper;

    //待支付订单
    public PageInfo<UOrder> listOpenOrder(int userid)
    {
        return  mapper.listUorderByStatus(userid,1);
    }

    //已完成的订单
    public PageInfo<UOrder> listCloseOrder(int userid)
    {
        return  mapper.listUorderByStatus(userid,0);
    }

    //取消的订单
    public PageInfo<UOrder> listCancelOrder(int userid)
    {
        return  mapper.listUorderByStatus(userid,2);
    }
}

package cn.xp.report.service;

import cn.xp.report.dao.MachineMapper;
import cn.xp.report.dao.WealthMapper;
import cn.xp.report.model.CoinInfo;
import cn.xp.report.model.CoinItem;
import cn.xp.report.model.MachineInfo;
import cn.xp.report.model.SessionUser;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoinManageService {
    private static Logger logger = LoggerFactory.getLogger(CoinManageService.class);

    @Autowired
    private WealthMapper mapper;

    public PageInfo<CoinItem> getCoinList(int pageNo, int pageSize,int uid,int coinId){
        PageHelper.startPage(pageNo, pageSize);
        List<CoinItem> groupList;
        if (coinId<=0)
            groupList = mapper.selectUserWealthList(uid);
        else
            groupList = mapper.selectUserWealthListByCid(uid,coinId);
        PageInfo<CoinItem> pageInfo= new PageInfo<CoinItem>(groupList);
        return pageInfo;
    }


    public PageInfo<CoinItem> getCoinLogs(int pNo, int pSize, int userId, int coinId) {
        PageHelper.startPage(pNo, pSize);
        List<CoinItem> groupList;
        groupList = mapper.selectUserWealthLog(userId,coinId);
        PageInfo<CoinItem> pageInfo= new PageInfo<CoinItem>(groupList);
        return pageInfo;
    }


    private CoinInfo getConinfo(int coinId)
    {
        return mapper.getConinInfo(coinId);
    }

    private List<CoinInfo> getConinfoList()
    {
        return mapper.getConinInfoList();
    }
}

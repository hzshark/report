package cn.xp.report.service;

import cn.xp.report.dao.MachineMapper;
import cn.xp.report.dao.WealthMapper;
import cn.xp.report.model.CoinInfo;
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

    public PageInfo<CoinInfo> getCoinList(int pageNo, int pageSize,int uid){
        PageHelper.startPage(pageNo, pageSize);
        List<CoinInfo> groupList = mapper.selectUserWealthList(uid);
        PageInfo<CoinInfo> pageInfo= new PageInfo<CoinInfo>(groupList);
        return pageInfo;
    }


}

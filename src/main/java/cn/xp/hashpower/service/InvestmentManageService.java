package cn.xp.hashpower.service;

import cn.xp.hashpower.dao.InvestmentContractMapper;
import cn.xp.hashpower.dao.InvestmentMapper;
import cn.xp.hashpower.model.CoinInfo;
import cn.xp.hashpower.model.CoinItem;
import cn.xp.hashpower.model.InvestMentContract;
import cn.xp.hashpower.model.UInvestment;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InvestmentManageService {
    private static Logger logger = LoggerFactory.getLogger(InvestmentManageService.class);


    @Autowired
    private InvestmentMapper mapper;

    @Autowired
    private InvestmentContractMapper investmentContractMapper;

    public PageInfo<UInvestment> getUserInvestMentList(int pageNo, int pageSize,int uid,int coinId){
        PageHelper.startPage(pageNo, pageSize);
        List<UInvestment> groupList;
        if (coinId<=0)
            groupList = mapper.selectUInvestment(uid);
        else
            groupList = mapper.selectUInvestmentByCid(uid,coinId);
        if (groupList==null)
            groupList=new ArrayList<>();
        PageInfo<UInvestment> pageInfo= new PageInfo<UInvestment>(groupList);
        return pageInfo;
    }

    @Cacheable(value = "localCache" )
    public List<InvestMentContract> SelectInvestmentContractBycoinId(int coinid)
    {
            List<InvestMentContract> list =investmentContractMapper.selectInvestmentContract() ;
            int len=list.size();
            InvestMentContract item;
            for ( int i=0;i<len;i++)
            {
                 item=list.get(i);
                 if (item.getCoinId()!=coinid)
                     list.remove(i);
            }
            return  list;
    }


    public PageInfo<UInvestment> getUInvestments(int pNo, int pSize, int userId, int userid) {
        PageHelper.startPage(pNo, pSize);
        List<UInvestment> groupList;
        groupList = mapper.selectUInvestment(userId);
        PageInfo<UInvestment> pageInfo= new PageInfo<UInvestment>(groupList);
        return pageInfo;
    }


   /* private CoinInfo getConinfo(int coinId)
    {
        return mapper.getConinInfo(coinId);
    }

    private List<CoinInfo> getConinfoList()
    {
        return mapper.getConinInfoList();
    }*/
}

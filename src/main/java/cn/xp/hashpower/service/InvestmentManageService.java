package cn.xp.hashpower.service;

import cn.xp.hashpower.dao.InvestmentItemMapper;
import cn.xp.hashpower.dao.UInvestmentMapper;
import cn.xp.hashpower.model.InvestMentItem;
import cn.xp.hashpower.model.UInvestment;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Log4j
@Service
public class InvestmentManageService {
    private static Logger logger = LoggerFactory.getLogger(InvestmentManageService.class);


    @Autowired
    private UInvestmentMapper mapper;

    @Autowired
    private InvestmentItemMapper investmentContractMapper;

    public PageInfo<UInvestment> getUserInvestMentList(int pageNo, int pageSize,int uid,int coinId){
        PageHelper.startPage(pageNo, pageSize);
        List<UInvestment> groupList;
       groupList=getUInvestment(uid,coinId);
        PageInfo<UInvestment> pageInfo= new PageInfo<UInvestment>(groupList);
        return pageInfo;
    }

    @Test
    public  void Test0()
    {
        List<UInvestment> groupList=getUInvestment(22,100);
        log.info(groupList);
    }


    @Cacheable(value = "localCache" )
    private List<UInvestment> getUInvestment(int userid,int coinId)
    {
        List<UInvestment> groupList;

        groupList = mapper.selectUInvestment(userid);

        if (coinId>0) {
            Iterator<UInvestment> iterator = groupList.iterator();
            UInvestment item;
            while (iterator.hasNext()) {
                item = iterator.next();
                if (item.getCointype() != coinId)
                    iterator.remove();
            }
        }
        return groupList;
    }

    @Cacheable(value = "localCache" )
    public List<InvestMentItem> SelectInvestmentContractBycoinId(int coinid)
    {
            List<InvestMentItem> list =investmentContractMapper.selectInvestmentContract() ;
            int len=list.size();
            InvestMentItem item;
            for ( int i=0;i<len;i++)
            {
                 item=list.get(i);
                 if (item.getCointype()!=coinid)
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

    @CacheEvict(value = "localCache")
    public int BuyInvestMnet(int userId, Double amount,int trad_id)
    {
        return mapper.BuyInvestMnet(userId,amount,trad_id);
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

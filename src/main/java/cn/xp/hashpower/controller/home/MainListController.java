package cn.xp.hashpower.controller.home;

import cn.xp.hashpower.common.Constants;
import cn.xp.hashpower.common.annotation.SystemControllerLog;
import cn.xp.hashpower.common.exception.BizException;
import cn.xp.hashpower.common.rule.ParamsChecker;
import cn.xp.hashpower.common.util.StringUtil;
import cn.xp.hashpower.controller.BaseController;
import cn.xp.hashpower.model.InvestMentContract;
import cn.xp.hashpower.model.MachineItem;
import cn.xp.hashpower.service.InvestmentManageService;
import cn.xp.hashpower.service.MachineManageService;
import cn.xp.hashpower.vo.ListVO;
import cn.xp.hashpower.vo.ResultVO;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/10/29.
 */
@RestController
@RequestMapping("/salelist")
public class MainListController extends BaseController {

    @Autowired
    MachineManageService service;

    @Autowired
    InvestmentManageService investmentManageService;

    /*
    可售机器的列表
     */
    @RequestMapping(value = "/machines",  method = RequestMethod.GET)
    @SystemControllerLog(description = "/salelist/machines" )
    public ListVO ListSaleMachineItem(@RequestParam(value = "page",required= false)  String page,@RequestParam(value = "limit",required= false) String limit ){
        ListVO listVO = new ListVO();
        int pNo=0,pSize=10;
        pNo= StringUtil.toInt(page);
        pSize=Math.min(StringUtil.toInt(limit),10);
        try {
            PageInfo<MachineItem> pageInfo = service.getSaleMachineList(pNo, pSize);
            long count = 0;
            if(pageInfo != null){
                //分页
                count = pageInfo.getTotal();
               // listVO.setList(pageInfo.getList());
                listVO.setData(pageInfo.getList());
                listVO.setSucessMsg();
            }
            listVO.setRel(true);
            listVO.setCount(count);
        } catch (Exception e) {
            listVO.setErrorMsg("获取列表异常");
            logger.error("获取列表异常",e);
        }
        return listVO;
    }


    /*
    可售理财列表
     */
    @RequestMapping(value = "/Investmen",  method = RequestMethod.GET)
    @SystemControllerLog(description = "/salelist/Investmen/{type}" )
    public ResultVO ListInvestMentContract(@PathVariable String type ) throws BizException {
        Object dd= SecurityUtils.getSubject().getPrincipal();
        ResultVO listVO = new ResultVO();

        int coinid = 0;
        ParamsChecker.checkNotBlank(type,"coin type error");
        if (type.equalsIgnoreCase("btc"))
            coinid= Constants.btcoinId;
        if (type.equalsIgnoreCase("eth"))
            coinid=Constants.ethcoinId;
       /* if (coinid==0)
            throw  new BizException(501,"unkonw coin type");*/

        try {
            List<InvestMentContract> pageInfo = investmentManageService.SelectInvestmentContractBycoinId(coinid);
            listVO.setResult(pageInfo);
            listVO.setSucessRepmsg();
        } catch (Exception e) {
            listVO.setFailRepmsg("获取列表异常");
            logger.error("获取列表异常",e);
        }
        return listVO;
    }


}

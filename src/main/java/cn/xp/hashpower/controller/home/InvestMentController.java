package cn.xp.hashpower.controller.home;

import cn.xp.hashpower.common.Constants;
import cn.xp.hashpower.common.annotation.SystemControllerLog;
import cn.xp.hashpower.common.exception.BizException;
import cn.xp.hashpower.common.rule.ParamsChecker;
import cn.xp.hashpower.common.util.AuthUtil;
import cn.xp.hashpower.controller.BaseController;
import cn.xp.hashpower.model.SessionUser;
import cn.xp.hashpower.model.UInvestment;
import cn.xp.hashpower.service.InvestmentManageService;
import cn.xp.hashpower.vo.ListVO;
import cn.xp.hashpower.vo.ResultVO;
import com.github.pagehelper.Constant;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/10/29.
 */
@RestController
@RequestMapping("/investment")
public class InvestMentController extends BaseController {


    @Autowired
    InvestmentManageService service;


    /**
     * 查询理财的订单
     * @param page
     * @param limit
     * @return
     * @throws BizException
     */

    @RequestMapping(value = "/list",  method = RequestMethod.GET)
    @SystemControllerLog(description = "/Investment/list" )
    public ListVO ListUserInvestMent(@RequestParam(value = "page",required= false)  String page,@RequestParam(value = "limit",required= false) String limit ) throws BizException {
        Object dd= SecurityUtils.getSubject().getPrincipal();
        ListVO listVO = new ListVO();
        SessionUser user= AuthUtil.verfiy(listVO,dd);
        if (user==null) {
            return listVO;
        }
        //ParamsChecker.checkNotBlank(userName, "用户名不能为空");
        //ParamsChecker.checkNotBlank(password, "登录密码不能为空");

        int pNo=0,pSize=10;

        pNo= ParamsChecker.Conver2Int(page,0);
        pSize=Math.max(ParamsChecker.Conver2Int(limit,0),10);
        try {
            PageInfo<UInvestment> pageInfo = service.getUserInvestMentList(pNo, pSize,user.getUserId(),-1);
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


    /**
     * 查询理财的订单
     * @return
     * @throws BizException
     */

    @RequestMapping(value = "/summary",  method = RequestMethod.GET)
    @SystemControllerLog(description = "/Investment/summary" )
    public ListVO SummaryUserInvestMent( ) throws BizException {
        Object dd= SecurityUtils.getSubject().getPrincipal();
        ListVO listVO = new ListVO();
        SessionUser user= AuthUtil.verfiy(listVO,dd);
        if (user==null) {
            return listVO;
        }
        //ParamsChecker.checkNotBlank(userName, "用户名不能为空");
        //ParamsChecker.checkNotBlank(password, "登录密码不能为空");

        /*try {
            PageInfo<UInvestment> pageInfo = service.getUserInvestMentList(pNo, pSize,user.getUserId(),-1);
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
        }*/
        return listVO;
    }


/*
    @RequestMapping(value = "/investment",  method = RequestMethod.GET)
    @SystemControllerLog(description = "/investment/list" )
    public ListVO ListUserInvestment(@RequestParam(value = "coin",required= false) String pcoinid,@RequestParam(value = "page",required= false)  String page,
                                     @RequestParam(value = "limit",required= false) String limit )  throws BizException {
        Object dd= SecurityUtils.getSubject().getPrincipal();
        ListVO result = new ListVO();
        SessionUser user= AuthUtil.verfiy(result,dd);
        if (user==null) {
            return result;
        }
        int pNo=0,pSize=10,coinId=0;
        coinId= ParamsChecker.Conver2Int(pcoinid,0);
        pNo= ParamsChecker.Conver2Int(page,0);
        pSize=Math.max(ParamsChecker.Conver2Int(limit,0),10);
        try {
            PageInfo<UInvestment> pageInfo = service.getUInvestments(pNo, pSize,user.getUserId(),coinId);
            long count = 0;
            if(pageInfo != null){
                //分页
                count = pageInfo.getTotal();
                // listVO.setList(pageInfo.getList());
                result.setData(pageInfo.getList());
                result.setSucessMsg();
            }
            result.setRel(true);
            result.setCount(count);
        } catch (Exception e) {
            result.setErrorMsg("获取列表异常");
            logger.error("获取列表异常",e);
        }
        return result;
    }*/

    /**
     *  购买理财产品
     *  需要完善 区块节点的 钱包转账的同步问题
     * @param amount
     * @param trad_id
     * @return
     * @throws BizException
     */

    @RequestMapping(value = "/buy",method = RequestMethod.GET)
    @SystemControllerLog(description = "/investment/buy")
    public ResultVO BuyInvestmentItem(@RequestParam(value = "amount",required= true) String amount,@RequestParam(value = "tradid") int trad_id ) throws BizException
    {
        Object dd= SecurityUtils.getSubject().getPrincipal();
        ResultVO result = new ResultVO();
        //result.setFailRepmsg();
        SessionUser user= AuthUtil.verfiy(result,dd);
        if (user==null) {
            return result;
        }
        Double Amount;
        Amount= ParamsChecker.Conver2Double(amount, 0D);
        //int mid = ParamsChecker.Conver2AbsInt(pmid,0);

        /*int mid=Constants.getCoinId(cointype);
        if (mid<0)
            throw new  BizException(402,"unknow coin type");
         if (Amount<0 || mid<1) {
            result.setFailRepmsg("购买数量或参数错误");
            return result;
        }*/
        try {
            int ret = service.BuyInvestMnet(user.getUserId(),Amount,trad_id);
            switch (ret) {
                case 0:
                    result.setSucessRepmsg();
                    break;
                case 1:
                    result.setFailRepmsg("购买失败,清重试");
                    break;
                case 2:
                    result.setFailRepmsg("可用额度不足");
                    break;
            }
        } catch (Exception e) {
            logger.error("购买失败",e);
            result.setFailRepmsg("购买失败");
            logger.warn(e.getMessage());
        }
        return result;
    }


}

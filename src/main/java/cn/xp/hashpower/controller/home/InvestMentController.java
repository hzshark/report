package cn.xp.hashpower.controller.home;

import cn.xp.hashpower.common.Constants;
import cn.xp.hashpower.common.annotation.SystemControllerLog;
import cn.xp.hashpower.common.exception.BizException;
import cn.xp.hashpower.common.rule.ParamsChecker;
import cn.xp.hashpower.common.util.AuthUtil;
import cn.xp.hashpower.controller.BaseController;
import cn.xp.hashpower.dao.InvestmentMapper;
import cn.xp.hashpower.model.CoinItem;
import cn.xp.hashpower.model.InvestMentContract;
import cn.xp.hashpower.model.SessionUser;
import cn.xp.hashpower.model.UInvestment;
import cn.xp.hashpower.service.CoinManageService;
import cn.xp.hashpower.service.InvestmentManageService;
import cn.xp.hashpower.vo.ListVO;
import cn.xp.hashpower.vo.ResultVO;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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






    @RequestMapping(value = "/investment",  method = RequestMethod.GET)
    @SystemControllerLog(description = "/investment/ log" )
    public ListVO ListUserCoinLog(@RequestParam(value = "coin",required= false) String pcoinid,@RequestParam(value = "page",required= false)  String page,@RequestParam(value = "limit",required= false) String limit ){
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
    }


}

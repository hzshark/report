package cn.xp.hashpower.controller.home;

import cn.xp.hashpower.common.Constants;
import cn.xp.hashpower.common.annotation.SystemControllerLog;
import cn.xp.hashpower.common.exception.BizException;
import cn.xp.hashpower.common.rule.ParamsChecker;
import cn.xp.hashpower.common.util.AuthUtil;
import cn.xp.hashpower.controller.BaseController;
import cn.xp.hashpower.model.CoinItem;
import cn.xp.hashpower.model.SessionUser;
import cn.xp.hashpower.service.BitcoinClient;
import cn.xp.hashpower.service.CoinManageService;
import cn.xp.hashpower.service.UserManageService;
import cn.xp.hashpower.vo.ListVO;
import cn.xp.hashpower.vo.ResultVO;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/10/29.
 */
@RestController
@RequestMapping("/coin")
public class CoinController extends BaseController {

    @Resource
    CoinManageService service;

    @Resource
    BitcoinClient bitcoinClient;

    @Resource
    UserManageService userManageService;



    /*
     查询可用金额
     */
    @RequestMapping(value = "/list",  method = RequestMethod.GET)
    @SystemControllerLog(description = "/coin/list" )
    public ListVO ListUserCoinItem(@RequestParam(value = "coin",required= false) String cointype,@RequestParam(value = "page",required= false)  String page,@RequestParam(value = "limit",required= false) String limit ){
        Object dd= SecurityUtils.getSubject().getPrincipal();
        ListVO listVO = new ListVO();
        SessionUser user= AuthUtil.verfiy(listVO,dd);
        if (user==null) {
            return listVO;
        }
        //ParamsChecker.checkNotBlank(userName, "用户名不能为空");
        //ParamsChecker.checkNotBlank(password, "登录密码不能为空");
        int pcoinid=0;
        if (cointype.equalsIgnoreCase(Constants.bitcoinName))
            pcoinid=Constants.btcoinId;
        if (cointype.equalsIgnoreCase(Constants.ethcoinName))
            pcoinid=Constants.ethcoinId;

        if (pcoinid==0)
            return listVO;
        int pNo=0,pSize=10;
        //coinId= ParamsChecker.Conver2Int(pcoinid,0);
        pNo= ParamsChecker.Conver2Int(page,0);
        pSize=Math.max(ParamsChecker.Conver2Int(limit,0),10);
        try {
            PageInfo<CoinItem> pageInfo = service.getCoinList(pNo, pSize,user.getUserId(),pcoinid);
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



    @RequestMapping(value = "/coinlog",  method = RequestMethod.GET)
    @SystemControllerLog(description = "/coin/coinlog" )
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
            PageInfo<CoinItem> pageInfo = service.getCoinLogs(pNo, pSize,user.getUserId(),coinId);
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


    /*
    查用户的充值账户
     */
    @RequestMapping(value = "/walletbalance/{type}",  method = RequestMethod.GET)
    @SystemControllerLog(description = "/coin/walletbalance/{type}" )
    public ResultVO ListInvestMentContract(@PathVariable String type ) throws BizException {
        Object dd= SecurityUtils.getSubject().getPrincipal();
        ResultVO result = new ResultVO();
        SessionUser user= AuthUtil.verfiy(result,dd);
        if (user==null) {
            return result;
        }
        ParamsChecker.checkNotBlank(type,"错误类型");
        String address=null;
      //  ParamsChecker.checkNotBlank(address,"获取钱包错误");
        double amount=0;
        try {
            if (type.equalsIgnoreCase(Constants.bitcoinName)) {
                address = userManageService.getUserBitCoinwalletAddress(user.getUserId());
                amount = bitcoinClient.getbalance(address);
            }
            if (type.equalsIgnoreCase(Constants.ethcoinName)) {
                address = userManageService.getUserEtherwalletAddress(user.getUserId());
                //增加eth 接口
            }
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("address",address);
            jsonObject.put("amount",amount);
            result.setSucessRepmsg();
            result.setResult(jsonObject);
        } catch (Exception e) {
            result.setFailRepmsg("获取列表异常");
            logger.error("获取列表异常",e);
        }
        return result;
    }



}

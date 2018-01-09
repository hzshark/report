package cn.xp.hashpower.controller.home;

import cn.xp.hashpower.common.annotation.SystemControllerLog;
import cn.xp.hashpower.common.rule.ParamsChecker;
import cn.xp.hashpower.common.util.AuthUtil;
import cn.xp.hashpower.controller.BaseController;
import cn.xp.hashpower.model.CoinItem;
import cn.xp.hashpower.model.SessionUser;
import cn.xp.hashpower.service.CoinManageService;
import cn.xp.hashpower.vo.ListVO;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/10/29.
 */
@RestController
@RequestMapping("/coin")
public class CoinController extends BaseController {

    @Resource
    CoinManageService service;

    @RequestMapping(value = "/list",  method = RequestMethod.GET)
    @SystemControllerLog(description = "/coin/list" )
    public ListVO ListUserCoinItem(@RequestParam(value = "coin",required= false) String pcoinid,@RequestParam(value = "page",required= false)  String page,@RequestParam(value = "limit",required= false) String limit ){
        Object dd= SecurityUtils.getSubject().getPrincipal();
        ListVO listVO = new ListVO();
        SessionUser user= AuthUtil.verfiy(listVO,dd);
        if (user==null) {
            return listVO;
        }
        //ParamsChecker.checkNotBlank(userName, "用户名不能为空");
        //ParamsChecker.checkNotBlank(password, "登录密码不能为空");

        int pNo=0,pSize=10,coinId=0;
        coinId= ParamsChecker.Conver2Int(pcoinid,0);
        pNo= ParamsChecker.Conver2Int(page,0);
        pSize=Math.max(ParamsChecker.Conver2Int(limit,0),10);
        try {
            PageInfo<CoinItem> pageInfo = service.getCoinList(pNo, pSize,user.getUserId(),coinId);
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


}

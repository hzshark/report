package cn.xp.hashpower.controller.home;

import cn.xp.hashpower.common.annotation.SystemControllerLog;
import cn.xp.hashpower.common.rule.ParamsChecker;
import cn.xp.hashpower.common.util.AuthUtil;
import cn.xp.hashpower.controller.BaseController;
import cn.xp.hashpower.model.MachineInfo;
import cn.xp.hashpower.model.SessionUser;
import cn.xp.hashpower.service.MachineManageService;
import cn.xp.hashpower.vo.ListVO;
import cn.xp.hashpower.vo.ResultVO;
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
@RequestMapping("/machine")
public class MachineController extends BaseController {
    @Resource
    MachineManageService machineManageService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @SystemControllerLog(description = "/machine/list")
    public ListVO ListUserMachineItem(@RequestParam(value = "mid",required= false) String mid,@RequestParam(value = "page",required= false)  String page, @RequestParam(value = "limit",required= false) String limit){
        Object dd= SecurityUtils.getSubject().getPrincipal();
        ListVO listVO = new ListVO();
        SessionUser user= AuthUtil.verfiy(listVO,dd);
        if (user==null) {
            return listVO;
        }
        int pNo=0,pSize=10,Mid=0;
        pNo= ParamsChecker.Conver2Int(page,0);
        ParamsChecker.Conver2Int(mid,0);
        pSize=Math.max(ParamsChecker.Conver2Int(limit,0),10);
        try {
            PageInfo<MachineInfo> pageInfo = machineManageService.getMachineList(pNo,pSize,user.getUserId(),Mid);
            long count = 0;
            if(pageInfo != null){
                //分页
                count = pageInfo.getTotal();
                //listVO.setList(pageInfo.getList());
                listVO.setSucessMsg();
                listVO.setData(pageInfo.getList());
            }
            listVO.setRel(true);
            listVO.setCount(count);
        } catch (Exception e) {
            logger.error("获取用户列表异常",e);
            listVO.setErrorMsg(e.getMessage());
            logger.warn(e.getMessage());
        }
        return listVO;
    }


    @RequestMapping(value = "/buy",method = RequestMethod.GET)
    @SystemControllerLog(description = "/machine/buy")
    public ResultVO BuyMachineItem(@RequestParam(value = "mid",required= false) String pmid, @RequestParam(value = "amount",required= false) String amount){
        Object dd= SecurityUtils.getSubject().getPrincipal();
        ResultVO result = new ResultVO();
        //result.setFailRepmsg();
        SessionUser user= AuthUtil.verfiy(result,dd);
        if (user==null) {
            return result;
        }
        Double Amount;
        Amount= ParamsChecker.Conver2Double(amount, 0D);
        int mid = ParamsChecker.Conver2AbsInt(pmid,0);
        if (Amount<0 || mid<1) {
            result.setFailRepmsg("购买数量或参数错误");
            return result;
        }
        try {
            int ret = machineManageService.BuyMachine(user.getUserId(),mid,Amount);
            switch (ret) {
                case 0:
                    result.setSucessRepmsg();
                    break;
                case 1:
                    result.setFailRepmsg("购买失败,清重试");
                    break;
                case 2:
                    result.setFailRepmsg("没有足够的机器可以购买");
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

package cn.xp.report.controller.home;

import cn.xp.report.common.annotation.SystemControllerLog;
import cn.xp.report.common.rule.ParamsChecker;
import cn.xp.report.common.util.StringUtil;
import cn.xp.report.common.util.AuthUtil;
import cn.xp.report.controller.BaseController;
import cn.xp.report.model.MachineInfo;
import cn.xp.report.model.SessionUser;
import cn.xp.report.service.MachineManageService;
import cn.xp.report.vo.ListVO;
import cn.xp.report.vo.ResultVO;
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

    public ResultVO BuyMachineItem(@RequestParam(value = "mid",required= false) String mid, @RequestParam(value = "aoumt",required= false) String amount){
        Object dd= SecurityUtils.getSubject().getPrincipal();
        ResultVO result = new ResultVO();
        result.setFailRepmsg();
        SessionUser user= AuthUtil.verfiy(result,dd);
        if (user==null) {
            return result;
        }
        int Amount=0;
        Amount= ParamsChecker.Conver2Int(amount,0);
        if (Amount<1) {
            result.setFailRepmsg("购买数量错误");
            return result;
        }
        try {
            boolean ret = machineManageService.BuyMachine(user.getUserId(),Amount);
            long count = 0;
            if(ret){
                result.setSucessRepmsg();
            }

        } catch (Exception e) {
            logger.error("获取用户列表异常",e);
            result.setFailRepmsg(e.getMessage());
            logger.warn(e.getMessage());
        }
        return result;
    }




}

package cn.xp.report.controller.home;

import cn.xp.report.common.annotation.SystemControllerLog;
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
    public ListVO ListUserMachineItem(){
        Object dd= SecurityUtils.getSubject().getPrincipal();
        ListVO listVO = new ListVO();
        SessionUser user= AuthUtil.verfiy(listVO,dd);
        if (user==null) {
            return listVO;
        }

        try {
            PageInfo<MachineInfo> pageInfo = machineManageService.getMachineList(0, 10,user.getUserId());
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



}

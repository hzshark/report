package cn.xp.hashpower.controller.home;

import cn.xp.hashpower.common.annotation.SystemControllerLog;
import cn.xp.hashpower.common.rule.ParamsChecker;
import cn.xp.hashpower.common.util.AuthUtil;
import cn.xp.hashpower.controller.BaseController;
import cn.xp.hashpower.model.MachineInfo;
import cn.xp.hashpower.model.SessionUser;
import cn.xp.hashpower.model.UOrder;
import cn.xp.hashpower.service.MachineManageService;
import cn.xp.hashpower.service.UOrderManagerService;
import cn.xp.hashpower.vo.ListVO;
import cn.xp.hashpower.vo.ResultVO;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/10/29.
 */
@RestController
@RequestMapping("/order")
public class OrderController extends BaseController {

    UOrderManagerService _service;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @SystemControllerLog(description = "/order/list/{type}")
    public ListVO listOpenOrder(@PathVariable String type){
        Object dd= SecurityUtils.getSubject().getPrincipal();
        ListVO listVO = new ListVO();
        SessionUser user= AuthUtil.verfiy(listVO,dd);
        if (user==null) {
            return listVO;
        }
        int tp=0;
        tp= ParamsChecker.Conver2Int(type,0);
        PageInfo<UOrder> list=null;
        try {
            switch (tp)
            {
                //待支付
                case 1:
                    list = _service.listOpenOrder(user.getUserId());
                    break;
                //已完成
                case 0:
                    list=_service.listCloseOrder(user.getUserId());
                    break;
                //已取消
                case 2:
                    list=_service.listCancelOrder(user.getUserId());
                    break;
            }

            long count = 0;
            if(list != null){
                //分页
                count = list.getTotal();
                //listVO.setList(pageInfo.getList());
                listVO.setSucessMsg();
                listVO.setData(list.getList());
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

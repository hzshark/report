package cn.xp.report.controller.home;

import cn.xp.report.common.annotation.SystemControllerLog;
import cn.xp.report.controller.BaseController;
import cn.xp.report.model.MachineInfo;
import cn.xp.report.service.MachineManageService;
import cn.xp.report.vo.ListVO;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/10/29.
 */
@RestController
@RequestMapping("/coin")
public class CoinController extends BaseController {

    @Resource
    MachineManageService machineManageService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @SystemControllerLog(description = "/coin/list")
    public ListVO toMachineItem(){
        ListVO listVO = new ListVO();
        try {

            PageInfo<MachineInfo> pageInfo = machineManageService.getMachineList(0, 10,10);
            long count = 0;
            if(pageInfo != null){
                //分页
                count = pageInfo.getTotal();
                listVO.setList(pageInfo.getList());
                listVO.setData(pageInfo.getList());
            }
            listVO.setRel(true);
            listVO.setCode(0);
            listVO.setCount(count);
        } catch (Exception e) {
            logger.error("获取用户列表异常",e);
            listVO.setMsg(e.getMessage());
        }
        return listVO;
    }



}

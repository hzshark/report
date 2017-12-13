package cn.xp.report.controller.home;

import cn.xp.report.common.annotation.SystemControllerLog;
import cn.xp.report.common.util.AuthUtil;
import cn.xp.report.common.util.StringUtil;
import cn.xp.report.controller.BaseController;
import cn.xp.report.model.CoinInfo;
import cn.xp.report.model.MachineItem;
import cn.xp.report.model.SessionUser;
import cn.xp.report.service.CoinManageService;
import cn.xp.report.service.MachineManageService;
import cn.xp.report.vo.ListVO;
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
@RequestMapping("/salelist")
public class MainController extends BaseController {

    @Resource
    MachineManageService service;
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



}

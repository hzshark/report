package cn.xp.report.service;

import cn.xp.report.dao.MachineMapper;
import cn.xp.report.model.MachineInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MachineManageService {
    private static Logger logger = LoggerFactory.getLogger(MachineManageService.class);

    @Autowired
    private MachineMapper machineMapper;

    public PageInfo<MachineInfo> getMachineList(int pageNo, int pageSize,int uid){
        PageHelper.startPage(pageNo, pageSize);
        List<MachineInfo> groupList = machineMapper.selectUserMachineList(uid);
        PageInfo<MachineInfo> pageInfo= new PageInfo<MachineInfo>(groupList);
        return pageInfo;
    }


}

package cn.xp.report.service;

import cn.xp.report.dao.MachineMapper;
import cn.xp.report.model.UMachine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShareBenefitService {
    @Autowired
    private MachineMapper machineMapper;

    public List<UMachine> getUMachine() {
        return machineMapper.selectUMachine();
    }
}

package cn.xp.hashpower.service;

import cn.xp.hashpower.dao.MachineMapper;
import cn.xp.hashpower.model.UMachine;
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

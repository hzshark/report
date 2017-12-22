package cn.xp.report.job;

import cn.xp.report.model.UMachine;
import cn.xp.report.service.ShareBenefitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class ShareBenefit {
    @Autowired
    private ShareBenefitService shareBenefitService;

    public final static long ONE_Minute =  60 * 1000;
    @Scheduled(fixedDelay=ONE_Minute)
    public void fixedDelayJob(){
        List<UMachine> list = shareBenefitService.getUMachine();

        for (UMachine uMachine:
             list) {
            System.out.println(new Date()+":"+uMachine.getUserid()+":"+uMachine.getMachin_id()+":"+uMachine.getAmount());
        }
    }

    @Scheduled(fixedRate=ONE_Minute)
    public void fixedRateJob(){
        System.out.println(new Date()+"test2");
    }

    @Scheduled(cron="0 15 3 * * ?")
    public void cronJob(){
        System.out.println(new Date()+"test3");
    }
}

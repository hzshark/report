package cn.xp.hashpower.model;

import lombok.Data;

@Data
public class InvestMentContract {
    int contractId;
    String name;
    int duration;
    //年化收益
    float inerest;
    int enalbe;
    int coinId;
}

package cn.xp.hashpower.model;

import lombok.Data;

@Data
public class InvestMentItem {
    private int tradeId;
    private  String saledesc;
    private int duration;
    //年化收益
    private int  interest;
    private int cointype;

    private double limited;
    private double amount;

    public double getAvaliable()
    {
        return limited-amount;
    }


}

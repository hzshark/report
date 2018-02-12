package cn.xp.hashpower.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class UInvestment {
   // private int userid;
    //投资id
    private int investmentId;
    //理财合约id
    private int tradeId;
    private double amount;

    private String invest_date;

    private String end_date;

    private int cointype;
    //private InvestMentItem investMentContract;
}

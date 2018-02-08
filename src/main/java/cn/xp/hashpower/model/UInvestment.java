package cn.xp.hashpower.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UInvestment {
    private int userid;
    //投资id
    private int investmentId;
    //理财合约id
    private int contractId;
    private BigDecimal amount;
    private  InvestMentContract investMentContract;
}

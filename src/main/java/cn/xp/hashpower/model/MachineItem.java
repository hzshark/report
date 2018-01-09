package cn.xp.hashpower.model;

import lombok.Data;

@Data
public class MachineItem {

    int machine_id;
    String machine_name;
    int amount;
    String tradeid;
    String saledesc;

    //合约期限
    private  String contract_duration;

    //管理费
    private  String maintainfee;

    //电费
    private String energy_fee;

    //比特币 价格
    private String btc_price;

   //人民币价格
    private String rmb_price;

}

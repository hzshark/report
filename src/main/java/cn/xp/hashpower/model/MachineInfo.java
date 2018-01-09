package cn.xp.hashpower.model;

import lombok.Data;

/**
 * 机器信息封装
 * 
 */
@Data
public class MachineInfo {

	private String machine_id;

	private String machine_name;

	//算力
    private String capacity ;

    private String type ;

    private String describe ;
    //功耗
    private String watt;
  /*  //合约期限
    private  String contract_duration;
    //维护费
    private  String maintainfee;
    //支付方式
    private  String paytype;
    */

  //挖掘币种
    private int coin_type;
}

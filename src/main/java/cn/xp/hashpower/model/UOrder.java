package cn.xp.hashpower.model;

import lombok.Data;

import java.sql.Date;
@Data
public class UOrder {
    String order_id;
    int userid;
    int pay_type;
    String pay_amount;
    String catture_set;
    //int status;
    Date pay_time;
}

package cn.xp.hashpower.service.Interface;

public interface SmsService {

    boolean sendTxt(String phone,String msg);

    boolean sendVode(String phone,String code);
}

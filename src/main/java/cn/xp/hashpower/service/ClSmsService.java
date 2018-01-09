package cn.xp.hashpower.service;


import cn.xp.hashpower.service.Interface.SmsService;
import org.springframework.stereotype.Component;

@Component
public class ClSmsService implements SmsService {

	@Override
	public boolean sendTxt(String phone, String msg) {
		return false;
	}

	@Override
	public boolean sendVode(String phone, String code) {
		return false;
	}
}

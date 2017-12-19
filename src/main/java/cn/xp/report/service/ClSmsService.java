package cn.xp.report.service;


import cn.xp.report.service.Interface.SmsService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

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

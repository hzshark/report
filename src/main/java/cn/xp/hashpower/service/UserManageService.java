package cn.xp.hashpower.service;

import cn.xp.hashpower.dao.SysLoginAccountMapper;
import cn.xp.hashpower.dao.UOrderMapper;
import cn.xp.hashpower.model.SessionUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManageService {
    private static Logger logger = LoggerFactory.getLogger(UserManageService.class);

    @Autowired
    private SysLoginAccountMapper loginAccountDao;

    @Autowired
    private UOrderMapper uOrderMapper;

    public SessionUser getUserByUserName(String userName){

        uOrderMapper.findByOid(11);
        SessionUser account = loginAccountDao.selectByAccount(userName);
        return account;
    }

    public SessionUser getUserInfo(int userid){

        SessionUser account = loginAccountDao.selectByUserId(userid);
        return account;
    }

    public boolean addUser(String phone,String pwd)
    {
        return loginAccountDao.adduser(phone,pwd);
    }

    public void modifyUserPwd(int userid,String pwd)
    {
         loginAccountDao.modifyUserPwd(userid,pwd);
    }

}

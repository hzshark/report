package cn.xp.hashpower.service;

import cn.xp.hashpower.dao.SysAccountMapper;
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
    private SysAccountMapper AccountDao;

    @Autowired
    private UOrderMapper uOrderMapper;

    public SessionUser getUserByUserName(String userName){

        //uOrderMapper.findByOid(11);
    SessionUser account = AccountDao.selectByAccount(userName);
    return account;
    }

    public SessionUser getUserInfo(int userid){

        SessionUser account = AccountDao.selectByUserId(userid);
        return account;
    }

    public boolean addUser(SessionUser user)
    {
        return AccountDao.adduser(user);
    }

    public void modifyUserLoginPwd(int userid, String pwd)
    {
         AccountDao.modifyLoginUserPwd(userid,pwd);
    }

    public void modifyUserPayPwd(int userid, String pwd)
    {
        AccountDao.modifyPayUserPwd(userid,pwd);
    }

    public void setUserBtwallet(int userid,String address)
    {
        AccountDao.setUserBtwallet(userid,address);
    }

    public String getUserBitCoinwalletAddress(int userId) {
        return  AccountDao.getUserWalletAddress(userId,1);
    }

    public String getUserEtherwalletAddress(int userId) {
        return  AccountDao.getUserWalletAddress(userId,2);
    }

    public String getUserAuthStatus(int userId) {
        return AccountDao.getUserAuthStatus(userId);
    }

    public void setGoogleAuthSecret(int userId, String secret)
    {
         AccountDao.setGoogleAuthSecret(userId,secret);
    }
}

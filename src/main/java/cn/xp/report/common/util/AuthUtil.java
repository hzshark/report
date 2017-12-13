package cn.xp.report.common.util;

import cn.xp.report.model.SessionUser;
import cn.xp.report.vo.ResultVO;

public class AuthUtil {

    public static SessionUser verfiy(ResultVO resultVO,Object session)
    {
        SessionUser user=null;
        if (session ==null || !(session  instanceof SessionUser))
        {
            resultVO.setFailRepmsg(" login first");
            return user;
        }
        user=(SessionUser) session;
        return user;
    }
}

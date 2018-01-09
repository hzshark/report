package cn.xp.hashpower.common.util;

import cn.xp.hashpower.model.SessionUser;
import cn.xp.hashpower.vo.ListVO;
import cn.xp.hashpower.vo.ResultVO;

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

    public static SessionUser verfiy(ListVO resultVO, Object session)
    {
        SessionUser user=null;
        if (session ==null || !(session  instanceof SessionUser))
        {
            resultVO.setErrorMsg(" login first");
            return user;
        }
        user=(SessionUser) session;
        return user;
    }

}

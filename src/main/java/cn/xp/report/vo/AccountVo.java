package cn.xp.report.vo;

import lombok.Getter;
import lombok.Setter;

public class AccountVo extends BaseVO{

    @Setter @Getter
    public Integer id;

    @Setter @Getter
    public String login_name = "";

    @Setter @Getter
    public String loginpwd = "";

    @Setter @Getter
    public String email = "";

    @Setter @Getter
    public String phone = "";

}

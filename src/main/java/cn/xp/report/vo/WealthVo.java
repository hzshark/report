package cn.xp.report.vo;

import lombok.Getter;
import lombok.Setter;

public class WealthVo extends BaseVO {

    @Setter @Getter
    public Integer id;

    @Setter @Getter
    public Integer userid = 0;

    @Setter @Getter
    public Integer coinid = 0;

    @Setter @Getter
    public String amount = "";
}

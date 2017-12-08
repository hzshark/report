package cn.xp.report.vo;

import lombok.Getter;
import lombok.Setter;

public class CionItemVo extends BaseVO {

    @Setter @Getter
    public Integer id;

    @Setter @Getter
    public String name = "";

    @Setter @Getter
    public Integer enable = 0;

}

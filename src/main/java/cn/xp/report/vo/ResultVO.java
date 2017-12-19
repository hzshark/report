package cn.xp.report.vo;


import lombok.Data;

import java.io.Serializable;


import java.io.Serializable;
import java.util.Map;

@Data
public class ResultVO   {

    private  String msg;
    private int code;
    private Object result;

    public ResultVO() {
        super();
        msg="failed";

    }


    public void setSucessRepmsg() {
        this.msg = "sucessed";
        this.code=200;

    }

    public void setFailRepmsg() {
        this.msg = "failed";
        this.code=400;
    }

    public void setSucessRepmsg(String repmsg) {
        this.msg = repmsg;
        this.code=200;

    }

    public void setFailRepmsg(String repmsg) {
        this.msg = repmsg;
        this.code=400;
    }


}

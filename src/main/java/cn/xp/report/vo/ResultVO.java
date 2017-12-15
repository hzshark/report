package cn.xp.report.vo;



import java.io.Serializable;

public class ResultVO  implements Serializable {

    private  String msg;
    private int code;
    private Object result;

    public ResultVO() {
        super();
        msg="failed";

    }


  /*  public void setRepmsg(String repmsg) {
        this.repmsg = repmsg;
    }*/

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

    public void setRepstate(int repstate) {
        this.code = repstate;
    }

    /*public void setLinkedHashMap(LinkedHashMap<String, Object> linkedHashMap) {
        this.linkedHashMap = linkedHashMap;
    }*/

        public Object getResult() {
            return result;
        }

        public void setResult(Object rst) {
            this.result = rst;
        }

    /*public boolean complete(HttpServletResponse response, ZzResp resp) {
        if(StringUtil.isBlank(this.repmsg)){
            return false;
        }
        if(StringUtil.isBlank(this.repstate)){
            return false;
        }
        super.put(_msg,this.repmsg);
        super.put(_state,this.repstate);
        if(null!=this.linkedHashMap){
            super.put(rspData,this.linkedHashMap);
        }
       ServletUtils.writeToResponse(response,resp);
        return  true;
    }*/
}

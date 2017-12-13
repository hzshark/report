package cn.xp.report.vo;



import java.io.Serializable;

public class ResultVO  implements Serializable {

    private final String _state="state";
    private final String _msg="msg";
    private final String rspData="data";
    private String repmsg;
    private int repstate;
    private final String _defSucessMsg="sucessed";
    private final String _defFailMsg="failed";
    //private LinkedHashMap<String,Object> linkedHashMap;
    private Object result;

    public ResultVO() {
        super();
    }


  /*  public void setRepmsg(String repmsg) {
        this.repmsg = repmsg;
    }*/

    public void setSucessRepmsg() {
        this.repmsg = _defSucessMsg;
        this.repstate=200;

    }

    public void setFailRepmsg() {
        this.repmsg = _defFailMsg;
        this.repstate=400;
    }

    public void setSucessRepmsg(String repmsg) {
        this.repmsg = repmsg;
        this.repstate=200;

    }

    public void setFailRepmsg(String repmsg) {
        this.repmsg = repmsg;
        this.repstate=400;
    }

    public void setRepstate(int repstate) {
        this.repstate = repstate;
    }

    /*public void setLinkedHashMap(LinkedHashMap<String, Object> linkedHashMap) {
        this.linkedHashMap = linkedHashMap;
    }*/

        public Object getResult() {
            return result;
        }

        public void setResult(Object result) {
            this.result = result;
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

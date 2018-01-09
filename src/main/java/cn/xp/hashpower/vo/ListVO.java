package cn.xp.hashpower.vo;


import java.io.Serializable;
import java.util.List;

public class ListVO implements Serializable {

    private int code;
    private List data;
    private boolean rel;
    private long count;
    private List list;
    private String msg;

    public boolean isRel() {
        return rel;
    }

    public void setRel(boolean rel) {
        this.rel = rel;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public String getMsg() {
        return msg;
    }

    /*public void setMsg(String msg) {
        this.msg = msg;
    }
*/
    public int getCode() {
        return code;
    }

    public void setSucessMsg(String msg)
    {
        this.code=0;
        this.msg=msg;
    }
    public void setSucessMsg()
    {
        this.code=0;
        this.msg="sucess";
    }

    public void setErrorMsg(String msg)
    {
        this.code=405;
        this.msg=msg;
    }
    public void setErrorMsg()
    {
        this.code=405;
        this.msg="error";
    }

    public void setSucessCode()
    {
        setSucessMsg();
    }


    public void setErrorCode(int code)
    {
        this.code=code;
        setErrorMsg();
    }
    public void setErrorCode()
    {
        this.setErrorMsg();
    }

    /*
    private void setCode(int code) {
        this.code = code;
    }*/

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }
}

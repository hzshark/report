
package cn.xp.report.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 请求参数封装基类 分类抽取
 */
public class PageVO {

    @JsonIgnore
    private int pageNum = 1;

    @JsonIgnore
    private int pageSize = 10;

    @JsonIgnore
    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }


    public int getPageSize() {
        return pageSize < 1 ? 20 : pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
}

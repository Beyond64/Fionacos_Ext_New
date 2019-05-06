package com.colin.entity;

import java.util.List;

/**分页VO
 * @param <T>
 */
public class PageVo<T> {
    private Integer count;
    private Integer page = 1;
    private Integer limit = 20;
    private Integer starRows;
    private Integer endRows;
    private Integer code = 0;
    private List<T> data;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getPage() {
        if(this.count % this.limit == 0 ){
            return this.count / this.limit;
        }else {
            return this.count / this.limit + 1;
        }
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getStarRows() {
        return (this.page -1) * this.limit + 1;
    }

    public void setStarRows(Integer starRows) {
        this.starRows = starRows;
    }

    public Integer getEndRows() {
        if (this.page * this.limit > this.count){
            return this.count;
        }else{
            return this.page * this.limit;
        }
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setEndRows(Integer endRows) {
        this.endRows = endRows;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}

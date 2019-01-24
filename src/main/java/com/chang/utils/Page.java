package com.chang.utils;

import lombok.Data;

@Data
public class Page {

    // 当前页数
    private int start;

    // 每页记录数
    private int count;

    // 总记录数
    private int total;

    public Page(int start, int count){
        super();
        this.start = start;
        this.count = count;
    }

    /**
     * 是否存上一页
     * @return
     */
    public boolean isHasPreviouse(){
        return start != 0;
    }

    /**
     * 是否存在下一页
     * @return
     */
    public boolean isHasNext(){
        return start != getLast();
    }

    /**
     * 获取总页数
     * @return
     */
    public int getTotalPage(){
        int totalPage;

        if(0 == total % count) {
            totalPage = total / count;
        } else {
            totalPage = total / count + 1;
        }

        if (0 == totalPage) {
            totalPage = 1;
        }
        return  totalPage;
    }

    /**
     * 获取最后页
     * @return
     */
    public int getLast(){
        int last;

        if(0 == total % count) {
            last = total - count;
        } else {
            last = total - total % count;
        }

        last = last < 0 ? 0 : last;
        return last;
    }
}

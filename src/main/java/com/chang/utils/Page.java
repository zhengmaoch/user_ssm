package com.chang.utils;

import lombok.Data;

@Data
public class Page {

    private int start;
    private int count;
    private int total;

    public Page(int start, int count){
        super();
        this.start = start;
        this.count = count;
    }

    public boolean isHasPreviouse(){
        return start != 0;
    }

    public boolean isHasNext(){
        return start != getLast();
    }

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

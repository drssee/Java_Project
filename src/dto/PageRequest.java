package dto;

import util.AdminServiceUtil;

public class PageRequest {
    private int page; //현재 페이지
    private int size; //한페이지당 게시물 개수

    private int totalCnt; //전체 게시물 개수

    private int totalPage;

    private boolean prev;

    private boolean next;

    public PageRequest() {
        this.page=1;
        this.size=10;
    }

    public PageRequest(int totalCnt) {
        this();
        this.totalCnt= totalCnt;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSkip(){
        return (page-1)*size;
    }

    public int getTotalCnt() {
        return totalCnt;
    }

    public void setTotalCnt(int totalCnt) {
        this.totalCnt = totalCnt;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public boolean isPrev() {
        return prev;
    }

    public void setPrev(boolean prev) {
        this.prev = prev;
    }

    public boolean isNext() {
        return next;
    }

    public void setNext(boolean next) {
        this.next = next;
    }
}

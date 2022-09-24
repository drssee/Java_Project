package dto;

import util.AdminServiceUtil;

public class PageRequest {
    private int page; //현재 페이지
    private int size; //한페이지당 게시물 개수

    private int totalCnt; //전체 게시물 개수

    private int totalPage;

    public PageRequest() {
        this.page=1;
        this.size=5;
    }

    public PageRequest(int totalCnt) {
        this();
        this.totalCnt= totalCnt;
        this.totalPage=(int)(Math.ceil(this.totalCnt/this.size));
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
}

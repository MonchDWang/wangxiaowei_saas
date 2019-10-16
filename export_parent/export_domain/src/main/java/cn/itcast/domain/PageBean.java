package cn.itcast.domain;

import java.util.List;

// 封装分页需要的数据
@SuppressWarnings("all")
public class PageBean<T> {
    private List<T> list;  //页面要展示的数据
    private int pageNum;   //  当前页
    private int pageSize;     //  每页展示的条数
    private int total;    // 总条数
    private int pages;     // 总页数
    // 起始数据
    private int navigateFirstPage;
    // 结尾数据
    private int navigateLastPage;

    //做一个前5后4的计算方法
    public void jisuan(){
        if(pages<=10){
            navigateFirstPage=1;
            navigateLastPage=pages;
        }else{  //总页数大于10
            navigateFirstPage=pageNum-5;
            navigateLastPage=pageNum+4;

            // 判断越界问题
            if(navigateFirstPage<=0){
                navigateFirstPage=1;
                navigateLastPage=10;
            }
            if(navigateLastPage>pages){   //20
                navigateFirstPage=pages-9;
                navigateLastPage=pages;
            }
        }
    }

    public int getNavigateFirstPage() {
        jisuan();
        return navigateFirstPage;
    }
    public int getNavigateLastPage() {
        jisuan();
        return navigateLastPage;
    }


    public void setNavigateFirstPage(int navigateFirstPage) {
        this.navigateFirstPage = navigateFirstPage;
    }
    public void setNavigateLastPage(int navigateLastPage) {
        this.navigateLastPage = navigateLastPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

}

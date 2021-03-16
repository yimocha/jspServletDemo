package com.zsx.bean;

import java.util.List;

public class Page<T> {
    /**
     * 构造参数
     * @param currentPage  页面传入的当前页
     * @param pageSize   每页显示的条数
     * @param totalCount  数据库查询的总记录数
     */
    public Page(Integer currentPage, Integer pageSize, Integer totalCount){
        this.currentPage = currentPage;
        this.pageSize = (pageSize == null ? this.pageSize : pageSize);
        this.totalCount = totalCount;
        this.previousPage = (currentPage == 1 ? 1 : currentPage-1);
        this.totalPage = (totalCount % this.pageSize == 0 ? totalCount / this.pageSize : totalCount / this.pageSize + 1);
        this.nextPage = (currentPage == totalPage) ? totalPage : (currentPage + 1);
        this.startIndex =(currentPage - 1) * this.pageSize + 1;
        this.endIndex = currentPage * this.pageSize;
    }
    /**
     * 当前页(由页面传递)
     */
    private Integer currentPage;
 
    /**
     * 每页显示的数量默认为10
     */
    private Integer pageSize = 10;
 
    /**
     * 上一页
     * prePage = curPage ==1 ? 1 : curPage-1
     * 举例
     * 		2  -- 1
     * 		3  -- 2
     * 		4  -- 3
     */
    private Integer previousPage;
 
    /**
     * 下一页
     * 	举例:
     * 	nextPage=curPage==totalPage?totalPage:(curPage+1)
     * 	curPage   totalPage  nextPage
     * 		1         3         2
     */
    private Integer nextPage;
 
    /**
     * 总页数
     * pageCount(每页显示的条数)   total(总记录数)   totalpage
     *       10                   20             3
     */
    private Integer totalPage;
 
    /**
     * 总记录数(从数据库查询)
     */
    private Integer totalCount;
 
    /**
     * 开始索引
     * curPage    pageCount  start-end
     * 	  1           10         1-10
     * 	  2           10         11-20
     * 							 (curPage-1)*pageCount+1  curPage*pageCount
     */
    private Integer startIndex;
 
    /**
     * 结束索引
     * @return
     */
    private Integer endIndex;
 
    /**
     * 查询最终查询的数据
     */
    private List<T> data;
 
    public Integer getCurrentPage() {
        return currentPage;
    }
 
    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }
 
    public Integer getPageSize() {
        return pageSize;
    }
 
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
 
    public Integer getPreviousPage() {
        return previousPage;
    }
 
    public void setPreviousPage(Integer previousPage) {
        this.previousPage = previousPage;
    }
 
    public Integer getNextPage() {
        return nextPage;
    }
 
    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }
 
    public Integer getTotalPage() {
        return totalPage;
    }
 
    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
 
    public Integer getTotalCount() {
        return totalCount;
    }
 
    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
 
    public List<T> getData() {
        return data;
    }
 
    public void setData(List<T> data) {
        this.data = data;
    }
 
    public Integer getStartIndex() {
        return startIndex;
    }
 
    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }
 
    public Integer getEndIndex() {
        return endIndex;
    }
 
    public void setEndIndex(Integer endIndex) {
        this.endIndex = endIndex;
    }
    
}

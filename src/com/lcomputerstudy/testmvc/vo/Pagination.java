package com.lcomputerstudy.testmvc.vo;

//import com.lcomputerstudy.testmvc.service.UserService;
//import com.lcomputerstudy.testmvc.service.BoardService;

public class Pagination {
	private int totalCount;
	private int page;
	int pageNum;
	int startPage;
	int endPage;
	int lastPage;
	int prevPage;
	private int nextPage;
	
	public static final int pageUnit=5;
	public static final int perPage=3;
	
	public Pagination() {
		
	}
	public Pagination(int page, int totalCount) {
		this.page =page;
		this.totalCount = totalCount;
		startPage=((page-1)/pageUnit)*pageUnit+1;
		lastPage = (int)Math.ceil(totalCount / (float)perPage);
		endPage=startPage+pageUnit-1;
		endPage=endPage<lastPage ? endPage : lastPage;
		prevPage=(endPage-pageUnit);
		nextPage=(startPage+pageUnit);
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page=page;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum=pageNum;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getLastPage() {
		return lastPage;
	}
	public void setLastPage(int lastPage) {
		this.lastPage=lastPage;	
	}
	public int getPageUnit() {
		return pageUnit;
	}
	public int getPerpage() {
		return perPage;
	}
	public int getPrevPage() {
		return prevPage;
	}
	public void setPrevPage(int prevPage) {
		this.prevPage=prevPage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage=nextPage;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
}




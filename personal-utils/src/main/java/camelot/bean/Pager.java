package camelot.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * <p>Description: [分页类]</p>
 * Created on 2016-3-24
 * @author  <a href="mailto: wuchaoqiang@clt.com">武超强</a>
 * @version 1.0 
 * Copyright (c) 2016 北京柯莱特科技有限公司 交付部
 */
public class Pager<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	/**默认每页记录数为10条*/
	public static final int DEFAULT_PAGESIZE = 10;
	
	private List<T> records; //分页数据
	private int page = 1;// 当前页
	private int rows = 10;// 每页显示记录数
	private int pageOffset;// 当前页起始记录
	private String sort;// 排序字段（如有多个字段    例 "columnA,columnB desc"  ）
	private String order;// asc/desc
	private int totalPage;//总页数
	private long totalCount;//总记录数
	private int startPageIndex;//开始页
	private int endPageIndex;//结束页
	private int pageCode = 10;	//页码数量：翻页条工显示多少页的索引
	private int previewPage = 1;//上一页
	private int nextPage = 1;	//下一页

	public Pager(){
	}
	/**
	 * @param page 当前页
	 * @rows 每页记录数大小 
	 */
	public Pager(int page, int rows){
		this.page = page;
		this.rows = rows;
	}
	
	/**
	 * @param page 当前页
	 */
	public Pager(int page){
		this.setPage(page);
		this.rows = DEFAULT_PAGESIZE;
	}
	
	public int getPageOffset() {
		return (page - 1) * rows;
	}

	public void setPageOffset(int pageOffset) {
		this.pageOffset = pageOffset;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		if( page > 0 ){
			this.page = page;
		}
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		if( rows > 0 ){
			this.rows = rows;
		}
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
		
		this.startPageIndex  = this.page-(pageCode%2==0? pageCode/2-1 : pageCode/2);
		this.endPageIndex = this.page+pageCode/2;
		if(this.startPageIndex <1){
			this.startPageIndex  = 1;
			if(totalPage>=pageCode) {
				this.endPageIndex = pageCode;
			} else {
				this.endPageIndex = totalPage;
			}
		}
		if(this.endPageIndex>totalPage){
			this.endPageIndex = totalPage;
			if((this.endPageIndex-pageCode)>0) {
				this.startPageIndex = this.endPageIndex -pageCode+1;
			}
			else this.startPageIndex  = 1;
		}
		if(this.endPageIndex<=1){
			this.endPageIndex = 1;
		}
		this.previewPage = this.page - 1;
		this.nextPage = this.page + 1;
		if(this.page<=1){
			this.previewPage = 1;
		}
		if(this.page>=this.totalPage){
			this.nextPage = this.totalPage;
		}
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
		setTotalPage((int) (totalCount%rows==0?totalCount/rows:(totalCount/rows+1)));
	}

	public int getStartPageIndex() {
		return startPageIndex;
	}

	public void setStartPageIndex(int startPageIndex) {
		this.startPageIndex = startPageIndex;
	}

	public int getEndPageIndex() {
		return endPageIndex;
	}

	public void setEndPageIndex(int endPageIndex) {
		this.endPageIndex = endPageIndex;
	}

	public int getPageCode() {
		return pageCode;
	}

	public void setPageCode(int pageCode) {
		this.pageCode = pageCode;
	}

	public int getPreviewPage() {
		return previewPage;
	}

	public void setPreviewPage(int previewPage) {
		this.previewPage = previewPage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public List<T> getRecords() {
		return records;
	}

	public void setRecords(List<T> records) {
		this.records = records;
	}
	
}

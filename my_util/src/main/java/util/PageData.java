package util;

import java.util.List;

public  class PageData<T>{
	private Integer total;
	private Integer page;
	private Integer size;
	protected List<T> rows;
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	public PageData(Integer total, List<T> rows) {
		super();
		this.total = total;
		this.rows = rows;
	}
	public PageData(Integer total, Integer page, Integer size, List<T> rows) {
		super();
		this.total = total;
		this.page = page;
		this.size = size;
		this.rows = rows;
	}
	public PageData() {
		super();
	}
}

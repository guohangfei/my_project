package util;
 /**
  * @Author:         郭航飞
  * @Description:    页面信息类
  * @CreateDate:   2018/4/12 17:33
  */
public class PageInfo {
	private Integer page;
	private Integer size;
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
	public PageInfo(Integer page, Integer size) {
		super();
		this.page = page;
		this.size = size;
	}
	public PageInfo() {
		super();
	}
	
	
}

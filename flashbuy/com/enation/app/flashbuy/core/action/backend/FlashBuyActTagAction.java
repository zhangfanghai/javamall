package com.enation.app.flashbuy.core.action.backend;


import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.enation.app.flashbuy.core.service.IFlashBuyManager;
import com.enation.app.shop.core.service.IGoodsTagManager;
import com.enation.framework.action.WWAction;
/**
 * 
 * @ClassName: FlashBuyActTagAction 
 * @Description: 限时抢购活动标签Action 
 * @author TALON 
 * @date 2015-7-31 上午1:24:20 
 *
 */
@ParentPackage("eop_default")
@Namespace("/shop/admin")
@Results({
	@Result(name="search_list",type="freemarker", location="/flashbuy/tag/search_list.html"),
	@Result(name="list",type="freemarker", location="/flashbuy/tag/tag_goods_list.html")
})
@Action("flashBuyActTag")
public class FlashBuyActTagAction extends WWAction{
	
	private Integer catid;
	private Integer tagid;
	private Integer actid;
	private IFlashBuyManager flashBuyManager;
	private IGoodsTagManager goodsTagManager;
	
	/**
	 * 
	 * @Title: list
	 * @Description: 跳转至限时抢购活动标签列表
	 * @return String    限时抢购活动标签列表
	 */
	public String list(){
		return "list";
	}
	/**
	 * 
	 * @Title: listJson
	 * @Description: 商品标签    商品列表json
	 * @param catid 商品分类Id,Integer
	 * @param tagid 标签Id,Integer
	 * @return 标签商品列表JSON
	 */
	public String listJson(){
		if (catid == null || catid.intValue() == 0) {
			this.webpage = goodsTagManager.getGoodsList(tagid, this.getPage(), this.getPageSize());
		} else {
			this.webpage = goodsTagManager.getGoodsList(tagid, catid.intValue(), this.getPage(), this.getPageSize());
		}
		this.showGridJson(webpage);
		return JSON_MESSAGE;
	}
	/**
	 * 跳转至标签商品选择列表
	 * @return
	 */
	public String search() {
		return "search_list";
	}
	/**
	 * 
	 * @Title: goodsListJson
	 * @Description: 获取限时抢购标签商品列表JSON
	 * @param actid 限时抢购活动ID
	 * @param webpage 限时抢购标签商品分页列表
	 * @return 限时抢购标签商品列表JSON
	 */
	public String goodsListJson() {
		
		this.webpage=this.flashBuyManager.listByActId(this.getPage(), this.getPageSize(), actid, 1);
		this.showGridJson(this.webpage);
		return JSON_MESSAGE;
	}

	public Integer getCatid() {
		return catid;
	}

	public void setCatid(Integer catid) {
		this.catid = catid;
	}

	public Integer getTagid() {
		return tagid;
	}

	public void setTagid(Integer tagid) {
		this.tagid = tagid;
	}

	public Integer getActid() {
		return actid;
	}

	public void setActid(Integer actid) {
		this.actid = actid;
	}

	public IFlashBuyManager getFlashBuyManager() {
		return flashBuyManager;
	}

	public void setFlashBuyManager(IFlashBuyManager flashBuyManager) {
		this.flashBuyManager = flashBuyManager;
	}

	public IGoodsTagManager getGoodsTagManager() {
		return goodsTagManager;
	}

	public void setGoodsTagManager(IGoodsTagManager goodsTagManager) {
		this.goodsTagManager = goodsTagManager;
	}
}

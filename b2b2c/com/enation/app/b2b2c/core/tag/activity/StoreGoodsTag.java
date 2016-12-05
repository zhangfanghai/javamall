package com.enation.app.b2b2c.core.tag.activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.enation.app.b2b2c.core.service.member.IStoreMemberManager;
import com.enation.app.shop.core.service.impl.GoodsManager;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.taglib.BaseFreeMarkerTag;

import freemarker.template.TemplateModelException;

@Component
public class StoreGoodsTag extends BaseFreeMarkerTag {
    
    private IStoreMemberManager storeMemberManager;
    private GoodsManager goodsManager;

    @Override
    protected Object exec(Map params) throws TemplateModelException {
        HttpServletRequest request=ThreadContextHolder.getHttpRequest();

        Integer storeId = storeMemberManager.getStoreMember().getStore_id();
        if(storeId == null) {
            return new ArrayList<Map<String, Object>>();
        }
        Map<String, Object> returnMap = new HashMap<String, Object>();
        returnMap.put("goods_name", request.getParameter("goods_name"));
        returnMap.put("sn", request.getParameter("sn"));
        returnMap.put("storeId",storeId);
        returnMap.put("isAdd",request.getParameter("isAdd"));
//       List<Map<String, Object>> list = goodsManager.searchGoodsForActivity(storeId, null);
      
        List<Map<String, Object>> list = goodsManager.searchGoodsForActivity(returnMap);
        return list;
    }
    
    public IStoreMemberManager getStoreMemberManager() {
        return storeMemberManager;
    }
    
    public void setStoreMemberManager(IStoreMemberManager storeMemberManager) {
        this.storeMemberManager = storeMemberManager;
    }
    
    public GoodsManager getGoodsManager() {
        return goodsManager;
    }
    
    public void setGoodsManager(GoodsManager goodsManager) {
        this.goodsManager = goodsManager;
    }

}

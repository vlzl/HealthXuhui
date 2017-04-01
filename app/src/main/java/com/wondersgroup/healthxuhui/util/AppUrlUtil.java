package com.wondersgroup.healthxuhui.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.text.TextUtils;

import com.wondersgroup.healthxuhui.data.entity.SecondItemEntity;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 通过此类获取app相关的url
 * Created by yangjinxi on 2016/6/27.
 */
public class AppUrlUtil {
    private static final String APP_URL_FILE = "appurl.xml";

    private static final String IP_NODE = "ip";
    private static final String PORT_NODE = "port";
    private static final String APPNAME_NODE = "appname";
    private static final String WEB_NODE = "web";
    private static final String ITEM_NODE = "item";
    private static final String NAME_NODE = "name";
    private static final String URL_NODE = "url";
    private static final String ACTION_NODE = "action";

    private static final String HOME_URL = "home_url";



    private static Map<String, Object> map = new HashMap<String, Object>();


    /**
     * 获取首页地址
     * @param context
     * @return
     */
    public static String getHomeUrl(Context context){
        String homeUrl = (String) map.get(HOME_URL);
        if (TextUtils.isEmpty(homeUrl)){
            parse(context);
            homeUrl = (String) map.get(HOME_URL);
        }
        return homeUrl;
    }

    /**
     * 根据itemName 获取二级菜单
     * @param context
     * @param itemName
     * @return
     */
    public static List<SecondItemEntity> getSecondItemListByItemName(Context context, String itemName){
        List<SecondItemEntity> list = (List<SecondItemEntity>) map.get(itemName);
        if (list == null || list.size() == 0){
            parse(context);
            list = (List<SecondItemEntity>) map.get(itemName);
        }
        return list;
    }

    /**
     * 根据名称获取action
     * @param context
     * @param name
     * @return
     */
    public static String getActionByName(Context context, String name){
        String action = (String) map.get(name);
        if (TextUtils.isEmpty(action)){
            parse(context);
            action = (String) map.get(name);
        }
        return action;
    }


    /**
     * 解析appurl.xml文件
     * @param context
     */
    private static void parse(Context context) {
        AssetManager manager = context.getAssets();
        InputStream is = null;
        try {
            is = manager.open(APP_URL_FILE);
            Document document = XmlUtil.getDocument(is);
            parseDocument(document);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 解析documents
     * @param document
     */
    private static void parseDocument(Document document) {
        String ip = document.getElementsByTagName(IP_NODE).item(0).getFirstChild().getNodeValue();
        String port = document.getElementsByTagName(PORT_NODE).item(0).getFirstChild().getNodeValue();
        String appname = document.getElementsByTagName(APPNAME_NODE).item(0).getFirstChild().getNodeValue();
        String homeUrl = ip + ":" + port + "/" + appname;
        map.put(HOME_URL, homeUrl);

        NodeList actionNodeList = document.getElementsByTagName(ACTION_NODE);//获取节点为action的列表
        int actionLength = actionNodeList.getLength();
        for (int i = 0; i < actionLength; i++){
            Element actionElement = (Element) actionNodeList.item(i);
            String actionName = actionElement.getAttribute("name");
            String action = actionElement.getFirstChild().getNodeValue();
            map.put(actionName, homeUrl + action);
        }

        NodeList webNodeList = document.getElementsByTagName(WEB_NODE);//获取节点为web的列表
        int webLength = webNodeList.getLength();
        for (int i = 0; i < webLength; i++){
            Element webElement = (Element)webNodeList.item(i);
            List<SecondItemEntity> itemEntities = new ArrayList<SecondItemEntity>();
            String webName = webElement.getAttribute("name");
            NodeList itemNodes = webElement.getElementsByTagName(ITEM_NODE);//获取节点为item的列表
            int childLength = itemNodes.getLength();
            for (int j = 0; j < childLength; j++){
                Element itemElement = (Element)itemNodes.item(j);//获取单个item
                String itemName = itemElement.getAttribute("name");
                String name = itemElement.getElementsByTagName(NAME_NODE).item(0).getFirstChild().getNodeValue();
                String url = itemElement.getElementsByTagName(URL_NODE).item(0).getFirstChild().getNodeValue();
                SecondItemEntity entity = new SecondItemEntity();
                entity.setItemName(itemName);
                entity.setName(name);
                entity.setUrl(homeUrl + url);
                itemEntities.add(entity);
            }
            map.put(webName, itemEntities);
        }
        LogUtil.i("解析appurl:" + new Date().toString());
    }
}

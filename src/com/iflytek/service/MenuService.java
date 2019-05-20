package com.iflytek.service;

import java.util.List;

import com.iflytek.dao.MenuDao;
import com.iflytek.pojo.Menu;

public class MenuService {
    private MenuDao menuDao = new MenuDao();

    public List<Menu> getMenus(String username){
        // 调用DAO 来获取数据
        return menuDao.queryAll();
    }
}

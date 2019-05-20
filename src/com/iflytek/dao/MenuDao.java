package com.iflytek.dao;

import java.util.ArrayList;
import java.util.List;

import com.iflytek.pojo.Menu;

public class MenuDao {
    private static final List<Menu> list = new ArrayList<Menu>();
    static {
        init();
    }

    public List<Menu> queryAll(){
        return list;
    }

    private static void init() {
        String menuNames[] = new String[] {"用户管理","用户列表","新增用户","修改用户","系统功能","我的工作台","系统功能","设备管理"};
        String icos[] = new String[] {"glyphicon glyphicon-home","glyphicon glyphicon-home",
                "glyphicon glyphicon-cog","glyphicon glyphicon-grain",
                "glyphicon glyphicon-cog","glyphicon glyphicon-home"
                ,"glyphicon glyphicon-cog","glyphicon glyphicon-grain"};
        String urls[] = new String[] {"","/kdxfjysbbxpt/UserServlet","","","","","",""};

        Menu menu1 = new Menu(1,menuNames[0],icos[0],urls[0]);
        List<Menu> children1 = new ArrayList<Menu>();
        for (int i = 1; i < 4; i++) {
            children1.add(new Menu(i+1,menuNames[i],icos[i],urls[i]));
        }
        menu1.setChildren(children1);

        Menu menu2 = new Menu(5,menuNames[4],icos[4],urls[4]);
        List<Menu> children2 = new ArrayList<Menu>();
        for (int i = 5; i < 8; i++) {
            children2.add(new Menu(i+1,menuNames[i],icos[i],urls[i]));
        }
        menu2.setChildren(children2);
        list.add(menu1);
        list.add(menu2);
    }
}

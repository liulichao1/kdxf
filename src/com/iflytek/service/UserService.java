package com.iflytek.service;

import java.util.List;

import com.iflytek.dao.UserDao;
import com.iflytek.pojo.User;
import com.iflytek.util.Page;

public class UserService {
    UserDao dao = new UserDao();

    /**
     *   这里是业务操作   方法名称和业务名称是一致的
     * @param username
     * @param password
     * @return
     */
    public User login(String username, String password) {

        // model和数据库交互
        return dao.queryByUsernameAndPassword(username, password);
    }

    /**
     *    获取所有的用户数据
     * @return
     */
    public List<User> getAllUser(){
        return dao.queryAll();
    }

    // currrentPage 默认1
    public Page getPageUser(int currentPage, int prePageNum) {
        Page page = new Page(currentPage,prePageNum);
        List<User> userList = dao.queryPageAll((currentPage-1)*prePageNum, prePageNum);
        page.setValues(userList);

        int count = dao.countAll();
        page.setTotalNum(count);
        return page;
    }

    public int addUser(User user) {
        return dao.add(user);
    }
}

package com.iflytek.dao;

import com.iflytek.pojo.User;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
	/**
	 *   和数据库连接
	 * 1、创建数据库
	 * 2、创建用户表
	 * 3、创建用户数据
	 * 4、使用jdbc连接数据库
	 * 		4.1 加入驱动包
	 * 		4.2 加载驱动
	 * 		4.3 获取连接--》 username password url
	 * 		    jdbc.driver=com.mysql.jdbc.Driver
	 jdbc.url=jdbc:mysql://localhost:3306/kdxfjysbbxpt?useUnicode=true&characterEncoding=UTF-8&useSSL=false&autoReconnect=true&failOverReadOnly=false
	 jdbc.name=root
	 jdbc.password=123456
	 * 		4.4 创建statement --》操作sql语句
	 * 		4.5 执行sql 返回ResultSet
	 * 		4.6 循环ResultSet 取值封装对象（List<User>)
	 *
	 * 5、实现增删改查操作
	 * 6、实现登录查询功能
	 * 7、效果演示
	 */
	public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String JDBC_USERNAME = "root";
	public static final String JDBC_PASSWORD = "chao0309@";
	public static final String JDBC_URL = "jdbc:mysql://localhost:3306/kdxf?characterEncoding=UTF-8&useSSL=false";

	private static Connection conn = null;
    private String E_mail;

    public User queryByUsernameAndPassword(String username,String password){
//		User user = new User("admin","admin",18,new java.util.Date(),0,"110110200108071264");
//		if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
//			return user;
//		}
//		return null;
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection(JDBC_URL,JDBC_USERNAME,JDBC_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
		User user = null;
		String sql = "select id, username, password,  from user where username = ? and password = ?";
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, username);
			psmt.setString(2, password);

			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String un = rs.getString("username");
				String pw = rs.getString("password");
				String em = rs.getString("E_mail");
				user = new User(un, pw, em);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public List<User> queryAll(){
		List<User> userList = new ArrayList<User>();
		User user = null;
		String sql = "select id, username, password, E_mail from user";
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);

			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String un = rs.getString("username");
				String pw = rs.getString("password");
				String em = rs.getString("E_mail");
				user = new User(un, pw, em);
				user.setId(id);
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}

	// start 默认是从0开始
	public List<User> queryPageAll(int start, int prePageNum){
		List<User> userList = new ArrayList<User>();
		User user = null;
		String sql = "select id, username, password, E_mail from user limit ?,?";
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setInt(1, start);
			psmt.setInt(2, prePageNum);

			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String un = rs.getString("username");
				String pw = rs.getString("password");
				String em = rs.getString("E_mail");
				user = new User(un, pw, E_mail);
				user.setId(id);
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}

	public int countAll(){
		String sql = "select count(1) from user";
		int count = 0;
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);

			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
    public int add(User user){
        String sql = "insert into user(username, password, E_mail) values (?,?,?)";
        try {
                PreparedStatement psmt = conn.prepareStatement(sql);
                conn.setAutoCommit(false);
                psmt.setString(1, user.getUsername());
                psmt.setString(2, user.getPassword());
                psmt.setString(3,user.getE_mail());

                int res = psmt.executeUpdate();
                conn.commit();
                return res;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            return -1;
        }
    }

    public int edit(User user){
        String sql = "update user set sex = ?";
        if (user.getPassword() != null) {
            sql += ",password = ?";
        }

        sql += "where id = ?";

        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            conn.setAutoCommit(false);

            int index = 1;
            if (user.getPassword() != null) {
                psmt.setString(++index, user.getPassword());
            }

            psmt.setInt(++index, user.getId());
            int res = psmt.executeUpdate();
            conn.commit();
            return res;
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            return -1;
        }
    }

    public int del(User user){
        String sql = "delete from user where id = ?";
        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            conn.setAutoCommit(false);
            psmt.setInt(1, user.getId());
            int res = psmt.executeUpdate();
            conn.commit();
            return res;
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            return -1;
        }
    }

    public static void main(String[] args) {
        UserDao dao = new UserDao();
//		User user = dao.queryByUsernameAndPassword("user", "user");
//		System.out.println(user);
        User user = new User("123","123","1234656@qq.com");
        dao.add(user);
    }
}
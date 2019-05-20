package com.iflytek.servlet;

import com.iflytek.pojo.User;
import com.iflytek.service.UserService;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserService();
        /**
         * 2019-04-23 下午：
         *   实现列表页面分页功能：
         *  1、写一个分页sql --》mysql库
         *  	select * from user limit 0,5;
         select * from user limit 5,5;
         *  2、分页封装 Page
         *    1) 当前页码 currentPage
         *    2) 每一页显示的条数 prePageNum
         *    3) 总页数 totalNum
         *    4) 每一页显示的结果数据 values
         *  3、查询数据库，将查询的结果数据封装成分页信息
         *  4、实现分页功能
         */
        String currentPage = request.getParameter("currentPage");
        if (currentPage == null) {
            request.setAttribute("page", userService.getPageUser(1, 5));
        }else {
            int p = Integer.parseInt(currentPage);
            if (p < 1) {
                p = 1;
            }
            request.setAttribute("page", userService.getPageUser(p, 5));
        }

        request.getRequestDispatcher("jsp/userList.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

    /**
     * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
     */
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String add_username = request.getParameter("add_username");
        String add_password = request.getParameter("add_password");
        String add_confirmPassword = request.getParameter("add_confirmPassword");
        String add_E_mail = request.getParameter("add_E_mail");

        // 封装Java对象
        User user = new User();
        user.setUsername(add_username);
        user.setPassword(add_password);

        // 新增用户
        UserService userService = new UserService();
        int res = userService.addUser(user);
        if (res == -1) {
            throw new ServletException("数据库错误");
        }else {
            // 新增成功后，跳转到列表页面    展示新增后的结果
//			request.getRequestDispatcher("UserServlet").forward(request, response);
            response.sendRedirect("UserServlet");
        }
    }
    /**
     * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
     */
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }
}

package com.iflytek.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.iflytek.pojo.Menu;
import com.iflytek.pojo.User;
import com.iflytek.service.MenuService;
import com.iflytek.service.UserService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
//		throw new ServletException("系统错误");
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 1、设置编码
         * 2、获取页面的参数
         * 3、参数校验
         *
         * 4、请求javabean model层处理业务
         * 5、model和数据库交互
         * 6、获取数据库交互的结果
         *
         * 7、选择合适的view 页面进行跳转
         */

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String image = request.getParameter("image");
        String rememberMe = request.getParameter("rememberMe");

        /**
         * 设置记住我
         *
         */
        Cookie nameCookie = new Cookie("username", username);
        Cookie passwordCookie = new Cookie("password", password);
        nameCookie.setPath(request.getContextPath()+"/");      //设置Cookie的有效路径
        passwordCookie.setPath(request.getContextPath()+"/");//设置Cookie的有效路径
        if(rememberMe != null && "yes".equals(rememberMe)){            //有记住我，就设置cookie的保存时间
            nameCookie.setMaxAge(7*24*60*60);//设置日期为一星期
            passwordCookie.setMaxAge(7*24*60*60);
        }else{                             //没有记住我，设置cookie的时间为0
            nameCookie.setMaxAge(0);
            passwordCookie.setMaxAge(0);
        }


        String verCode = (String)request.getSession().getAttribute("text");
        if (!verCode.equalsIgnoreCase(image)) {
            request.setAttribute("error", "验证码输入错误");
            request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
            return;
        }

        /**
         * 	业务处理
         *	1、需要业务处理的javabean Model类   ----->  service
         *  2、在service包下面来进行创建 UserService -----> 为了实现某些功能
         *  3、在UserService类中写相关操作的方法 -----> User login(String username, String password)
         *  4、编写login函数（方法）来实现登录功能 ------>  通过返回User对象来判断（User == null 用户名密码错误）
         */
        UserService service = new UserService();
        User u = service.login(username, password);
        if (u == null) {
            // 选择视图
            request.setAttribute("error", "用户名或者密码错误");
            request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
            return;
        }

        response.addCookie(nameCookie);
        response.addCookie(passwordCookie);
        request.getSession().setAttribute("user", username);

        /**
         * 2019-4-22
         * 要做什么事情：
         * 	1、当我没有登录时候，不能够访问main.jsp主界面
         *  2、我们使用response.sendRedirect方法，将我的main.jsp这个暴露出来（不安全）
         *
         *  1） response.sendRedirct 方式跳转页面与 request.getRequestDispatcher("jsp/login.jsp").forward跳转页面的区别
         *  2） Filter技术：过滤器的作用,可以过滤请求
         *
         *  response.sendRedirct：重定向方式跳转页面，暴露了请求的资源 ，不能够将request数据传递给页面
         *  forward方式：forward方式页面转发，不暴露请求的资源，可以将reqeust数据传递给页面
         *
         *  Filter:能做哪些事情？
         *  1、登录校验 --- 过滤器没有登录时不能访问系统资源
         *  2、过滤一些重要的系统资源如 .jsp .css .js ......
         *  3、统一设置请求的系统编码格式
         *  4、可以统一设置系统的错误信息
         *
         *  特性：统一设置，统一处理  需要使用filter
         */

//		response.sendRedirect("jsp/main.jsp");

        /**
         * 2019-4-23
         * 1、菜单的读取 ---》 来源数据库或者来源内存
         * 		采用内存的方式来实现
         * 		service --> dao
         *    1) 定义一个Menu类来存储菜单信息（ID,NAME,URL,ICO）
         *    2) 定义一个MenuService来获取系统中的菜单的信息
         *    3) 定义一个MenuDao 来获取数据库的信息（自定义创建Menu信息）
         * 2、列表的实现 ---》 数据的读取、修改、新增、删除、分页
         * 		读取、分页
         * 	  1)  填写一个servlet ---> 展示页面数据  UserServlet
         *    2) Get请求中，使用UserService获取数据库数据并保存到 request 属性数据中去
         *    3) 在页面中使用 jstl标签 将数据循环迭代出来
         */

        // 获取菜单数据
        MenuService menuService = new MenuService();
        List<Menu> menuList = menuService.getMenus(username);
        request.getSession().setAttribute("menuList", menuList);


        request.getRequestDispatcher("jsp/main.jsp").forward(request, response);

    }
}

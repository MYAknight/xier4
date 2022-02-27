package serv;

import bean.user;
import mapper.userInfMapper;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

import static sqlUtil.sqlMaker.getSession;
import static util.MD5Util.encode;

public class LoginServlet extends ViewBaseServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String password =req.getParameter("password");
        String enPassword = encode(password);
        String phone =req.getParameter("phone");
        SqlSession sql1 = getSession();
        userInfMapper mapper =sql1.getMapper(userInfMapper.class);
        String s1 = mapper.checkName2Password(phone);
        HttpSession session = req.getSession(true);
        if (enPassword.equals(s1)){
            user user1 = mapper.getUserInf(phone);
            session.setAttribute("userInf",user1);
            super.processTemplate("bookStore/directoryPage",req,resp);
            //这里如果重定向，实际上是回去了，就get不到session里的东西
//            resp.sendRedirect(req.getContextPath() + "/bookStore/directoryPage.html");
        }else {
            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = resp.getWriter();
            writer.write("密码错误，请检查用户名或密码");
        }
        sql1.commit();
        sql1.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}

package serv;

import bean.book;
import mapper.userInfMapper;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static sqlUtil.sqlMaker.getSession;
import static util.MD5Util.encode;

public class adminLoginServ extends ViewBaseServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String password =req.getParameter("password");
        String enPassword = encode(password);
        String name =req.getParameter("name");
        SqlSession sql1 = getSession();
        userInfMapper mapper =sql1.getMapper(userInfMapper.class);
        String s1 = mapper.checkAdminPassword(name);
        HttpSession session = req.getSession();
        System.out.println(s1);
        if (enPassword.equals(s1)){
            List<book> showed = mapper.getShowed();
            session.setAttribute("unCheckedBookList",showed);
            super.processTemplate("adminPage/controlPage",req,resp);
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

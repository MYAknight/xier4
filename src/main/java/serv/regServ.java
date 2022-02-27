package serv;

import bean.user;
import mapper.userInfMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import static util.MD5Util.encode;

public class regServ extends ViewBaseServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String phone =req.getParameter("phone");
        SqlSession sql1 = getSession();
        userInfMapper mapper =sql1.getMapper(userInfMapper.class);
        Integer i = mapper.checkExist(phone);
        if (!(i==null)){
            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = resp.getWriter();
            writer.write("抱歉！该手机已注册");
            resp.sendRedirect(req.getContextPath() + "/bookStore/register01.html");
        }else {
            String username =req.getParameter("username");
            String password =req.getParameter("password");
            String enPassword = encode(password);
            user u1 = new user(username,enPassword,phone);
            System.out.println(u1);
            mapper.addUserInf(u1);
            user user1 = mapper.getUserInf(phone);
            HttpSession session = req.getSession();
            session.setAttribute("userInf",user1);
            System.out.println(user1);
            super.processTemplate("bookStore/directoryPage",req,resp);
        }
        sql1.commit();
        sql1.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
    private static SqlSession getSession() throws IOException {
        String resource = "mybiits-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return sqlSession;
    }
}

package serv;

import bean.book;
import mapper.userInfMapper;
import org.apache.ibatis.session.SqlSession;
import sqlUtil.sqlMaker;
import util.stringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
@WebServlet(name="diffTypeServlet",urlPatterns="/diffTypeServlet")
public class diffTypeServlet extends ViewBaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String type = req.getParameter("type");
        System.out.println(type);
        if (stringUtil.isNotEmpty(type)){
            SqlSession session1 = sqlMaker.getSession();
            userInfMapper mapper =session1.getMapper(userInfMapper.class);
            List<book> book2Show = mapper.getBookByType(type);
            System.out.println(book2Show);
            HttpSession session = req.getSession();
            session.setAttribute("bookList",book2Show);
            super.processTemplate("bookStore/diffType",req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}

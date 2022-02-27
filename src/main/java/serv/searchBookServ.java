package serv;

import bean.book;
import mapper.userInfMapper;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static sqlUtil.sqlMaker.getSession;


//原始版本1，搜索servlet
public class searchBookServ extends ViewBaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("asdfasdfasdf");
        req.setCharacterEncoding("UTF-8");
        String bookName =req.getParameter("bookName");
        SqlSession s1 = getSession();
        userInfMapper mapper =s1.getMapper(userInfMapper.class);
        List<book> bookInf = mapper.getBookInf(bookName);
        HttpSession session = req.getSession();
        session.setAttribute("bookInf",bookInf);
        super.processTemplate("bookStore/front",req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

}

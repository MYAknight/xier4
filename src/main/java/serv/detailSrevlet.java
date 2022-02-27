package serv;

import bean.book;
import mapper.userInfMapper;
import org.apache.ibatis.session.SqlSession;
import sqlUtil.sqlMaker;
import util.stringUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class detailSrevlet extends ViewBaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String bID = req.getParameter("bID");
        System.out.println(bID);
        if (stringUtil.isNotEmpty(bID)){
            SqlSession session1 = sqlMaker.getSession();
            userInfMapper mapper =session1.getMapper(userInfMapper.class);
            book book2Show = mapper.getBookByID(bID);
            req.setAttribute("book",book2Show);
            super.processTemplate("bookStore/detail",req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}

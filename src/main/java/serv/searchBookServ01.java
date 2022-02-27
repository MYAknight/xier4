package serv;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.book;
import mapper.userInfMapper;
import org.apache.ibatis.session.SqlSession;
import sqlUtil.sqlMaker;
import util.stringUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//添加了分页功能的搜索servlet
public class searchBookServ01 extends ViewBaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String pageNum =req.getParameter("pageNum");
        Integer p1 = 1;
        if (stringUtil.isNotEmpty(pageNum)){
             p1 = Integer.parseInt(pageNum);
        }
        System.out.println(p1);
        HttpSession session = req.getSession(true);
        //如果seesion里面没有书名变量，就从请求中拿
        session.setAttribute("pageNum",p1);
        String bookName1 = (String) session.getAttribute("bookName");
        System.out.println(bookName1);
        String bookName =bookName1;
        if (stringUtil.isEmpty(bookName1)){
            bookName =req.getParameter("bookName");
            session.setAttribute("bookName",bookName);
        }else {
            //判断有没有新搜索内容
            String b1 = req.getParameter("bookName");
            if (stringUtil.isNotEmpty(b1)){
                    bookName =req.getParameter("bookName");
                    session.setAttribute("bookName",bookName);
            }
        }
        List<book> bookInf = getBook(p1,bookName,session);
        session.setAttribute("bookInf",bookInf);
        super.processTemplate("bookStore/front",req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    protected static List<book> getBook(int page,String bookName,HttpSession session) throws IOException {
        SqlSession session1 = sqlMaker.getSession();
        userInfMapper mapper = session1.getMapper(userInfMapper.class);
        Map m1 = new HashMap();
        m1.put("name",bookName);
        m1.put("page1",(page-1)*3);
        m1.put("page2",(page*3));
        List bookInfByPage = mapper.getBookInfByPage(m1);
        System.out.println(bookInfByPage);

        int bookListLength = mapper.getBookListLength(bookName);
        //求出总页面数量存入session里
        int size = (bookListLength+2)/3;
        //之前试着用size做变量名发现不行，应该是size本身也是thymeleaf的关键字
        session.setAttribute("len",size);
        System.out.println(size);
        session1.commit();
        session1.close();
        return bookInfByPage;
    }

}

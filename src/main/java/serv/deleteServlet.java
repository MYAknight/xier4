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
import java.io.File;
import java.io.IOException;

@WebServlet("/deleteServlet")
public class deleteServlet extends ViewBaseServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String book_ID = req.getParameter("book_ID");
        if (stringUtil.isNotEmpty(book_ID)){
            SqlSession session1 = sqlMaker.getSession();
            userInfMapper mapper = session1.getMapper(userInfMapper.class);
            book b1 = mapper.getBookByID(book_ID);
            System.out.println(b1);
            String s2="/WEB-INF/"+b1.getContent();
            String path=req.getSession().getServletContext().getRealPath(s2);
            File file = new File(path);
            boolean delete = file.delete();
            if (delete){
                mapper.deleteByID(b1.getBook_ID());
            }
            session1.commit();
            session1.close();
            resp.sendRedirect("adminPage/controlPage.html");
        }else {
            System.out.println("不存在指定内容");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}

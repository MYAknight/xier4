package serv;

import bean.book;
import bean.user;
import bean.myStar;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/userServlet")
public class userServlet extends ViewBaseServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String method = req.getParameter("method");
        System.out.println(method);
        if (stringUtil.isNotEmpty(method)) {
            switch (method) {
                case "logout":
                    logout(req, resp);
                    break;
                case "addMyStar":
                    addMyStar(req, resp);
                    break;
                case "myCollection":
                    myCollection(req, resp);
                    break;
                default:
                    break;
            }
        }
    }

//    用户登出（管理员也用这个就行）
    protected void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        processTemplate("index", request, response);
    }

//    添加新收藏
    protected void addMyStar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        user userInf = (user) session.getAttribute("userInf");
        String bID = request.getParameter("bID");
        String phone = userInf.getphone();
        SqlSession session1 = sqlMaker.getSession();
        userInfMapper mapper = session1.getMapper(userInfMapper.class);
        int UID = mapper.checkUserID(phone);
        int BID = Integer.parseInt(bID);
        myStar myStar = new myStar(BID, UID);
        mapper.addMyStars(myStar);
        session1.commit();
        session1.close();
    }


//    转到我的收藏页面
    protected void myCollection(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        user userInf = (user) session.getAttribute("userInf");
        String phone = userInf.getphone();
        SqlSession session1 = sqlMaker.getSession();
        userInfMapper mapper = session1.getMapper(userInfMapper.class);
        int UID = mapper.checkUserID(phone);
        List<Integer> userStarBooks = mapper.getUserStarBooks(UID);
        List<book> bookInf= new ArrayList<>();
        for (int i = 0; i < userStarBooks.size(); i++) {
            String BID = String.valueOf(userStarBooks.get(i));
            book b1 = mapper.getBookByID(BID);
            bookInf.add(b1);
        }
        System.out.println(bookInf);
        session.setAttribute("bookInf",bookInf);
        session1.commit();
        session1.close();
        super.processTemplate("bookStore/myStars",request,response);

    }

}

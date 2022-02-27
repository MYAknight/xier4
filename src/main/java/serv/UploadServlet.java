package serv;

import bean.book;
import mapper.userInfMapper;
import org.apache.ibatis.session.SqlSession;
import sqlUtil.sqlMaker;
import util.newFileUtil;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

//使用@WebServlet配置UploadServlet的访问路径
@WebServlet(name = "UploadServlet", urlPatterns = "/UploadServlet")
//使用注解@MultipartConfig将一个Servlet标识为支持文件上传
@MultipartConfig//标识Servlet支持文件上传
public class UploadServlet extends ViewBaseServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String name = request.getParameter("name");
        String intro = request.getParameter("intro");
        String type = request.getParameter("type");
        String front = "bookStore/pageImage/moren.png";
        String c1 = "bookStore/bookContent/book000";
        //存储路径
        String savePath = request.getServletContext().getRealPath("/WEB-INF/bookStore/bookContent");
        SqlSession session1 = sqlMaker.getSession();
        userInfMapper mapper = session1.getMapper(userInfMapper.class);
        int nextId = mapper.getNextId();
        String id = nextId + "";
        newFileUtil.makeNewFileInWebInf(id);
        savePath = savePath + File.separator + "book000" + nextId;
        Part part = request.getPart("file");
        //通过表单file控件(<input type="file" name="file">)的名字直接获取Part对象
        String header = part.getHeader("content-disposition");
        String fileName = getFileName(header);
        String s1 = "bookStore/bookContent"+ File.separator + "book000" + nextId;
        String inC = s1+ File.separator + fileName;
        System.out.println(savePath);
        String content = savePath + File.separator + fileName;
        book b1 = new book(name, "0", intro, front, type, inC, 2);
        mapper.addNewBook(b1);
        part.write(content);
        PrintWriter out = response.getWriter();
        out.println("上传成功");
        out.flush();
        out.close();
        session1.commit();
        session1.close();
    }

    public String getFileName(String header) {
        System.out.println(header);
        String[] tempArr1 = header.split(";");
        String[] tempArr2 = tempArr1[2].split("=");
        String fileName = tempArr2[1].substring(tempArr2[1].lastIndexOf("\\") + 1).replaceAll("\"", "");
        return fileName;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }
}
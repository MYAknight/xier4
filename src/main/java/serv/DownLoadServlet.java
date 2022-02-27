package serv;

import bean.book;
import mapper.userInfMapper;
import org.apache.ibatis.session.SqlSession;
import sqlUtil.sqlMaker;
import util.stringUtil;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

public class DownLoadServlet extends HttpServlet {

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bID = req.getParameter("bID");
        System.out.println(bID);
        if (stringUtil.isNotEmpty(bID)){
            SqlSession session1 = sqlMaker.getSession();
            userInfMapper mapper =session1.getMapper(userInfMapper.class);
            book book2Show = mapper.getBookByID(bID);
            String content = book2Show.getContent();
            String fileRoot = this.getServletContext().getRealPath("/WEB-INF");
            String finalPath=fileRoot+File.separator+content;
            System.out.println(finalPath);
            File f = new File(finalPath);
            if(f.exists()){
                FileInputStream fis = new FileInputStream(f);
                String filename= URLEncoder.encode(f.getName(),"utf-8"); //解决中文文件名下载后乱码的问题
                byte[] b = new byte[fis.available()];
                fis.read(b);
                resp.setCharacterEncoding("utf-8");
                resp.setHeader("Content-Disposition","attachment; filename="+filename+"");
                //获取响应报文输出流对象
                ServletOutputStream out =resp.getOutputStream();
                //输出
                out.write(b);
                out.flush();
                out.close();
            }else {
                System.out.println("wrong???");
                resp.setContentType("text/html;charset=UTF-8");
                PrintWriter writer = resp.getWriter();
                writer.write("来晚了哦，文件已经被删除了");
            }
        }else {
            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = resp.getWriter();
            writer.write("来晚了哦，文件已经被删除了");
        }



    }

    @Override
    public void init() throws ServletException {
        super.init();
    }
}

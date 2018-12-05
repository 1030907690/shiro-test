package controller;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Zhou Zhong Qing
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/12/5 16:23
 */
@WebServlet("/session")
public class SessionServlet extends HttpServlet {

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        System.out.println(request.getRemoteAddr());
        System.out.print(request.getRemoteHost() + " : " + request.getRemotePort());

        String sesssionID = request.getSession().getId();
        System.out.println("-----------tomcat2---sesssionID-------" + sesssionID);

        String testKey = (String)request.getSession().getAttribute("testKey");
        System.out.println("-----------tomcat2-testKey-------" + testKey);

        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append("tomcat2 ---- sesssionID : " + sesssionID);
            out.append("{\"name\":\"dufy2\"}" + "\n");
            out.append("tomcat2 ----- testKey : " + testKey + "\n");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(out != null){
                out.close();
            }
        }

    }

}
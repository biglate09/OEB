package Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Created by USER on 3/13/2017.
 */
@WebServlet("/LogOutServlet")
public class LogOutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String target = "/WEB-INF/index.jsp";
        HttpSession hs = request.getSession(false);
        if(hs != null){
            hs.invalidate();
        }

        Cookie cookies[] = request.getCookies();
        if(cookies != null){
            for (int i = 0;i<cookies.length;i++){
                Cookie c = cookies[i];
                c.setMaxAge(0);
                response.addCookie(c);
            }
        }
        getServletContext().getRequestDispatcher(target).forward(request,response);
    }
}

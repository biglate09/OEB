package Servlet;

import Model.ActorManagement;
import Model.Restaurant;
import Model.RestaurantOwner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Created by USER on 3/13/2017.
 */
@WebServlet("/ToIndexServlet")
public class ToIndexServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String target = "/WEB-INF/index.jsp";
        Cookie[] c = request.getCookies();
        HttpSession hs = request.getSession(false);
        if(hs == null || hs.getAttribute("restaurantOwner") == null){
            String cookieVal = ActorManagement.chkCookie(c);
            if(cookieVal != null){
                RestaurantOwner ro = RestaurantOwner.signInForCookie(ActorManagement.decryptPassword(cookieVal));
                target = "/WEB-INF/emshome.jsp";
                hs = request.getSession();
                hs.setAttribute("restaurantOwner",ro);
            }
        }else{
            target = "/WEB-INF/emshome.jsp";
        }
        getServletContext().getRequestDispatcher(target).forward(request,response);
    }
}

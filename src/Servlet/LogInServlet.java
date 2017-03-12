/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Model.ActorManagement;
import Model.RestaurantOwner;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


@WebServlet("/LogInServlet")
public class LogInServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberme = request.getParameter("remember-me");
        String target = "/index.jsp";
        RestaurantOwner ro = null;
        Cookie[] c = request.getCookies();
        if(c != null){
            String cookieVal = ActorManagement.chkCookie(c);
            if(cookieVal != null){
                ro = RestaurantOwner.signInForCookie(ActorManagement.decryptPassword(cookieVal));
                target = "/WEB-INF/home.jsp";
            }
        }

        if(ro == null){
            ro = RestaurantOwner.signIn(username,password);
            if(ro != null){
                HttpSession hs = request.getSession();
                hs.setAttribute("restaurantOwner",ro);
                if(rememberme != null){
                    Cookie cookie = new Cookie("restaurantOwner",ActorManagement.encryptPassword(ro.getRestUserName()));
                    cookie.setMaxAge(Integer.MAX_VALUE);
                    response.addCookie(cookie);
                }
                target = "/WEB-INF/home.jsp";
            }else{
                request.setAttribute("msg","ชื่อผู้ใช้หรือรหัสผ่านของคุณ ไม่ถูกต้อง");
                request.setAttribute("username",username);
                if(rememberme != null){
                    request.setAttribute("check",true);
                }
            }
        }
        getServletContext().getRequestDispatcher(target).forward(request,response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }


}

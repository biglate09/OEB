/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Model.RestaurantOwner;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/LogInServlet")
public class LogInServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String target = "/index.jsp";
        RestaurantOwner ro = RestaurantOwner.signIn(username,password);
        if(ro != null){
            HttpSession hs = request.getSession();
            hs.setAttribute("restaurantOwner",ro);
            target = "/WEB-INF/home.jsp";
        }else{
            request.setAttribute("username",username);
        }
        getServletContext().getRequestDispatcher(target).forward(request,response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

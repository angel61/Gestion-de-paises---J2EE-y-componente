/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ControlBD extends HttpServlet {

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String op = request.getParameter("accion");
        if (op.equals("neodatis")) {
            getServletConfig().getServletContext().setAttribute("tipo", 2);
            RequestDispatcher rd = request.getRequestDispatcher("/menu.html");
            rd.forward(request, response);

        }
        if (op.equals("mysql")) {
            getServletConfig().getServletContext().setAttribute("tipo", 1);
            RequestDispatcher rd = request.getRequestDispatcher("/menu.html");
            rd.forward(request, response);
        }
        if (op.equals("oracle")) {
            getServletConfig().getServletContext().setAttribute("tipo", 3);
            RequestDispatcher rd = request.getRequestDispatcher("/menu.html");
            rd.forward(request, response);
        }
    }
    
}

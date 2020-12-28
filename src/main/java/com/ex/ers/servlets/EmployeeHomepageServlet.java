package com.ex.ers.servlets;

import com.ex.ers.daos.ReimbursementRequestDAO;
import com.ex.ers.daos.ReimbursementRequestDAOImpl;
import com.ex.ers.models.ReimbursementRequest;
import com.ex.ers.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Servlet to handle employee homepage responses and requests
 */
public class EmployeeHomepageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if(user!=null){
            req.setAttribute("user",user);
            ReimbursementRequestDAO dao = new ReimbursementRequestDAOImpl();
            List<ReimbursementRequest> requests = dao.getAllByUser(user.getUserID());
            req.setAttribute("requests",requests);
            req.getRequestDispatcher("EmployeeHomepage.jsp").forward(req, resp);
        }
        else{
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }
}

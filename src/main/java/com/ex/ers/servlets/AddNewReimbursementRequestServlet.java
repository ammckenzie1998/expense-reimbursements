package com.ex.ers.servlets;

import com.ex.ers.daos.ReimbursementRequestDAO;
import com.ex.ers.daos.ReimbursementRequestDAOImpl;
import com.ex.ers.models.ReimbursementRequest;
import com.ex.ers.models.RequestStatus;
import com.ex.ers.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet to handle reimbursement request creation responses and requests
 */
public class AddNewReimbursementRequestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if(user!=null){
            req.setAttribute("user",user);
            req.getRequestDispatcher("AddNewReimbursementRequest.jsp").forward(req, resp);
        }
        else{
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if(user!=null){
            req.setAttribute("user",user);
            String title = req.getParameter("title");
            String description = req.getParameter("desc");
            String comments = req.getParameter("comments");

            ReimbursementRequest newRequest = new ReimbursementRequest();
            ReimbursementRequestDAO dao = new ReimbursementRequestDAOImpl();
            newRequest.setTitle(title);
            newRequest.setDescription(description);
            newRequest.setRequestComments(comments);
            newRequest.setUser(user);
            newRequest.setStatus(RequestStatus.NEW.toString());
            dao.create(newRequest);
            resp.sendRedirect(req.getContextPath() + "/employee-home");
        }
        else{
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }
}

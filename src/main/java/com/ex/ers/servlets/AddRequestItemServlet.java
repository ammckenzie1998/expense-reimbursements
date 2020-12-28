package com.ex.ers.servlets;

import com.ex.ers.daos.ReimbursementRequestDAO;
import com.ex.ers.daos.ReimbursementRequestDAOImpl;
import com.ex.ers.daos.RequestItemDAOImpl;
import com.ex.ers.models.ReimbursementRequest;
import com.ex.ers.models.RequestItem;
import com.ex.ers.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
/**
 * Servlet to handle request item creation responses and requests
 */
public class AddRequestItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        int requestID = Integer.parseInt(req.getParameter("id"));

        if(user!=null){
            req.setAttribute("user",user);
            ReimbursementRequestDAO dao = new ReimbursementRequestDAOImpl();
            ReimbursementRequest request = dao.getByID(requestID);
            req.setAttribute("request",request);

            req.getRequestDispatcher("AddRequestItem.jsp").forward(req, resp);
        }
        else{
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        int requestID = Integer.parseInt(req.getParameter("id"));

        if(user!=null){
            req.setAttribute("user",user);
            ReimbursementRequestDAO dao = new ReimbursementRequestDAOImpl();
            ReimbursementRequest request = dao.getByID(requestID);
            RequestItemDAOImpl requestItemDAO = new RequestItemDAOImpl();
            RequestItem newItem = new RequestItem();
            //Need to check input
            newItem.setReimbursementRequest(request);
            newItem.setAmount(Double.parseDouble(req.getParameter("amt")));
            newItem.setDescription(req.getParameter("desc"));
            newItem.setItemType(req.getParameter("type"));
            newItem.setItemComments(req.getParameter("comments"));
            requestItemDAO.create(newItem);

            resp.sendRedirect(req.getContextPath() + "/view-request?id="+requestID);
        }
        else{
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }
}

package com.ex.ers.servlets;

import com.ex.ers.daos.ReimbursementRequestDAO;
import com.ex.ers.daos.ReimbursementRequestDAOImpl;
import com.ex.ers.daos.RequestItemDAO;
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

/**
 * Servlet to handle reimbursement request item edits responses and requests
 */
public class EditRequestItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if(user!=null){
            int itemID = Integer.parseInt(req.getParameter("id"));
            RequestItemDAO dao = new RequestItemDAOImpl();
            RequestItem item = dao.getByID(itemID);
            req.setAttribute("item",item);
            req.getRequestDispatcher("EditRequestItem.jsp").forward(req, resp);
        }
        else{
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        int itemID = Integer.parseInt(req.getParameter("id"));

        if(user!=null){
            req.setAttribute("user",user);
            RequestItemDAO dao = new RequestItemDAOImpl();
            RequestItem item = dao.getByID(itemID);
            item.setAmount(Double.parseDouble(req.getParameter("amt")));
            item.setItemType(req.getParameter("type"));
            item.setDescription(req.getParameter("desc"));
            item.setItemComments(req.getParameter("comments"));
            dao.update(item);
            req.setAttribute("request",item.getReimbursementRequest());

            resp.sendRedirect(req.getContextPath() + "/view-request?id="+item.getReimbursementRequest().getRequestID());
        }
        else{
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }
}

package com.example;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class TicketServlet extends HttpServlet {

    private TicketDAO ticketDAO;

    @Override
    public void init() throws ServletException {
        ticketDAO = new TicketDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("edit".equals(action)) {
            String id = request.getParameter("id");
            Ticket ticket = ticketDAO.getTicketById(id);
            request.setAttribute("ticket", ticket);
            request.getRequestDispatcher("/edit.jsp").forward(request, response);
        } else {
            List<Ticket> tickets = ticketDAO.getAllTickets();
            request.setAttribute("tickets", tickets);
            request.getRequestDispatcher("/tickets.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("delete".equals(action)) {
            String id = request.getParameter("id");
            ticketDAO.deleteTicket(id);
            response.sendRedirect(request.getContextPath() + "/tickets");
        } else if ("update".equals(action)) {
            String id = request.getParameter("id");
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            String status = request.getParameter("status");

            Ticket ticket = ticketDAO.getTicketById(id);
            if (ticket != null) {
                ticket.setTitle(title);
                ticket.setDescription(description);
                ticket.setStatus(status);
                ticketDAO.updateTicket(ticket);
            }
            response.sendRedirect(request.getContextPath() + "/tickets");
        } else {
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            String status = request.getParameter("status");

            Ticket ticket = new Ticket(title, description, status);
            ticketDAO.createTicket(ticket);

            response.sendRedirect(request.getContextPath() + "/tickets");
        }
    }

    @Override
    public void destroy() {
        ticketDAO.close();
    }
}
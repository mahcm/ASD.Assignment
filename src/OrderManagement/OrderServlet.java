/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import entity.ItemArrangement;
import entity.Order1;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mah
 */
@WebServlet(urlPatterns = {"/OrderServlet"})
public class OrderServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @PersistenceContext
    EntityManager em;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date d = new Date();
            String date = formatter.format(d);
            HttpSession session = request.getSession();
            session.setAttribute("today", date);

            Query query1 = em.createNamedQuery("Order1.findAll", entity.Order1.class);
            List<Order1> orderlist = query1.getResultList();

            List<Order1> todaylistp = new ArrayList<>();
            List<Order1> todaylistd = new ArrayList<>();
            List<String> monthlist = new ArrayList<>();
            List<String> yearlist = new ArrayList<>();

            String year, today, month;

            for (Order1 order1 : orderlist) {
                today = order1.getFinaldate();
                if (today.equals(date)) {
                    if (order1.getOrdertype().equals('P')) {
                        todaylistp.add(order1);
                    } else {
                        todaylistd.add(order1);
                    }
                }
                
                month = today.substring(3, 5);
                switch (month) {
                    case "01":
                        if (!monthlist.contains("Jan")) {
                            monthlist.add("Jan");
                        }
                        break;
                    case "02":
                        if (!monthlist.contains("Feb")) {
                            monthlist.add("Feb");
                        }
                        break;
                    case "03":
                        if (!monthlist.contains("Mar")) {
                            monthlist.add("Mar");
                        }
                        break;
                    case "04":
                        if (!monthlist.contains("Apr")) {
                            monthlist.add("Apr");
                        }
                        break;
                    case "05":
                        if (!monthlist.contains("May")) {
                            monthlist.add("May");
                        }
                        break;
                    case "06":
                        if (!monthlist.contains("Jun")) {
                            monthlist.add("Jun");
                        }
                        break;
                    case "07":
                        if (!monthlist.contains("Jul")) {
                            monthlist.add("Jul");
                        }
                        break;
                    case "08":
                        if (!monthlist.contains("Aug")) {
                            monthlist.add("Aug");
                        }
                        break;
                    case "09":
                        if (!monthlist.contains("Sep")) {
                            monthlist.add("Sep");
                        }
                        break;
                    case "10":
                        if (!monthlist.contains("Oct")) {
                            monthlist.add("Oct");
                        }
                        break;
                    case "11":
                        if (!monthlist.contains("Nov")) {
                            monthlist.add("Nov");
                        }
                        break;
                    case "12":
                        if (!monthlist.contains("Dec")) {
                            monthlist.add("Dec");
                        }
                        break;
                }

                year = order1.getFinaldate().substring(6, 10);
                if (!yearlist.contains(year) || yearlist.isEmpty()) {
                    yearlist.add(year);
                }
            }

            session.setAttribute("todaylistp", todaylistp);
            session.setAttribute("todaylistd", todaylistd);
            session.setAttribute("monthlist", monthlist);
            session.setAttribute("yearlist", yearlist);
            session.setAttribute(date, date);

            RequestDispatcher rd = request.getRequestDispatcher("order/order_view.jsp");
            rd.forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("alertMsg", ex.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("order/order view.jsp");
            rd.include(request, response);
        }
    }
}

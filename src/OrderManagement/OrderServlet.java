/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import entity.Order1;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
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
import javax.transaction.UserTransaction;

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

    @Resource
    UserTransaction utx;

    Date d = new Date();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String date = formatter.format(d);
            HttpSession session = request.getSession();
            session.setAttribute("today", date);

            Query query1 = em.createNamedQuery("Order1.findAll", entity.Order1.class);
            List<Order1> orderlist = query1.getResultList();

            List<Order1> todaylistp = new ArrayList<>();
            List<Order1> todaylistd = new ArrayList<>();            
            List<Order1> todaylistc = new ArrayList<>();
            List<String> monthlist = new ArrayList<>();
            List<String> yearlist = new ArrayList<>();

            String year, today, month;

            for (Order1 order1 : orderlist) {
                today = order1.getFinaldate();
                if (today.equals(date)) {
                    switch (order1.getOrdertype()) {
                        case 'P':
                            todaylistp.add(order1);
                            break;
                        case 'D':
                            todaylistd.add(order1);
                            break;
                        default:
                            todaylistc.add(order1);
                            break;
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
            
            session.removeAttribute("todaylistp");
            session.removeAttribute("todaylistd");
            session.removeAttribute("todaylistc");

            session.setAttribute("todaylistp", todaylistp);
            session.setAttribute("todaylistd", todaylistd);
            session.setAttribute("todaylistc", todaylistc);
            session.setAttribute("monthlist", monthlist);
            session.setAttribute("yearlist", yearlist);
            session.setAttribute(date, date);

            RequestDispatcher rd = request.getRequestDispatcher("order/order_view.jsp");
            rd.forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("alertMsg", ex.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("order/order_view.jsp");
            rd.include(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String[] list = request.getParameterValues("list");
            Query query1 = em.createNamedQuery("Order1.findByOrderid", entity.Order1.class);

            for (String string : list) {
                query1.setParameter("orderid", string);
                Order1 order = (Order1) query1.getSingleResult();
                order.setCompletedate(d);
                order.setOrdertype('C');

                utx.begin();
                em.merge(order);
                em.flush();
                utx.commit();
            }

            response.sendRedirect("OrderServlet");

        } catch (Exception ex) {
            request.setAttribute("alertMsg", ex.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("order/order_view.jsp");
            rd.include(request, response);
            System.out.print(ex.getMessage());
        }
    }
}

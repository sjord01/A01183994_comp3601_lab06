package ca.bcit.a01183994.lab06;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ca.bcit.a01183994.lab06.domain.GreetingInfo;
import ca.bcit.a01183994.lab06.util.BeanUtilities;
import ca.bcit.a01183994.lab06.util.Constants;

public class GreetingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String inputMessage;
    private String welcomeMessage;
    private String htmlHeadTitle;

    @Override
    public void init() throws ServletException {
        inputMessage = getServletContext().getInitParameter("inputMessage");
        welcomeMessage = getServletContext().getInitParameter("welcomeMessage");
        htmlHeadTitle = getServletContext().getInitParameter("htmlHeadTitle");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("firstName") != null) {
            displayGreeting(request, response);
        } else {
            displayForm(response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GreetingInfo info = new GreetingInfo();
        BeanUtilities.populateBean(info, request);

        if (info.getFirstName() == null || info.getFirstName().trim().isEmpty()) {
            // Redisplay form if input is invalid
            displayForm(response);
        } else {
            // Store valid input in session and redirect
            HttpSession session = request.getSession();
            session.setAttribute("firstName", info.getFirstName());
            response.sendRedirect(request.getContextPath() + "/greeting");
        }
    }

    private void displayForm(HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(Constants.DOCTYPE);
        out.println(Constants.HTML_OPEN);
        out.printf(Constants.HTML_HEAD, htmlHeadTitle);
        out.println(Constants.BODY_OPEN);
        out.println("<h2>" + inputMessage + "</h2>");
        out.println("<form method='post' action=''>");
        out.println("First Name: <input type='text' name='firstName'>");
        out.println("<input type='submit' value='Submit'>");
        out.println("</form>");
        out.println(Constants.BODY_CLOSE);
        out.println(Constants.HTML_CLOSE);
    }

    private void displayGreeting(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        String firstName = (String) session.getAttribute("firstName");

        if (firstName == null || firstName.trim().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM d, yyyy HH:mm:ss");
        String currentDateTime = dateFormat.format(new Date());

        out.println(Constants.DOCTYPE);
        out.println(Constants.HTML_OPEN);
        out.printf(Constants.HTML_HEAD, htmlHeadTitle);
        out.println(Constants.BODY_OPEN);
        out.println("<h2>" + welcomeMessage + "</h2>");
        out.println("<p>Hello, " + firstName + "!</p>");
        out.println("<p>Current date and time: " + currentDateTime + "</p>");
        out.println(Constants.BODY_CLOSE);
        out.println(Constants.HTML_CLOSE);

        session.removeAttribute("firstName"); // Clean up session attribute
    }
}
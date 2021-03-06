package controller;

import model.AccountForm;
import model.AccountUpdateForm;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static model.ServletUtilities.forwardRequest;

@WebServlet(name = "UpdateAccountServlet", urlPatterns = {"/updateAccount"})
public class AccountUpdateServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.sendRedirect("jsp/update_account.jsp");
    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws IOException, ServletException {
        if (isSubmitButtonClicked(request)) {
            AccountUpdateForm accountForm = new AccountUpdateForm(request);
            if (accountForm.isAccountCreationSuccessful()) {
                goToPreviousScreen(response);
            } else {
                reloadBecauseAccountCreateFailed(request, response);
            }
        }
    }

    private boolean isSubmitButtonClicked(HttpServletRequest request) {
        return request.getParameter("submitButton") != null;
    }

    private void goToPreviousScreen(HttpServletResponse response)
            throws IOException {
        response.sendRedirect("jsp/index.jsp");
    }

    private void reloadBecauseAccountCreateFailed(HttpServletRequest request,
                                                  HttpServletResponse response)
            throws IOException, ServletException {
        forwardRequest(this, request, response, "/jsp/update_account.jsp");
    }
}

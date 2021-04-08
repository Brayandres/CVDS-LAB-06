package edu.eci.cvds.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.eci.cvds.servlet.model.Todo;

@WebServlet(urlPatterns = "/OwnServlet")

public class OwnServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter respWr = resp.getWriter();
		Optional<String> opId = Optional.ofNullable(req.getParameter("id"));
		String inputId = (opId.isPresent() && !opId.get().isEmpty()) ? opId.get() : "-1";
		try {
			int id = Integer.parseInt(inputId);
			if (Service.getTodo(id) != null) {
				resp.setStatus(HttpServletResponse.SC_OK);
				java.util.List<Todo> todoList = Arrays.asList(Service.getTodo(id));
				respWr.write(Service.todosToHTMLTable(todoList));
			} else {
				resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
				respWr.write("HTTP 404\n\tPage Not Found.");
			}

		} catch (NumberFormatException e) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		} catch (MalformedURLException e) {
			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		} finally {
			respWr.flush();
		}
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
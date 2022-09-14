package sp.senai.br.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Cargo;
import models.Funcionario;
import sp.senai.br.dao.FuncionarioDAO;

/**
 * Servlet implementation class TesteServlet
 */
@WebServlet("/TesteServlet")
public class TesteServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		FuncionarioDAO fdao = new FuncionarioDAO();
		Funcionario func = null;
		//fdao.novo(func = new Funcionario("Oliveira", "50583575854", Cargo.PROGRAMADOR));
		fdao.alterar("Luiz Gustavo", "50583575854", Cargo.ANALISTA);
		List<Funcionario> lista = fdao.listagem();
		
		PrintWriter pw = response.getWriter();
		
		pw.println("<html>");
		pw.println("<body>");
		
		for (Funcionario fun: lista) {
			pw.println("<div>");
			pw.println(fun.getNome());
			pw.println("</div>");
		}
	}

}

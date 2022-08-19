
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Cargo;
import models.Funcionario;

@WebServlet("/FuncionarioServlet")
public class FuncionarioServlet extends HttpServlet {
	private HttpSession session;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Funcionario> fun = new ArrayList<Funcionario>();

		String nome = request.getParameter("nome");
		String cpf = request.getParameter("cpf");
		String cargo = request.getParameter("select");

		session = request.getSession();
		Object obj = session.getAttribute("keyLista");

		if (obj == null) {
			session.setAttribute("keyLista", fun);
			fun.add(new Funcionario(nome, cpf, Enum.valueOf(Cargo.class, cargo)));
		} else {
			List<Funcionario> fun1 = (List<Funcionario>) obj;
			fun1.add(new Funcionario(nome, cpf, Enum.valueOf(Cargo.class, cargo)));
			fun.addAll(fun1);
		}
		
		PrintWriter writer = response.getWriter();
		
		fun.forEach(func -> writer.println(func.toString()));

		writer.println("ID da sessão: " + session.getId() + "\n");
		writer.println("Hora de Criação: " + new Date(session.getCreationTime()));

	}

}

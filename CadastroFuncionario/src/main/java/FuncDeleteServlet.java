

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Funcionario;

/**
 * Servlet implementation class FuncDeleteServlet
 */
@WebServlet("/FuncDeleteServlet")
@SuppressWarnings("unchecked")
public class FuncDeleteServlet extends HttpServlet {
	private HttpSession session;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Funcionario> fun1 = new ArrayList<Funcionario>();
		PrintWriter writer = response.getWriter();
		
		session = request.getSession();
		Object obj = session.getAttribute("keyLista");
		String cpf = request.getParameter("cpf");
		
		if (obj == null) {			
			writer.println("Não existe funcionários em sua lista");
			
		}else {
			fun1 = (List<Funcionario>) obj;
			
			try {
				for (Funcionario funcionario : fun1) {
					if (funcionario.getCpf().equals(cpf)) 
						fun1.remove(funcionario);
					
				}
				session.setAttribute("keyLista", fun1);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		fun1.forEach(func -> writer.println(func.toString()));
		
		writer.println("<button onclick=location.href='http://localhost:6919/CadastroFuncionario/index.html" 
				+ "';>Voltar para página inicial </button>");
		
		writer.println("ID da sessão: " + session.getId() + "\n");
		writer.println("Hora de Criação: " + new Date(session.getCreationTime()));
	}
}



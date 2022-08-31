

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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Instâncias e Preparamento de Respostas
		List<Funcionario> fun1 = new ArrayList<Funcionario>();
		PrintWriter writer = response.getWriter();
		
		//Pegando a sessão
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("keyLista");
		
		//Pegando os parâmetros get
		String cpf = request.getParameter("cpf");
		
		//Validando se a sessão existe e removendo
		if (obj == null) {			
			writer.println("Não existe funcionários em sua lista");
			
		}else {
			fun1 = (List<Funcionario>) obj;
			
			try {
				for (Funcionario funcionario : fun1) {
					if (funcionario.getCpf().equals(cpf)) 
						fun1.remove(funcionario);
					
				}
				//Atribuindo na mesma memória (List) armazenada na sessão a nova lista
				session.setAttribute("keyLista", fun1);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//Construindo uma página
		fun1.forEach(func -> {
			writer.println(func.toString() + "<button onclick=location.href='http://localhost:6919/CadastroFuncionario/FuncDeleteServlet?cpf=" 
		+ func.getCpf() + "';> Excluir Funcionário </button>" + "<button onclick=location.href='http://localhost:6919/CadastroFuncionario/FuncAlterarServlet?cpf=" 
		+ func.getCpf() + "';> Alterar Funcionário </button>");
		});
		
		writer.println("<button onclick=location.href='http://localhost:6919/CadastroFuncionario/index.html" 
				+ "';>Voltar para página inicial </button>");
		
		writer.println("ID da sessão: " + session.getId() + "\n");
		writer.println("Hora de Criação: " + new Date(session.getCreationTime()));
	}
}



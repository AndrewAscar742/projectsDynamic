

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

import models.Cargo;
import models.Funcionario;

/**
 * Servlet implementation class FuncAlterarServlet
 */
@WebServlet("/FuncAlterarServlet")
public class FuncAlterarServlet extends HttpServlet {
	private String cpf;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter writer = resp.getWriter();
		
		//Pegando o CPF do usuário que será alterado
		cpf = req.getParameter("cpf");
		System.out.println(cpf);
		
		//Construção do formulário
		writer.println("<html><body>" + "<form action=\"/CadastroFuncionario/FuncAlterarServlet\" method=\"post\">"
				+ "		<label>Digite um novo nome</label>"
				+ "		<input name=\"novoNome\" type=\"text\"/>"
				+ "		<label>Escolha seu novo cargo</label>"
				+ "		<select name=\"select\">"
				+ "		<option value=\"GER_PROJETO\"> Gerente de Projeto </option>"
				+ "		<option value=\"ANALISTA\"> Analista </option>"
				+ "		<option value=\"PROGRAMADOR\"> Programador </option>"
				+ "		</select>"
				+ "		<input type=\"submit\"/>"
				+ "	</form>"
				+ "</body> </html>");
	}
	
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Instâncias e Preparamento de Respostas
		PrintWriter writer = response.getWriter();
		List<Funcionario> fun1 = new ArrayList<>();
		
		//Pegando os parâmetros do get
		String novoNome = request.getParameter("novoNome");
		String novoCargo = request.getParameter("select");
		
		//Pegando a sessão
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("keyLista");
		
		//Validando se a sessão existe e alterando
		if (obj == null) {
			writer.println("Não existe nenhuma lista para alterar");
		}else {
			fun1 = (List<Funcionario>) obj;
			
			for (Funcionario funcionario : fun1) {
				if (funcionario.getCpf().equals(cpf)) {
					System.out.println(novoNome + " " + novoCargo);
					Funcionario fun = new Funcionario(novoNome, funcionario.getCpf(), Enum.valueOf(Cargo.class, novoCargo));
					int i = fun1.indexOf(funcionario);
					fun1.set(i, fun);
				}
			}
			
			//Atribuindo na mesma memória (List) armazenada na sessão a nova lista
			session.setAttribute("keyLista", fun1);
		}
		
		//Construindo uma página
		fun1.forEach(func -> {
			writer.println(func.toString() + "<button onclick=location.href='http://localhost:6919/CadastroFuncionario/FuncDeleteServlet?cpf=" 
		+ func.getCpf() + "';> Excluir Funcionário </button>" + "<button onclick=location.href='http://localhost:6919/CadastroFuncionario/FuncAlterarServlet?cpf=" 
		+ func.getCpf() + "';> Alterar Funcionário </button>");
		});
		
		writer.println("<button onclick=location.href='http://localhost:6919/CadastroFuncionario/index.html" 
				+ "';>Voltar para página inicial </button> \n");
		writer.println("ID da sessão: " + session.getId() + "\n");
		writer.println("Hora de Criação: " + new Date(session.getCreationTime()));
	}

}

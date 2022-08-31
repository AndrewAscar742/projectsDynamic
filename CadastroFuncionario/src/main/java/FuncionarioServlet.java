
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

@WebServlet("/FuncionarioServlet")
public class FuncionarioServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Inst�ncias e Preparamento de Respostas
		List<Funcionario> fun = new ArrayList<Funcionario>();
		PrintWriter writer = response.getWriter();
		
		//Pegando os par�metros post
		String nome = request.getParameter("nome");
		String cpf = request.getParameter("cpf");
		String cargo = request.getParameter("select");
		
		//Pegando a sess�o
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("keyLista");
		
		//Validando se a sess�o existe e adicionando
		if (obj == null) {
			fun.add(new Funcionario(nome, cpf, Enum.valueOf(Cargo.class, cargo)));
			session.setAttribute("keyLista", fun);
			
		} else {
			@SuppressWarnings("unchecked")
			List<Funcionario> fun1 = (List<Funcionario>) obj;
			fun = fun1;
			fun.add(new Funcionario(nome, cpf, Enum.valueOf(Cargo.class, cargo)));
			
			//Atribuindo na mesma mem�ria (List) armazenada na sess�o a nova lista
			session.setAttribute("keyLista", fun);
		}
		
		//Construindo uma p�gina
		fun.forEach(func -> {
			writer.println(func.toString() + "<button onclick=location.href='http://localhost:6919/CadastroFuncionario/FuncDeleteServlet?cpf=" 
		+ func.getCpf() + "';> Excluir Funcion�rio </button>" + "<button onclick=location.href='http://localhost:6919/CadastroFuncionario/FuncAlterarServlet?cpf=" 
		+ func.getCpf() + "';> Alterar Funcion�rio </button>"
		+ "<button onclick=location.href='http://localhost:6919/CadastroFuncionario/index.html" + "';>Voltar para p�gina inicial </button>");
		});

		writer.println("ID da sess�o: " + session.getId() + "\n");
		writer.println("Hora de Cria��o: " + new Date(session.getCreationTime()));

	}

}

package br.senai;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GeradorDeSenha
 */
@WebServlet("/GeradorDeSenha")
public class GeradorDeSenha extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> senhas = new ArrayList<String>();
		Random random = new Random();
		
		String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz123456789";
		String digito = request.getParameter("digito");
		String senha = request.getParameter("senha");
		String senhaAux = "";
		
		for (int i = 0; i < Integer.parseInt(senha); i++) {
			if (senhaAux.length() == Integer.parseInt(digito)) 
				senhas.add(senhaAux);
			
			senhaAux = "";
			for (int j = 0; j < Integer.parseInt(digito); j++) { 
				 senhaAux += str.charAt(random.nextInt(str.length()));
			}
		}
		
		PrintWriter saida = response.getWriter();
		saida.println("<html><body><h1>Senhas</h1>" + senhas.toString() + "</body></html>");
	}
	
}

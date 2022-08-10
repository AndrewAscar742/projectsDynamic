

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class OrdenarNum
 */
@WebServlet("/OrdenarNum")
public class OrdenarNum extends HttpServlet {

	private static int vetOrdenado[];

	public static int[] getVetOrdenado() {
		return vetOrdenado;
	}

	public static void setVetOrdenado(int[] vetOrdenado) {
		OrdenarNum.vetOrdenado = vetOrdenado;
	}

	private static int[] converterString(String vet[]) {
		int vetOrdenado[] = new int[vet.length];

		for (int i = 0; i < vet.length; i++) {
			vetOrdenado[i] = Integer.parseInt(vet[i]);
		}
		return vetOrdenado;
	}

	public static int[] ordenarVet(String text) {
		String vet[] = text.split(";");
		int aux = 0;
		setVetOrdenado(converterString(vet));

		for (int i = 0; i < vetOrdenado.length - 1; i++) {
			for (int j = 0; j < vetOrdenado.length - 1; j++) {
				if(vetOrdenado[j] > vetOrdenado[j + 1]) {
					aux = vetOrdenado[j];
					vetOrdenado[j] = vetOrdenado[j + 1];
					vetOrdenado[j + 1] = aux;
				}
			}			
		}

		return vetOrdenado;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String x = req.getParameter("numero");
		int vetOrdenado[] = ordenarVet(x);

		PrintWriter saida = resp.getWriter();
		saida.println("<html><body><h1> Numeros Ordenados </h1>" + Arrays.toString(vetOrdenado) + "</body></html>");

	}
}

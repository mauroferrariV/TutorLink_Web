package servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Usuario;
import logic.Login;

/**
 * Servlet implementation class Signin
 */
@WebServlet({ "/signin", "/SIGNIN", "/SignIn", "/Signin" }) //Aqui estan todas las urls que vamos a aceptar
public class Signin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("get at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario usuario = new Usuario();
		Login ctrl = new Login();
		
		String email = request.getParameter("email"); //sirve para obtener el parametro email que es el name del input dentro del form
		String password = request.getParameter("password");
		
		//Deberia realizar una validacion basica- que tengan contenido, que respete los parametros necesarios 
		
		usuario.setEmail(email);
		usuario.setPassword(password);
	
		usuario = ctrl.validate(usuario);
		//Luego de validar al usuario obtendre el conjunto de todos los usuarios
		LinkedList<Usuario> usuarios = ctrl.getAll();
		
		//Supongamos que deseamos mantener los datos del usuario para el resto de la Sesion
		//Guardaremos los datos en la sesion
		request.getSession().setAttribute("usuario", usuario);//Esto nos va a devolver la sesion si existe y sino creara una. Guarda los atributos en la sesion y ahi quedara los datos del usuario mientras dure la sesion del usuario. Esto nos sirve para referirnos a él, ver si tienes permisos, obtener datos personales. Permanece a traves de varias requests y responses
		request.setAttribute("listaUsuarios", usuarios); //Esto solo lo utilizaremos en la pagina donde lo voy a dirigir, voy a dirigirlo a la pagina de listado de personas, no lo voy a usar constantemente a lo largo de la sesion.
		
		//hay 2 formas de continuar nuestro flujo
		// foward -> envia a traves del propio servlet,va a buscar la nueva pagina y la devuelve en al URL del servlet. En la misma peticion que hubo estoy respondiendo a otra pagina, todo el circuto va por dentro del servidor. 
		// redirect -> se le envia la respuesta al cliente y se le dice "ahora dirigite a esta otra pagina". Hay que pasarle informacion sobre esta nueva pagina. (hay 2 tiempos de envio-respuesta)
		request.getRequestDispatcher("WEB-INF/UserManagement.jsp").forward(request, response); //Sera una pagina que solo se podra acceder por foward en el servlet del Login, pagina propia del servlet del Login
		
		//response.getWriter().append("Bienvenido ").append(usuario.getNombre()).append(" ").append(usuario.getApellido());
		/*response.getWriter().append("Email: ").append(email)
		.append("- Password: ").append(password)
		.append("post at: ").append(request.getContextPath());*/
	}

}
/*
 * Yo en el doGet y doPost recibo dos objetos, request (el pedido hacia el servidor) y response (todo lo que se refiere a la respuesta de esta peticion)
 * */
 
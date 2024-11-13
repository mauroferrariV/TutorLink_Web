package servlet;

import entities.Facultad;
import logic.LogicFacultad;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Servlet implementation class FacultadServlet
 */
@WebServlet({ "/facultad", "/FacultadServlet", "/facultadservlet", "/Facultadservlet", "/FACULTADSERVLET" })
public class FacultadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private LogicFacultad logicFacultad = new LogicFacultad();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FacultadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LinkedList<Facultad> facultades = logicFacultad.getAllFacultades();
        request.setAttribute("facultades", facultades);
        request.getRequestDispatcher("WEB-INF/FacultadManagement.jsp").forward(request, response);
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            Facultad facultad = new Facultad();
            facultad.setNombre(request.getParameter("nombre"));
            facultad.setCiudad(request.getParameter("ciudad"));
            facultad.setPais(request.getParameter("pais"));
            logicFacultad.addFacultad(facultad);
        } else if ("update".equals(action)) {
            Facultad facultad = new Facultad();
            facultad.setId(Integer.parseInt(request.getParameter("id")));
            facultad.setNombre(request.getParameter("nombre"));
            facultad.setCiudad(request.getParameter("ciudad"));
            facultad.setPais(request.getParameter("pais"));
            logicFacultad.updateFacultad(facultad);
        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            logicFacultad.deleteFacultad(id);
        }
        
        response.sendRedirect("facultad");
    }

}

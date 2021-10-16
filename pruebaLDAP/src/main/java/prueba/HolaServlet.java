package prueba;

import java.io.IOException;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HolaServlet
 */
@WebServlet("/HolaServlet")
public class HolaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HolaServlet() { 		//Login()
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void procesoAutentificacionLDAP(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
    	
    	final String SUCCESS = "acceso.jsp";
    	final String FAILURE = "denegado.jsp";
    	String strUrl = "inicio.jsp";
    	String username = request.getParameter("nombre");
    	String password = request.getParameter("contrasena");
    	
    	Hashtable<String, String> env = new Hashtable<>();
    	
    	//Variable control autenticacion
    	boolean autentificado = false;
    	
    	//Parametros para la conexion LDAP
    	env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
    	env.put(Context.PROVIDER_URL, "ldap://localhost:10389");
    	env.put(Context.SECURITY_AUTHENTICATION, "simple");
    	env.put(Context.SECURITY_PRINCIPAL, "uid=" + username +", ou=users, ou=system");
    	env.put(Context.SECURITY_CREDENTIALS, password);
    	try {
    		//Contexto inicial
    		DirContext ctx = new InitialDirContext(env);
    		
    		//Establecemos variable y cerramos el contexto
    		autentificado = true;
    		ctx.close();
    	} catch (NamingException e) {
    		autentificado = false;
    	}finally {
    		if (autentificado) {
    			System.out.print("Success");
    			strUrl = SUCCESS;
    		}else {
    			System.out.print("Failure");
    			strUrl = FAILURE;
    		}
    	}
    	//eNVIAMOS A jsp CORRESPONDIENTE
    	RequestDispatcher rd = request.getRequestDispatcher(strUrl);
    	rd.forward(request, response);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		procesoAutentificacionLDAP(request, response);
	}

}

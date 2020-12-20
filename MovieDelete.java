package movie;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class MovieDelete
 */
@WebServlet("/MovieDelete")
public class MovieDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovieDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out= response.getWriter();
		
		int deleteCount=0;
		String dburl="jdbc:mysql://172.17.0.2:3306/movies";
		String dbUser="root";
		String dppasswd="root";
		int movie_id=Integer.parseInt(request.getParameter("movie_id"));
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String sql="DELETE FROM movie WHERE movie_id=?";
		
		try(Connection conn = DriverManager.getConnection(dburl, dbUser, dppasswd);
				PreparedStatement ps=conn.prepareStatement(sql)){
			
			ps.setInt(1, movie_id);
			deleteCount=ps.executeUpdate();
		
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		
		 PrintWriter script=response.getWriter();
           
			if(deleteCount==0) {
                script.println("<script>");
				script.println("alert('Fail to Delete')");
                script.println("</script>");
				script.close();
			}
			else{
                     script.println("<script>");
					script.println("alert('Delete Success!')");
					script.println("</script>");
					script.close();
            }
		
		out.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

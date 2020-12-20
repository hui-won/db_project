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
 * Servlet implementation class MoviePersonnelInsert
 */
@WebServlet("/MoviePersonnelInsert")
public class MoviePersonnelInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MoviePersonnelInsert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out= response.getWriter();
		
		String dburl="jdbc:mysql://172.17.0.2:3306/movies";
		String dbUser="root";
		String dppasswd="root";
		
		int ssn=Integer.parseInt(request.getParameter("ssn"));
		String name=request.getParameter("name");
		String bdate=request.getParameter("bdate");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		String sql="INSERT INTO movie_personnel (ssn, name, bdate) VALUES (?,?,?)";
		
		try(Connection conn = DriverManager.getConnection(dburl, dbUser, dppasswd);
				PreparedStatement ps=conn.prepareStatement(sql)){
			
			ps.setInt(1,ssn);
			ps.setString(2, name);
			if(bdate.matches("")) {	
				ps.setString(3,"0000-00-00");
			}else {
				ps.setString(3,bdate);
			}
			
			int count=ps.executeUpdate();
			PrintWriter script=response.getWriter();
			if(count==0) {
				System.out.println("fail to insert");
			}
			else {

				script.println("<script>");
				script.println("alert('Insert Success!')");
				script.println("</script>");
			}
			
			} catch(Exception ex) {
				ex.printStackTrace();
					PrintWriter script=response.getWriter();
					script.println("<script>");
					script.println("history.back()");
					script.println("alert('Fail to insert')");
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

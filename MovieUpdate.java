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
 * Servlet implementation class MovieUpdate
 */
@WebServlet("/MovieUpdate")
public class MovieUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovieUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String dburl="jdbc:mysql://172.17.0.2:3306/movies";
		 String dbUser="root";
		 String dppasswd="root";
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out= response.getWriter();
		
		int updateCount=0;
		
		int movie_id=Integer.parseInt(request.getParameter("movie_id"));
		String title=request.getParameter("title");
		String odate=request.getParameter("odate");
		int dssn=Integer.parseInt(request.getParameter("dssn"));
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		String sql="update movie set title =?, dssn=?,opening_date=? where movie_id=?";
		
		try(Connection conn = DriverManager.getConnection(dburl, dbUser, dppasswd);
				PreparedStatement ps=conn.prepareStatement(sql)){
			
			ps.setString(1, title);
			ps.setInt(2, dssn);
			ps.setString(3, odate);
			ps.setInt(4,movie_id);
			
			updateCount=ps.executeUpdate();
			
            PrintWriter script=response.getWriter();
           
			if(updateCount==0) {
                script.println("<script>");
				script.println("alert('Fail to update')");
                script.println("</script>");
				script.close();
			}
			else{
                     script.println("<script>");
					script.println("alert('Update Success!')");
					script.println("</script>");
					script.close();
            }
			
		} catch(Exception ex) {
			ex.printStackTrace();
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

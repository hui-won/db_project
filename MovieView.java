package movie;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MovieView
 */
@WebServlet("/MovieView")
public class MovieView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovieView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		Connection conn =null;
		Statement stmt=null;
		ResultSet rs=null;
		
		String sql;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url="jdbc:mysql://172.17.0.2:3306/movies";
			
			conn=DriverManager.getConnection(url,"root","root");
			
			stmt=conn.createStatement();

			sql="SELECT * FROM movie_info";
			rs=stmt.executeQuery(sql);
				
			out.println("<h2>Information of movie participation</h2>");
			while(rs.next()) {
				String title=rs.getString(1);
				int hnum=rs.getInt(2);
				int anum=rs.getInt(3);
				int pnum=rs.getInt(4);
	
				out.println("<h3>Movie title:"+title+"&nbsp;number of lead-role:"+hnum+"&nbsp;number of actor:"+anum+"&nbsp;number of producer:"+pnum+"</h3>" );
				}
	
		}
		catch(ClassNotFoundException e) {
			System.out.println("fail to load");
		}
		catch(SQLException e) {
			System.out.println("error"+e);
		}
		finally {
			try {
				if(conn!=null&&!conn.isClosed()) {
					conn.close();
				}
				if(stmt!=null&&!stmt.isClosed()) {
					stmt.close();
				}
				if(rs!=null&&!rs.isClosed()) {
					rs.close();
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
					
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

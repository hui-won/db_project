package movie;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProducerInsert
 */
@WebServlet("/ActorInsert")
public class ActorInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActorInsert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		
		int movie_id=Integer.parseInt(request.getParameter("movie_id"));
		int assn=Integer.parseInt(request.getParameter("assn"));
		
		Connection conn =null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url="jdbc:mysql://172.17.0.2:3306/movies";
			
			conn=DriverManager.getConnection(url,"root","root");
		
		
			String sql="INSERT INTO performs_in (movie_id,assn) VALUES (?,?)";
			pstmt=conn.prepareStatement(sql);
		
			pstmt.setInt(1,movie_id);
			pstmt.setInt(2,assn);
	
			int count=pstmt.executeUpdate();
			if(count==0) {
				System.out.println("fail to insert");
			}
			else {
				PrintWriter script=response.getWriter();
				script.println("<script>");
				script.println("alert('Insert Success about actor!')");
				script.println("</script>");
			}
		}
		catch(ClassNotFoundException e) {
			System.out.println("fail to load");
		}
		catch(SQLException e) {
			System.out.println("error"+e);
			PrintWriter script=response.getWriter();
			script.println("<script>");
			script.println("history.back()");
			script.println("alert('Fail to insert about actor')");
			script.println("</script>");
			script.close();
		}
		finally {
			try {
				if(conn!=null&&!conn.isClosed()) {
					conn.close();
				}
				if(pstmt!=null&&!pstmt.isClosed()) {
					pstmt.close();
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

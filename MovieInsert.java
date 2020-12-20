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
 * Servlet implementation class MovieInsert
 */
@WebServlet("/MovieInsert")
public class MovieInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovieInsert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		
		int movie_id=Integer.parseInt(request.getParameter("movie_id"));
		String title=request.getParameter("title");
		String odate=request.getParameter("odate");
		int dssn=Integer.parseInt(request.getParameter("dssn"));
		int pssn=Integer.parseInt(request.getParameter("pssn"));
		int lssn1=Integer.parseInt(request.getParameter("lssn1"));
		String lssn=request.getParameter("lssn2");
	
		Connection conn =null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url="jdbc:mysql://172.17.0.2:3306/movies";
			
			conn=DriverManager.getConnection(url,"root","root");
	
			String sql1="INSERT INTO movie (movie_id,title,dssn,opening_date) VALUES (?,?,?,?)";
			pstmt=conn.prepareStatement(sql1);
		
			pstmt.setInt(1,movie_id);
			pstmt.setString(2, title);
			pstmt.setInt(3,dssn);
			if(odate.matches("")) {	
				pstmt.setString(4,"0000-00-00");
			}else {
				pstmt.setString(4,odate);
			}
			
			int count=pstmt.executeUpdate();
			if(count==0) {
				System.out.println("fail to insert");
			}
			else {
				PrintWriter script=response.getWriter();
				script.println("<script>");
				script.println("alert('Insert Success about movie!')");
				script.println("</script>");
			}
		}
		catch(ClassNotFoundException e) {
			System.out.println("fail to loading");
		}
		catch(SQLException e) {
			System.out.println("error"+e);
			PrintWriter script=response.getWriter();
			script.println("<script>");
			script.println("history.back()");
			script.println("alert('Fail to insert about movie')");
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
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		
			String url="jdbc:mysql://172.17.0.2:3306/movies";
			
			conn=DriverManager.getConnection(url,"root","root");
			
			String sql="INSERT INTO produces (movie_id,pssn) VALUES (?,?)";
			pstmt=conn.prepareStatement(sql);
		
			pstmt.setInt(1,movie_id);
			pstmt.setInt(2,pssn);
	
            int count=pstmt.executeUpdate();
			if(count==0) {
				System.out.println("fail to insert");
			}
			else {
				PrintWriter script=response.getWriter();
				script.println("<script>");
				script.println("alert('Insert Success about producer!')");
				script.println("</script>");
			}
		}
		catch(ClassNotFoundException e) {
			System.out.println("fail to loading");
		}
		catch(SQLException e) {
			System.out.println("error"+e);
			PrintWriter script=response.getWriter();
			script.println("<script>");
			script.println("history.back()");
			script.println("alert('Fail to insert about producer')");
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
		try {
		    Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url="jdbc:mysql://172.17.0.2:3306/movies";
			
			conn=DriverManager.getConnection(url,"root","root");
		
			String sql="INSERT INTO lead_role (movie_id,lssn) VALUES (?,?)";
			pstmt=conn.prepareStatement(sql);
		
			pstmt.setInt(1,movie_id);
			pstmt.setInt(2,lssn1);
	
			int count=pstmt.executeUpdate();
			if(count==0) {
				System.out.println("fail to insert");
			}
			else {
				
				PrintWriter script=response.getWriter();
				script.println("<script>");
				script.println("alert('Insert Success about lead role!')");
				script.println("</script>");
			}
		}
		catch(ClassNotFoundException e) {
			System.out.println("fail to loading");
		}
		catch(SQLException e) {
			System.out.println("error"+e);
			PrintWriter script=response.getWriter();
			script.println("<script>");
			script.println("history.back()");
			script.println("alert('Fail to insert about lead role')");
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
		if(lssn.matches("")==false) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url="jdbc:mysql://172.17.0.2:3306/movies";
			
			conn=DriverManager.getConnection(url,"root","root");
			
			String sql="INSERT INTO lead_role (movie_id,lssn) VALUES (?,?)";
			pstmt=conn.prepareStatement(sql);
		
			int lssn2=Integer.parseInt(request.getParameter("lssn2"));
			
			pstmt.setInt(1,movie_id);
			pstmt.setInt(2,lssn2);
	
			int count=pstmt.executeUpdate();
			if(count==0) {
				System.out.println("fail to insert");
			}
			else {
				PrintWriter script=response.getWriter();
				script.println("<script>");
				script.println("alert('Insert Success about lead role2!')");
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
			script.println("alert('Fail to insert about lead role')");
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

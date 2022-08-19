import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletSample
 */
@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//URLのパラメータからidを取得してsetAttribute
		int postId = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("message", "Edit your post " + postId);
		
		//データベース接続
		String url = "jdbc:mysql://localhost:8889/memo";
        String user = "root";
        String password = "root";
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //データベースのデータ取得
        String sql = "SELECT * FROM posts WHERE id = ?";
        
        try (Connection connection = DriverManager.getConnection(url, user, password);
                PreparedStatement statment = connection.prepareStatement(sql);
                ) {
        		
        		statment.setInt(1, postId);
        		ResultSet results = statment.executeQuery();

               while (results.next()) {   

                   String id = results.getString("id");
                   request.setAttribute("id", id);

                   String title = results.getString("title");
                   request.setAttribute("title", title);

                   String content = results.getString("content");
                   request.setAttribute("content",content);
     
               }

           } catch (Exception e) {
               request.setAttribute("message", "Exception:" + e.getMessage());
           }
        
		//JSPと関連付け
		String view = "/WEB-INF/views/edit.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
        dispatcher.forward(request, response);
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

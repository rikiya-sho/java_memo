import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletSample
 */
@WebServlet("/DestroyServlet")
public class DestroyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DestroyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//URLのパラメータからidを取得してsetAttribute
		int postId = Integer.parseInt(request.getParameter("id"));
		
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
        String sql = "DELETE FROM posts WHERE id = ?";
        
        try (Connection connection = DriverManager.getConnection(url, user, password);
                PreparedStatement statment = connection.prepareStatement(sql);
                ) {
        		
        		statment.setInt(1, postId);
        		int number = statment.executeUpdate();
        		System.out.println(number);

           } catch (Exception e) {
               request.setAttribute("message", "Exception:" + e.getMessage());
           }
        
		//JSPと関連付け
		String forword = "list";
        RequestDispatcher dispatcher = request.getRequestDispatcher(forword);
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

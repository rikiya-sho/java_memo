import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletSample
 */
@WebServlet("/ListServlet")
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("message", "メモ一覧");
		
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
        String sql = "SELECT * FROM posts";
        
        try (Connection connection = DriverManager.getConnection(url, user, password);
                PreparedStatement statment = connection.prepareStatement(sql);
                ResultSet results = statment.executeQuery()) {

               ArrayList<HashMap<String, String>> rows = new ArrayList<HashMap<String, String>>();

               while (results.next()) {
                   HashMap<String, String> columns = new HashMap<String, String>();

                   String id = results.getString("id");
                   columns.put("id", id);

                   String title = results.getString("title");
                   columns.put("title", title);

                   String content = results.getString("content");
                   columns.put("content", content);

                   rows.add(columns);
               }

               request.setAttribute("rows", rows);

           } catch (Exception e) {
               request.setAttribute("message", "Exception:" + e.getMessage());
           }
        
		
        //JSPと関連付け
		String view = "/WEB-INF/views/list.jsp";
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

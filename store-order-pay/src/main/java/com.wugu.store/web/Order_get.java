package homework_servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import homework_entity.Order;
import zbl_homework_Service.OrderService;


@WebServlet("/Order_get")
public class Order_get extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Order_get() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		OrderService oService = new OrderService();
		List<Order> orders = new ArrayList<>();
		orders = oService.queryAllOrders();
		
		float sum = oService.sum();
		out.print(sum);
		//response.getWriter().write( );
		//数据跳转到订单网页
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package homework_servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import homework_entity.Order;
import zbl_homework_Service.OrderService;


@WebServlet("/Order_finished")
public class Order_finished extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public Order_finished() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		
		OrderService oService = new OrderService();
		boolean flag = oService.updateOrdersStatus();
		if(flag){
			//支付成功，数据修改成功，弹出弹窗或者跳转到支付成功页面
		}else{
			//支付失败，修改数据失败，弹出弹窗或者跳转到支付失败页面提醒
		}
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

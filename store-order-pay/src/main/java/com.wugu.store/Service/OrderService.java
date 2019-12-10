package zbl_homework_Service;

import java.util.List;

import homework_Dao.OrderDao;
import homework_entity.Order;

public class OrderService {
	OrderDao orderDao = new OrderDao();
	public List<Order> queryAllOrders(){//查询数据库订单为未支付的订单
		return orderDao.queryAllOrders();
	}
	public boolean updateOrdersStatus(){//订单完成后修改数据库的订单状态
		 return orderDao.updateOrdersStatus();
		
	}
	public float sum(){
		
		return orderDao.sum();
	}

	

}

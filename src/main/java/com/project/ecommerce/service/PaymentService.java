package com.project.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.ecommerce.Dao.BuyDao;
import com.project.ecommerce.Dao.OrderDao;
import com.project.ecommerce.Dao.PaymentDao;
import com.project.ecommerce.entity.Address;
import com.project.ecommerce.entity.Buy;
import com.project.ecommerce.entity.Order;
import com.project.ecommerce.entity.Payment;
import com.project.ecommerce.exception.IdNotFoundException;
import com.project.ecommerce.util.ResponseStructure;

@Service
public class PaymentService {

	@Autowired
	private PaymentDao paymentDao;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private BuyDao buyDao;

	public ResponseEntity<ResponseStructure<Payment>> savePayment(long buyId, long orderId, Payment payment) {
		// TODO Auto-generated method stub
		if(buyId==0 && orderId==0) {
			throw new IdNotFoundException("Either buyId or orderId should be Given");
		}
		if(buyId!=0) {
			Buy buyDb = buyDao.findBuy(buyId);
			if(buyDb==null) {
				throw new IdNotFoundException("buyId is not present");
			}
			payment.setBuy(buyDb);
		}
		
		if(orderId!=0) {
			Order orderDb = orderDao.findOrder(orderId);
			if(orderDb==null) {
				throw new IdNotFoundException("orderId is not present");
			}
			payment.setOrder(orderDb);
		}

		Payment paymentDb = paymentDao.savePayment(payment);
		
		ResponseStructure<Payment> responseStructure=new ResponseStructure<Payment>();
        responseStructure.setStatus(HttpStatus.CREATED.value());
        responseStructure.setMessage("Payment is saved sucessfully");
        responseStructure.setData(paymentDb);
        
        return new ResponseEntity<ResponseStructure<Payment>>(responseStructure,HttpStatus.CREATED); 

	}

	public ResponseEntity<ResponseStructure<Payment>> findPayment(long id) {
		// TODO Auto-generated method stub
		Payment paymentDb = paymentDao.findPayment(id);
		if(paymentDb==null){
			throw new IdNotFoundException("Id is not present in the database");
		}
		ResponseStructure<Payment> responseStructure=new ResponseStructure<Payment>();
        responseStructure.setStatus(HttpStatus.FOUND.value());
        responseStructure.setMessage("Payment is fetched sucessfully");
        responseStructure.setData(paymentDb);
        
        return new ResponseEntity<ResponseStructure<Payment>>(responseStructure,HttpStatus.FOUND); 

	}

	public ResponseEntity<ResponseStructure<Payment>> updatePayment(long id, Payment payment) {
		// TODO Auto-generated method stub
		Payment paymentDb = paymentDao.findPayment(id);
		if(paymentDb.equals(null)){
			throw new IdNotFoundException("Id is not present in the database");
		}
		payment.setPaymentId(paymentDb.getPaymentId());
		payment.setBuy(paymentDb.getBuy());
		payment.setOrder(paymentDb.getOrder());
		Payment paymentDb1 = paymentDao.savePayment(payment);
		
		ResponseStructure<Payment> responseStructure=new ResponseStructure<Payment>();
        responseStructure.setStatus(HttpStatus.FOUND.value());
        responseStructure.setMessage("Payment is updated sucessfully");
        responseStructure.setData(paymentDb1);
        
        return new ResponseEntity<ResponseStructure<Payment>>(responseStructure,HttpStatus.FOUND); 

		
	}

	public ResponseEntity<ResponseStructure<Payment>> deletePayment(long id) {
		// TODO Auto-generated method stub
		Payment paymentDb = paymentDao.findPayment(id);
		if(paymentDb==null){
			throw new IdNotFoundException("Id is not present in the database");
		}
		Order order = paymentDb.getOrder();
		Buy buy = paymentDb.getBuy();

		paymentDao.deletePayment(id);
		
		if(order!=null) {
			orderDao.deleteOrder(order.getOrderId());
		}
		if(buy!=null) {
			buyDao.deleteBuy(buy.getBuyId());
		}
		
		
		ResponseStructure<Payment> responseStructure=new ResponseStructure<Payment>();
        responseStructure.setStatus(HttpStatus.OK.value());
        responseStructure.setMessage("Payment is deleted sucessfully");
        responseStructure.setData(paymentDb);
        
        return new ResponseEntity<ResponseStructure<Payment>>(responseStructure,HttpStatus.OK); 

	}
	
	
	
	
	
}

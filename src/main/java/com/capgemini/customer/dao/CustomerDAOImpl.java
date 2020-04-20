package com.capgemini.customer.dao;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.capgemini.customer.dto.*;

import lombok.extern.java.Log;

@Log
@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@PersistenceUnit
	private EntityManagerFactory fact;
	private UserInfoBean user = new UserInfoBean();
	DealerProductInfoBean dprod = new DealerProductInfoBean();

	boolean flag = false;

	@Override
	public OrderDetails buyProduct(UserInfoBean customer) {
		EntityManager mgr = fact.createEntityManager();
		EntityTransaction tx = mgr.getTransaction();
		try {
			tx.begin();
			UserInfoBean bean = mgr.find(UserInfoBean.class, customer.getUserId());
			Iterator<DealerProductInfoBean> itr = customer.getDealersProds().iterator();
			LocalDate date = LocalDate.now();
			if (itr.hasNext()) {
				DealerProductInfoBean product = itr.next();
				DealerProductInfoBean prod = mgr.find(DealerProductInfoBean.class, product.getDealersProductId());
				OrderDetails cameOrder=null;
				Iterator<OrderDetails> oritr = customer.getOrders().iterator();
				if(oritr.hasNext()) {
					cameOrder= oritr.next();
				}
				OrderDetails order = new OrderDetails();
				order.setDateOfOrder(date);
				order.setUser(bean);
				order.setProductId(prod.getProductId());
				order.setPaymentType(cameOrder.getPaymentType());
				order.setProductName(prod.getProductName());
				order.setStatus("Not yet Delivered");
				order.setAmount(prod.getSellingPrice() * product.getQuantity());
				order.setRole(bean.getRole());
				order.setQuantity(product.getQuantity());
				order.setDateOfDelivery(date.plusDays(3));
				bean.getOrders().add(order);
				int quantity = prod.getQuantity() - product.getQuantity();

				prod.setQuantity(quantity);
				if (prod.getQuantity() <= prod.getMinimumQuantity()) {
					//user.setUserId(prod.getDealerId());
					dprod.setDealersProductId(prod.getDealersProductId());
					//dprod.setProductId(prod.getProductId());
					dprod.setQuantity(prod.getQuantity() * 2);
					user.getDealersProds().add(dprod);
					flag = true;
				}

				// mgr.persist(order);
				mgr.persist(bean);
				mgr.flush();
				tx.commit();
				
				String jpql = "select o from OrderDetails o order by o.orderId desc";
				Query query = mgr.createQuery(jpql, OrderDetails.class);
				OrderDetails placedOrder = (OrderDetails) query.setMaxResults(1).getSingleResult();
				mgr.close();
				return placedOrder;
			} else {
				return null;
			}

		} catch (Exception e) {
			for (StackTraceElement ele : e.getStackTrace()) {
				log.info(ele.toString());
				return null;
			}
		}
		return null;
	}

	@Override
	public OrderDetails getOrderDetails(int id) {
		EntityManager mgr = fact.createEntityManager();
		OrderDetails order = mgr.find(OrderDetails.class, id);
		if (order != null) {
			return order;
		} else {
			return null;
		}
	}

	@Override
	public boolean checkEmailAvailability(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void autoBuy() {
		if (flag) {
			try {
				EntityManager mgr = fact.createEntityManager();
				EntityTransaction tx = mgr.getTransaction();
				tx.begin();
				OrderDetails order = new OrderDetails();
				//order.setUserId(user.getUserId());
				order.setProductId(dprod.getProduct().getProductId());
				order.setDateOfOrder(LocalDate.now());
				order.setDateOfDelivery(LocalDate.now().plusDays(2));
				order.setQuantity(dprod.getQuantity());

				ProductInfoBean prod = mgr.find(ProductInfoBean.class, dprod.getProduct().getProductId());
				UserInfoBean bean = mgr.find(UserInfoBean.class, user.getUserId());
				order.setAmount(dprod.getQuantity() * prod.getProductCost());
				order.setRole(bean.getRole());
				order.setProductName(prod.getProductName());
				bean.getOrders().add(order);
				mgr.persist(bean);
				tx.commit();
				flag = false;
			} catch (Exception e) {
				for (StackTraceElement ele : e.getStackTrace()) {
					log.info(ele.toString());
				}
			}

		}
	}


	@Override
	public List<DealerProductInfoBean> getProds() {
		EntityManager mgr = fact.createEntityManager();
		String jpql = "select p from DealerProductInfoBean p";
		TypedQuery<DealerProductInfoBean> query = mgr.createQuery(jpql, DealerProductInfoBean.class);
		List<DealerProductInfoBean> list = query.getResultList();
		return list;
	}

}

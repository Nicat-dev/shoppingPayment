package com.example.shoppingpay.service.serviceImpl;

import com.example.shoppingpay.enums.EnumAvaibleStatus;
import com.example.shoppingpay.exception.ExceptionConstans;
import com.example.shoppingpay.model.Customer;
import com.example.shoppingpay.model.Orders;
import com.example.shoppingpay.model.Product;
import com.example.shoppingpay.repository.CustomerDao;
import com.example.shoppingpay.repository.OrderDao;
import com.example.shoppingpay.repository.ProductDao;
import com.example.shoppingpay.request.ReqOrder;
import com.example.shoppingpay.response.*;
import com.example.shoppingpay.service.CustomerService;
import com.example.shoppingpay.service.OrderService;
import com.example.shoppingpay.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private CustomerDao customerDao;


    @Override
    public RespOrederList getOrderList() {
        RespOrederList response = new RespOrederList();
        try {
            List<RespOrder> respOrdersList = new ArrayList<>();
            List<Orders> ordersList = orderDao.findAllByActive(EnumAvaibleStatus.ACTIVE.getValue());
            if (ordersList.isEmpty()) {
                response.setStatus(new RespStatus(ExceptionConstans.ORDER_NOT_FOUND, "Customer not found Exception"));
                return response;
            }
            for (Orders order : ordersList) {
                RespOrder respOrder = getOrderById(order.getOrderId());
                respOrdersList.add(respOrder);
            }
            response.setRespOrderList(respOrdersList);
            response.setStatus(RespStatus.getSuccesMessage());
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstans.INTERNAL_EXCEPTION, "Internal Exception"));
        }
        return null;
    }

    @Override
    public RespOrder getOrderById(Long orderId) {
        RespOrder response = new RespOrder();
        try {
            if (orderId == null) {
                response.setStatus(new RespStatus(ExceptionConstans.INVALID_REQUEST_EXCEPTION, "Invalid request Exception"));
                return response;
            }
            Orders order = orderDao.findOrdersByOrderIdAndActive(orderId, EnumAvaibleStatus.ACTIVE.getValue());
            if (order == null) {
                response.setStatus(new RespStatus(ExceptionConstans.ORDER_NOT_FOUND, "Orders not found Exception"));
                return response;
            }
            response = new RespOrder(order);
            RespCustomer customer = customerService.getCustomerById(order.getCustomer().getCustomerId());
            response.setRespCustomer(customer);
            RespProduct product = productService.getProductById(order.getProduct().getProductId());
            response.setRespProduct(product);
            response.setStatus(RespStatus.getSuccesMessage());
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstans.INTERNAL_EXCEPTION, "Internal Exception"));
            return response;
        }
        return response;
    }

    @Override
    public RespStatus addOrder(ReqOrder reqOrder) {
        RespStatus status = null;
        try {
            String name = reqOrder.getOrderName();
            Integer quantity = reqOrder.getOrderQuantity();
            Long productId = reqOrder.getProductId();
            Long customerId = reqOrder.getCustomerId();
            if (name == null || quantity == null) {
                return new RespStatus(ExceptionConstans.INVALID_REQUEST_EXCEPTION, "Ivalid request data");
            }
            Product product = productDao.findProductByProductIdAndActive(productId, EnumAvaibleStatus.ACTIVE.getValue());
            if (product == null) {
                return new RespStatus(ExceptionConstans.PRODUCT_NOT_FOUND, "Product not found");
            }
            Customer customer = customerDao.findCustomerByCustomerIdAndActive(customerId, EnumAvaibleStatus.ACTIVE.getValue());
            if (customer == null) {
                return new RespStatus(ExceptionConstans.CUSTOMER_NOT_FOUND, "Customer not found");
            }
            Orders order = new Orders();
            order.setOrderName(name);
            order.setOrderQuantity(quantity);
            order.setProduct(product);
            order.setCustomer(customer);
            orderDao.save(order);
            status = RespStatus.getSuccesMessage();
        } catch (Exception e) {
            e.printStackTrace();
            status = new RespStatus(ExceptionConstans.INTERNAL_EXCEPTION, "Internal exception");
        }
        return status;
    }

    @Override
    public RespStatus updateOrder(ReqOrder reqOrder) {
        RespStatus status = null;
        try {
            Long orderId = reqOrder.getOrderId();
            String orderName = reqOrder.getOrderName();
            Integer orderQuantity = reqOrder.getOrderQuantity();
            Long productId = reqOrder.getProductId();
            Long customerId = reqOrder.getCustomerId();
            if (orderId == null || productId == null) {
                return new RespStatus(ExceptionConstans.INVALID_REQUEST_EXCEPTION, "Ivalid request data");
            }
            Orders order = orderDao.findOrdersByOrderIdAndActive(orderId, EnumAvaibleStatus.ACTIVE.getValue());
            if (order == null) {
                return new RespStatus(ExceptionConstans.ORDER_NOT_FOUND, "Order not found");
            }
            Product product = productDao.findProductByProductIdAndActive(productId, EnumAvaibleStatus.ACTIVE.getValue());
            if (product ==null){
                return new RespStatus(ExceptionConstans.PRODUCT_NOT_FOUND, "Order not found");
            }
            Customer customer = customerDao.findCustomerByCustomerIdAndActive(customerId,EnumAvaibleStatus.ACTIVE.getValue());
            if (customer ==null){
                return new RespStatus(ExceptionConstans.CUSTOMER_NOT_FOUND, "Order not found");
            }
            order.setOrderName(orderName);
            order.setOrderQuantity(orderQuantity);
            order.setCustomer(customer);
            order.setProduct(product);
            orderDao.save(order);
            status = RespStatus.getSuccesMessage();
        }catch (Exception e){
            e.printStackTrace();
            status = new RespStatus(ExceptionConstans.INTERNAL_EXCEPTION, "Internal exception");
        }
        return status;
    }

    @Override
    public RespStatus deleteOrder(Long orderId) {
        RespStatus status = null;
        try{
            if (orderId==null){
                return new RespStatus(ExceptionConstans.INVALID_REQUEST_EXCEPTION, "Ivalid request data");
            }
            Orders order = orderDao.findOrdersByOrderIdAndActive(orderId,EnumAvaibleStatus.ACTIVE.getValue());
            if (order==null){
                return new RespStatus(ExceptionConstans.ORDER_NOT_FOUND, "Order not found");
            }
            order.setActive(EnumAvaibleStatus.DEACTIVE.getValue());
            orderDao.save(order);
            status = RespStatus.getSuccesMessage();
        }catch (Exception e){
            e.printStackTrace();
            status = new RespStatus(ExceptionConstans.INTERNAL_EXCEPTION, "Internal exception");
        }

        return status;
    }
}

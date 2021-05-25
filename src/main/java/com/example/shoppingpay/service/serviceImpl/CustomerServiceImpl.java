package com.example.shoppingpay.service.serviceImpl;

import com.example.shoppingpay.enums.EnumAvaibleStatus;
import com.example.shoppingpay.exception.ExceptionConstans;
import com.example.shoppingpay.model.Customer;
import com.example.shoppingpay.repository.CustomerDao;
import com.example.shoppingpay.request.ReqCustomer;
import com.example.shoppingpay.response.RespCustomer;
import com.example.shoppingpay.response.RespCustomerList;
import com.example.shoppingpay.response.RespStatus;
import com.example.shoppingpay.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Override
    public RespCustomerList getCustomerList() {
        RespCustomerList response = new RespCustomerList();
        try{
            List<RespCustomer> respCustomerList = new ArrayList<>();
            List<Customer> customerList = customerDao.findAllByActive(EnumAvaibleStatus.ACTIVE.getValue());
            if (customerList.isEmpty()){
                response.setStatus(new RespStatus(ExceptionConstans.CUSTOMER_NOT_FOUND, "Customer not found Exception"));
                return response;
            }
            for (Customer customer: customerList){
                RespCustomer respCustomer = getCustomerById(customer.getCustomerId());
                respCustomerList.add(respCustomer);
            }
            response.setRespCustomerList(respCustomerList);
            response.setStatus(RespStatus.getSuccesMessage());
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstans.INTERNAL_EXCEPTION, "Internal Exception"));
        }
        return response;
    }

    @Override
    public RespCustomer getCustomerById(Long id) {
        RespCustomer response = new RespCustomer();
        try{
            if (id==null){
                response.setStatus(new RespStatus(ExceptionConstans.INVALID_REQUEST_EXCEPTION, "Invalid request Exception"));
                return response;
            }
            Customer customer = customerDao.findCustomerByCustomerIdAndActive(id,EnumAvaibleStatus.ACTIVE.getValue());
            if (customer==null){
                response.setStatus(new RespStatus(ExceptionConstans.CUSTOMER_NOT_FOUND, "Customer not found Exception"));
                return response;
            }
            response.setCustomerId(customer.getCustomerId());
            response.setCustomerName(customer.getCustomerSurname());
            response.setCustomerSurname(customer.getCustomerSurname());
            response.setActive(customer.getActive());
            response.setDataDate(customer.getDataDate());
            response.setStatus(RespStatus.getSuccesMessage());
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstans.INTERNAL_EXCEPTION, "Internal Exception"));
            return response;
        }

        return response;
    }

    @Override
    public RespStatus addCustomer(ReqCustomer reqCustomer) {
        RespStatus status = null;
        try{
            Long id = reqCustomer.getCustomerId();
            String name = reqCustomer.getCustomerName();
            String surname = reqCustomer.getCustomerSurname();
            Integer active = reqCustomer.getActive();
            if (name == null || surname == null) {
                return new RespStatus(ExceptionConstans.INVALID_REQUEST_EXCEPTION, "Ivalid request data");
            }
            Customer customer = new Customer();
            customer.setCustomerId(id);
            customer.setCustomerName(name);
            customer.setCustomerSurname(surname);
            customer.setActive(active);
            customerDao.save(customer);
            status = RespStatus.getSuccesMessage();
        }catch (Exception e){
            e.printStackTrace();
            status = new RespStatus(ExceptionConstans.INTERNAL_EXCEPTION, "Internal Exception");
        }
        return status;
    }

    @Override
    public RespStatus updateCustomer(ReqCustomer reqCustomer) {
        RespStatus status = null;
        try {
            String name = reqCustomer.getCustomerName();
            String surname = reqCustomer.getCustomerSurname();
            Integer active = reqCustomer.getActive();
            Long customerId = reqCustomer.getCustomerId();
            if (name == null || surname == null) {
                return new RespStatus(ExceptionConstans.INVALID_REQUEST_EXCEPTION, "Ivalid request data");
            }
            Customer customer = customerDao.findCustomerByCustomerIdAndActive(customerId,EnumAvaibleStatus.ACTIVE.getValue());
            if(customer== null){
                return new RespStatus(ExceptionConstans.CUSTOMER_NOT_FOUND, "Customer not found data");
            }
            customer.setCustomerName(name);
            customer.setCustomerSurname(surname);
            customer.setActive(active);
            customerDao.save(customer);
            status = RespStatus.getSuccesMessage();
        }catch (Exception e){
            e.printStackTrace();
            status = new RespStatus(ExceptionConstans.INTERNAL_EXCEPTION, "Internal Exception");
        }
        return status;
    }

    @Override
    public RespStatus deleteCustomer(Long customerId) {
        RespStatus status = null;
        try{
            if (customerId==null){
                return new RespStatus(ExceptionConstans.INVALID_REQUEST_EXCEPTION, "Ivalid request data");
            }
            Customer customer = customerDao.findCustomerByCustomerIdAndActive(customerId,EnumAvaibleStatus.ACTIVE.getValue());
            if (customer==null){
                return new RespStatus(ExceptionConstans.CUSTOMER_NOT_FOUND, "Customer not found data");
            }
            customer.setActive(EnumAvaibleStatus.DEACTIVE.getValue());
            customerDao.save(customer);
            status = RespStatus.getSuccesMessage();
        }catch (Exception e){
            e.printStackTrace();
            status = new RespStatus(ExceptionConstans.INTERNAL_EXCEPTION, "Internal Exception");
        }
        return status;
    }
}

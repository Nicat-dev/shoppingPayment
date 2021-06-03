package com.example.shoppingpay.service.serviceImpl;

import com.example.shoppingpay.enums.EnumAvaibleStatus;
import com.example.shoppingpay.exception.ExceptionConstans;
import com.example.shoppingpay.model.Product;
import com.example.shoppingpay.repository.ProductDao;
import com.example.shoppingpay.request.ReqProduct;
import com.example.shoppingpay.response.RespProduct;
import com.example.shoppingpay.response.RespProductList;
import com.example.shoppingpay.response.RespStatus;
import com.example.shoppingpay.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public RespProductList getProductList() {
        RespProductList response = new RespProductList();
        try{
            List<RespProduct> respProductList = new ArrayList<>();
            List<Product> productList = productDao.findAllByActive(EnumAvaibleStatus.ACTIVE.getValue());
            if (productList.isEmpty()){
                response.setStatus(new RespStatus(ExceptionConstans.PRODUCT_NOT_FOUND, "Product not found Exception"));
                return response;
            }for (Product product : productList){
                RespProduct respProduct = getProductById(product.getProductId());
                respProductList.add(respProduct);
            }
            response.setRespProductList(respProductList);
            response.setStatus(RespStatus.getSuccesMessage());
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstans.INTERNAL_EXCEPTION, "Internal Exception"));
        }
        return response;
    }

    @Override
    public RespProduct getProductById(Long productId) {
        RespProduct response = new RespProduct();
        try{
            if (productId==null){
                response.setStatus(new RespStatus(ExceptionConstans.INVALID_REQUEST_EXCEPTION, "Invalid request Exception"));
                return response;
            }
            Product product = productDao.findProductByProductIdAndActive(productId,EnumAvaibleStatus.ACTIVE.getValue());
            if (product==null){
                response.setStatus(new RespStatus(ExceptionConstans.INVALID_REQUEST_EXCEPTION, "Invalid request Exception"));
                return response;
            }
            response.setRespProductId(productId);
            response.setRespProductName(product.getProductName());
            response.setRespActive(product.getActive());
            response.setRespProductPrice(product.getProductPrice());
            response.setRespProductDate(product.getProductDate());
            response.setStatus(RespStatus.getSuccesMessage());
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstans.INTERNAL_EXCEPTION, "Internal Exception"));
            return response;
        }
        return response;
    }

    @Override
    public RespStatus updateProduct(ReqProduct reqProduct) {
        RespStatus status = null;
        try {
            Long id = reqProduct.getProductId();
            String name = reqProduct.getProductName();
            Double productPrice = reqProduct.getProductPrice();
            if (name == null || productPrice == null) {
                return new RespStatus(ExceptionConstans.INVALID_REQUEST_EXCEPTION, "Ivalid request data");
            }
            Product product = productDao.findProductByProductIdAndActive(id,EnumAvaibleStatus.ACTIVE.getValue());
            if (product==null){
                return new RespStatus(ExceptionConstans.PRODUCT_NOT_FOUND, "Product not found data");
            }
            product.setProductName(name);
            product.setProductPrice(productPrice);
            productDao.save(product);
            status = RespStatus.getSuccesMessage();
        }catch (Exception e){
            e.printStackTrace();
            status = new RespStatus(ExceptionConstans.INTERNAL_EXCEPTION, "Internal Exception");
        }
        return status;
    }

    @Override
    public RespStatus addProduct(ReqProduct reqProduct) {
        RespStatus status = null;
        try{
            Long id = reqProduct.getProductId();
            String name = reqProduct.getProductName();
            Double price = reqProduct.getProductPrice();
            Integer active = reqProduct.getActive();
            if (name == null || id == null) {
                return new RespStatus(ExceptionConstans.INVALID_REQUEST_EXCEPTION, "Ivalid request data");
            }
            Product product = new Product();
            product.setProductId(id);
            product.setProductName(name);
            product.setProductPrice(price);
            product.setActive(active);
            productDao.save(product);
            status = RespStatus.getSuccesMessage();
        }catch (Exception e){
            e.printStackTrace();
            status = new RespStatus(ExceptionConstans.INTERNAL_EXCEPTION, "Internal Exception");
        }
        return status;
    }

    @Override
    public RespStatus deleteProduct(Long productId) {
        RespStatus status = null;
        try{
            if (productId==null){
                return new RespStatus(ExceptionConstans.INVALID_REQUEST_EXCEPTION, "Ivalid request data");
            }
            Product product = productDao.findProductByProductIdAndActive(productId,EnumAvaibleStatus.ACTIVE.getValue());
            if (product==null){
                return new RespStatus(ExceptionConstans.PRODUCT_NOT_FOUND,"Product not found");
            }
            product.setActive(EnumAvaibleStatus.DEACTIVE.getValue());
            productDao.save(product);
            status = RespStatus.getSuccesMessage();
        }catch (Exception e){
            e.printStackTrace();
            status = new RespStatus(ExceptionConstans.INTERNAL_EXCEPTION, "Internal Exception");
        }
        return status;
    }
}

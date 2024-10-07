package org.scamlet.mvc.mvcstock.Service;

import jakarta.transaction.Transactional;
import org.scamlet.mvc.mvcstock.Entity.Product;
import org.scamlet.mvc.mvcstock.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public long productCount() {
        return productRepository.count();
    }

    public List<Product> productList() {
        return productRepository.findAll();
    }

    public Page<Product> findPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAll(pageable);
    }

    @Transactional
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> findById(long id) {
        return productRepository.findById(id);
    }

    @Transactional
    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }
}

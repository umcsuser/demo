package com.example.demo.service;

import com.example.demo.dao.ProductRepository;
import com.example.demo.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Product> findActive() {
        return productRepository.findByActiveTrue();
    }

    @Transactional(readOnly = true)
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Transactional
    public Product create(Product product) {
        product.setId(null);
        product.setActive(true);
        return productRepository.save(product);
    }

    @Transactional
    public Optional<Product> update(Long id, Product newData) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(newData.getName());
                    product.setPrice(newData.getPrice());
                    product.setActive(newData.isActive());
                    return product;
                });
    }

    @Transactional
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}

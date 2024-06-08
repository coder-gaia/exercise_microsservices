/**
 * @author alexandre.gaia
 */

package com.alexandreG.ProductService.repository;

import com.alexandreG.ProductService.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IProductRepository extends MongoRepository<Product, String> {

    Optional<Product> findByCodigo(String codigo);

    Page<Product> findAllByStatus(Pageable pageable, Product.Status status);
}

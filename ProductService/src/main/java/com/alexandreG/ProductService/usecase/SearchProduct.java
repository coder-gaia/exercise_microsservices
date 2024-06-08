/**
 * @author alexandre.gaia
 */

package com.alexandreG.ProductService.usecase;

import com.alexandreG.ProductService.domain.Product;
import com.alexandreG.ProductService.exception.EntityNotFoundException;
import com.alexandreG.ProductService.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SearchProduct {

    private IProductRepository produtoRepository;

    @Autowired
    public SearchProduct(IProductRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Page<Product> buscar(Pageable pageable) {
        return produtoRepository.findAll(pageable);
    }

    public Page<Product> buscar(Pageable pageable, Product.Status status) {
        return produtoRepository.findAllByStatus(pageable, status);
    }

    public Product buscarPorCodigo(String codigo) {
        return produtoRepository.findByCodigo(codigo)
                .orElseThrow(() -> new EntityNotFoundException(Product.class, "codigo", codigo));
    }
}

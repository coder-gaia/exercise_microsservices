/**
 * @author alexandre.gaia
 */

package com.alexandreG.ProductService.usecase;

import com.alexandreG.ProductService.domain.Product;
import com.alexandreG.ProductService.exception.EntityNotFoundException;
import com.alexandreG.ProductService.repository.IProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RegisterProduct {

    private IProductRepository produtoRepository;

    @Autowired
    public RegisterProduct(IProductRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Product cadastrar(@Valid Product produto) {
        produto.setStatus(Product.Status.ATIVO);
        return this.produtoRepository.insert(produto);
    }

    public Product atualizar(@Valid Product produto) {
        return this.produtoRepository.save(produto);
    }

    public void remover(String id) {
        Product prod = produtoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Product.class, "id", id));
        prod.setStatus(Product.Status.INATIVO);
        this.produtoRepository.save(prod);
        //this.produtoRepository.deleteById(id);
    }

}

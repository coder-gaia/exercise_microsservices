/**
 * @author alexandre.gaia
 */

package com.alexandreG.ProductService.controller;

import com.alexandreG.ProductService.domain.Product;
import com.alexandreG.ProductService.usecase.SearchProduct;
import com.alexandreG.ProductService.usecase.RegisterProduct;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    private SearchProduct buscarProduto;

    private RegisterProduct cadastroProduto;

    @Autowired
    public void ProductController(SearchProduct buscaProduto,
                                  RegisterProduct cadastroProduto) {
        this.buscarProduto = buscaProduto;
        this.cadastroProduto = cadastroProduto;
    }

    @GetMapping
    @Operation(summary = "Busca uma lista paginada de produtos")
    public ResponseEntity<Page<Product>> buscar(Pageable pageable) {
        return ResponseEntity.ok(buscarProduto.buscar(pageable));
    }

    @GetMapping(value = "/status/{status}")
    @Operation(summary = "Busca uma lista paginada de produtos por status")
    public ResponseEntity<Page<Product>> buscarPorStatus(Pageable pageable,
                                                         @PathVariable(value = "status", required = true) Product.Status status) {
        return ResponseEntity.ok(buscarProduto.buscar(pageable, status));
    }

    @GetMapping(value = "/{codigo}")
    @Operation(summary = "Busca um produto pelo codigo")
    public ResponseEntity<Product> buscarPorCodigo(String codigo) {
        return ResponseEntity.ok(buscarProduto.buscarPorCodigo(codigo));
    }

    @PostMapping
    @Operation(summary = "Cadastrar um produto")
    public ResponseEntity<Product> cadastrar(@RequestBody @Valid Product produto) {
        return ResponseEntity.ok(cadastroProduto.cadastrar(produto));
    }

    @PutMapping
    @Operation(summary = "Atualiza um produto")
    public ResponseEntity<Product> atualizar(@RequestBody @Valid Product produto) {
        return ResponseEntity.ok(cadastroProduto.atualizar(produto));
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Remove um produto pelo seu identificador único")
    public ResponseEntity<String> remover(@PathVariable(value = "id") String id) {
        cadastroProduto.remover(id);
        return ResponseEntity.ok("Removido com sucesso");
    }
}

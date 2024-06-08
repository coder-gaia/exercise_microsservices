/**
 * @author alexandre.gaia
 */

package com.alexandreG.ClientService.controller;


import com.alexandreG.ClientService.domain.Client;
import com.alexandreG.ClientService.usecase.RegisterClient;
import com.alexandreG.ClientService.usecase.SearchClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class ClientController {

    private SearchClient searchClient;
    private RegisterClient registerClient;


    @Autowired
    public ClientController(SearchClient searchClient,
                            RegisterClient registerClient) {
        this.searchClient = searchClient;
        this.registerClient = registerClient;
    }

    @GetMapping
    @Operation(summary = "Busca uma lista paginada de clientes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna a lista de clientes"),
            @ApiResponse(responseCode = "400", description = "Requisição malformada ou erro de sintaxe",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, examples = @ExampleObject(value = "BAD_REQUEST"))),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, examples = @ExampleObject(value = "INTERNAL_SERVER_ERROR"))),
    })
    public ResponseEntity<Page<Client>> buscar(@Parameter(description="Objeto de paginação") Pageable pageable) {
        return ResponseEntity.ok(searchClient.buscar(pageable));
    }


    @GetMapping(value = "/{id}")
    @Operation(summary = "Busca um cliente pelo id")
    public ResponseEntity<Client> buscarPorId(@PathVariable(value = "id", required = true) String id) throws Throwable {
        return ResponseEntity.ok(searchClient.buscarPorId(id));
    }

    @GetMapping(value = "isCadastrado/{id}")
    @Operation(summary = "Busca um cliente pelo id")
    public ResponseEntity<Boolean> isCadastrado(@PathVariable(value = "id", required = true) String id) {
        return ResponseEntity.ok(searchClient.isCadastrado(id));
    }

    @GetMapping(value = "/cpf/{cpf}")
    @Operation(summary = "Busca um cliente pelo cpf")
    public ResponseEntity<Client> buscarPorCpf(@PathVariable(value = "cpf", required = true) Long cpf) {
        return ResponseEntity.ok(searchClient.buscarPorCpf(cpf));
    }

    @PostMapping
    @Operation(summary = "Cadastrar um cliente")
    public ResponseEntity<Client> cadastrar(@RequestBody @Valid Client cliente) {
        return ResponseEntity.ok(registerClient.cadastrar(cliente));
    }

    @PutMapping
    @Operation(summary = "Atualiza um cliente")
    public ResponseEntity<Client> atualizar(@RequestBody @Valid Client cliente) {
        return ResponseEntity.ok(registerClient.atualizar(cliente));
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Remove um cliente pelo seu identificador único")
    public ResponseEntity<String> remover(@PathVariable(value = "id") String id) {
        registerClient.remover(id);
        return ResponseEntity.ok("Removido com sucesso");
    }

    /*http://server:port/context-path/swagger-ui.html*/
}


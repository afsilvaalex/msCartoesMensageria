package afsilva.com.msclientes.application;

import afsilva.com.msclientes.application.representation.ClienteSaveRequest;
import afsilva.com.msclientes.domain.Cliente;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("clientes")
@RequiredArgsConstructor
@Slf4j
public class ClientesResource {

    private static final Logger log = LoggerFactory.getLogger(ClientesResource.class);

    private final ClienteService service;
    @GetMapping
    public String status(){
        log.info("Obtendo o status do microservice de clientes ");
        return "Ok";
    }



    //chamada do post para salvar cliente
    @PostMapping
    public ResponseEntity save(@RequestBody ClienteSaveRequest request){
       Cliente cliente = request.toModel();
       service.save(cliente);
       URI headerlocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("cpf={cpf}")
                .buildAndExpand(cliente.getCpf())
                .toUri();
       return ResponseEntity.created(headerlocation).build();
    }

    @GetMapping(params = "cpf")
    public ResponseEntity dadosCliente(@RequestParam("cpf") String cpf){
        var cliente = service.getByCPF(cpf);
        if (cliente.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(cliente);

    }
}

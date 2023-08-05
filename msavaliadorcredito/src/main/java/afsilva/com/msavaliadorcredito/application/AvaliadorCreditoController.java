package afsilva.com.msavaliadorcredito.application;

import afsilva.com.msavaliadorcredito.application.ex.DadosClienteNotFoundException;
import afsilva.com.msavaliadorcredito.application.ex.ErroComunicacaoMicroservicesException;
import afsilva.com.msavaliadorcredito.application.ex.ErroSolicitacaoCartaoException;
import afsilva.com.msavaliadorcredito.domain.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("avaliacoes-credito")
public class AvaliadorCreditoController {

    private final AvaliadorCreditoService avaliadorCreditoService;
    @GetMapping
    public String status(){
        return "ok";
    }

    @GetMapping(value = "situacao-cliente", params = "cpf")
    public ResponseEntity consultaSituacaoCliente(@RequestParam("cpf") String cpf) {

        try {
            SituacaoCliente situacaoCliente = avaliadorCreditoService.obterSituacaoCliente(cpf);
            return ResponseEntity.ok(situacaoCliente);

        } catch(DadosClienteNotFoundException e){
            return ResponseEntity.notFound().build();
        } catch (ErroComunicacaoMicroservicesException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }


    }

    @PostMapping
    public ResponseEntity realizaAvaliacao(@RequestBody DadosAvaliacao dados ) {
        try{
            RetornoAvaliacaoCliente retornoAvaliacaoCliente = avaliadorCreditoService.realizaAvaliacao(dados.getCpf(), dados.getRenda());
            return ResponseEntity.ok(retornoAvaliacaoCliente);

        } catch(DadosClienteNotFoundException e){
            return ResponseEntity.notFound().build();
        } catch (ErroComunicacaoMicroservicesException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }

    }


    @PostMapping("solicitacoes-cartao")
    public ResponseEntity solicitaCartao(@RequestBody DadosSolicitacaoEmissaoCartao dados){

        try {
            ProtocoloSolicitacaoCartao protocoloSolicitacaoCartao = avaliadorCreditoService
                    .solicitarEmissaoCartao(dados);
            return ResponseEntity.ok(protocoloSolicitacaoCartao);

        }catch (ErroSolicitacaoCartaoException e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}

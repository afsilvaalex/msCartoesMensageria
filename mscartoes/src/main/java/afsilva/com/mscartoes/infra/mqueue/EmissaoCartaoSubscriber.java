package afsilva.com.mscartoes.infra.mqueue;


import afsilva.com.mscartoes.domain.Cartao;
import afsilva.com.mscartoes.domain.ClienteCartao;
import afsilva.com.mscartoes.domain.DadosSolicitacaoEmissaoCartao;
import afsilva.com.mscartoes.infra.repository.CartaoRepository;
import afsilva.com.mscartoes.infra.repository.ClienteCartaoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmissaoCartaoSubscriber {

    private final CartaoRepository cartaoRepository;
    private final ClienteCartaoRepository clienteCartaoRepository;

    @RabbitListener(queues = "${mq.queues.emissao-cartoes}")
    public void receberSolicitacaoEmissao(@Payload String payload){

        try {
            var mapper = new ObjectMapper();
            DadosSolicitacaoEmissaoCartao dadosSolicitacaoEmissaoCartao = mapper
                    .readValue(payload, DadosSolicitacaoEmissaoCartao.class);
            Cartao cartao =  cartaoRepository.findById(dadosSolicitacaoEmissaoCartao.getIdCartao()).orElseThrow();
            ClienteCartao clienteCartao = new ClienteCartao();
            clienteCartao.setCartao(cartao);
            clienteCartao.setCpf(dadosSolicitacaoEmissaoCartao.getCpf());
            clienteCartao.setLimite(dadosSolicitacaoEmissaoCartao.getLimiteLiberado());

            clienteCartaoRepository.save(clienteCartao);

        }catch (Exception e){
           log.error("Erro ao receber solicitacao de emissao de cartao  : {}", e.getMessage());

        }
    }
}

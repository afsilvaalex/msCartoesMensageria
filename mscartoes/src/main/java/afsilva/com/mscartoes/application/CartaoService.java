package afsilva.com.mscartoes.application;

import afsilva.com.mscartoes.domain.Cartao;
import afsilva.com.mscartoes.infra.repository.CartaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartaoService {

    private final CartaoRepository repository;

    @Transactional
    public Cartao save(Cartao cartao){
        return repository.save(cartao);
    }


    // Listar cartoes
    public List<Cartao> getCartoesRendaMenorIgual(Long renda){

        var rendaBigDecimal = BigDecimal.valueOf(renda);
        return repository.findByRendaLessThanEqual(rendaBigDecimal);

    }

}

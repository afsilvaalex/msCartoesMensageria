package afsilva.com.msavaliadorcredito.application.ex;

import lombok.Getter;

public class ErroComunicacaoMicroservicesException extends Exception{

    @Getter
    private Integer status;

    public ErroComunicacaoMicroservicesException(String msg, Integer status) {
        super(msg);
    }
}

package afsilva.com.msavaliadorcredito.application.ex;

public class DadosClienteNotFoundException  extends  Exception{
    public DadosClienteNotFoundException() {
        super("Dados do Cliente náo econtrado para o cpf informado");
    }
}

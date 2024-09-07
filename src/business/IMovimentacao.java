package business;

import java.math.BigDecimal;

public interface IMovimentacao {

    public boolean sacar(BigDecimal valor);
    public void depositar(BigDecimal valor);
    // public void transferirPara(BigDecimal valor, String agencia, String numero);
    public void visualizarExtrato();
    //a transferência precisa conhecer a conta destinatária e essa conta precisa ser validada
    //logo, implementei a transferência na classe GerenciadorConta
    public String getAgencia();
    public String getNumero();

}

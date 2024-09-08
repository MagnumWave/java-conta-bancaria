package business;

import java.math.BigDecimal;

public interface IMovimentacao {

    public boolean sacar(BigDecimal valor);
    public void depositar(BigDecimal valor);
    //overload para não exibir mensagem de dpósito quando é transferência.
    public void depositar(BigDecimal valor, boolean isFromTransferencia);
    public void transferirPara(IMovimentacao conta, BigDecimal valor);
    public void visualizarExtrato();
    public String getAgencia();
    public String getNumero();

}

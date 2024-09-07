package business;

import java.math.BigDecimal;

public interface IMovimentacao {

    public boolean sacar(BigDecimal valor);
    public void depositar(BigDecimal valor);
    public void transferirPara(BigDecimal valor, String agencia, String numero);
    public void visualizarExtrato();

}

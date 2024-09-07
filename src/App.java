import java.math.BigDecimal;

import business.ContaCorrente;
import business.ContaPoupanca;
import business.GerenciadorContas;

public class App {
    public static void main(String[] args) throws Exception {

        ContaCorrente cc = GerenciadorContas.criarContaCorrente("123", "asd");
        ContaPoupanca cp = GerenciadorContas.criarContaPoupanca("456", "zxc");

        cc.depositar(BigDecimal.valueOf(100));
        cp.depositar(BigDecimal.valueOf(100));

        

        GerenciadorContas.realizarTransferencia(cc, cp, BigDecimal.valueOf(0));

        cc.visualizarExtrato();
        cp.visualizarExtrato();

        
    }
}

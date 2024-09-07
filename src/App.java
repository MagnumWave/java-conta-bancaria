import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import business.ContaCorrente;
import business.ContaPoupanca;
import business.GerenciadorContas;
import business.IMovimentacao;

public class App {
    public static void main(String[] args) throws Exception {

        Set<IMovimentacao> contasCriadas = new HashSet<>();

        ContaCorrente cc = GerenciadorContas.criarContaCorrente("123", "asd", contasCriadas);
        contasCriadas.add(cc);
        ContaPoupanca cp = GerenciadorContas.criarContaPoupanca("456", "zxc",contasCriadas);
        contasCriadas.add(cp);

        cc.depositar(BigDecimal.valueOf(100));
        cp.depositar(BigDecimal.valueOf(100));

        GerenciadorContas.realizarTransferencia(cc, cp, BigDecimal.valueOf(50),contasCriadas);

        cc.visualizarExtrato();
        cp.visualizarExtrato();

        
    }
}

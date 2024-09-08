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

        ContaCorrente cc1 = GerenciadorContas.criarContaCorrente("123", "asd", contasCriadas);
        contasCriadas.add(cc1);
        ContaPoupanca cp1 = GerenciadorContas.criarContaPoupanca("456", "zxc",contasCriadas);
        contasCriadas.add(cp1);

        //esta falha porque o gerenciador sabe que ela já existe.
        ContaCorrente cc2 = GerenciadorContas.criarContaCorrente("123", "asd", contasCriadas);

        //esta pode ser criada porque é a mesma agencia e numero
        //acumulando contas dos 2 tipos.
        ContaCorrente cc3 = GerenciadorContas.criarContaCorrente("456", "zxc",contasCriadas);
        contasCriadas.add(cc3);

        cc1.depositar(BigDecimal.valueOf(100));
        cp1.depositar(BigDecimal.valueOf(100));

        
        // TESTES - descomente para testar
        // cp.transferirPara(null, BigDecimal.valueOf(50));
        // cp.transferirPara(cc, null);
        // cp.transferirPara(cc, BigDecimal.valueOf(-50));
        // cp.transferirPara(cc, BigDecimal.valueOf(0));
        // cp.transferirPara(cc, BigDecimal.valueOf(200));

        cp1.transferirPara(cc1, BigDecimal.valueOf(50));

        cc1.visualizarExtrato();
        cp1.visualizarExtrato();
        
    }
}

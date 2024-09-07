package business;

import java.math.BigDecimal;

public abstract class GerenciadorContas {

    public static ContaCorrente criarContaCorrente(String agencia, String numero){
        return new ContaCorrente(agencia,numero);
    }

    public static ContaPoupanca criarContaPoupanca(String agencia, String numero){
        return new ContaPoupanca(agencia,numero);
    }

    public static void realizarTransferencia(IMovimentacao originAccount, IMovimentacao destinationAccount, BigDecimal valor){
        boolean podeSacar = originAccount.sacar(valor);

        if (podeSacar){
            destinationAccount.depositar(valor);
        } else {
            System.out.println("Transferência não pôde ser realizada");
        }
    }
    
}

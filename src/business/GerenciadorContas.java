package business;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class GerenciadorContas {

    //sem persistencia essa conferência poderia ser feita em um banco de dados.
    //mas é responsabilidade do Gerenciador saber quantas e quais contas existem.
    //para este exemplo iremos fornecer as contas pre-existentes de fora do pacote como sendo uma lista válida.
    public static ContaCorrente criarContaCorrente(String agencia, String numero, Set<IMovimentacao> contasPreExistentes){
        if (contaJaExiste(agencia, numero, TipoDeContaEnum.CONTA_CORRENTE, contasPreExistentes)) {
            System.out.println("Conta informada já existe como Conta Corrente.");
            return null;
        }
        
        return new ContaCorrente(agencia,numero);
    }

    public static ContaPoupanca criarContaPoupanca(String agencia, String numero, Set<IMovimentacao> contasPreExistentes){
        if (contaJaExiste(agencia, numero, TipoDeContaEnum.POUPANCA, contasPreExistentes)) {
            System.out.println("Conta informada já existe como Conta Poupança.");
            return null;
        }
        return new ContaPoupanca(agencia,numero);
    }

    private static boolean contaJaExiste(String agencia, String numero, TipoDeContaEnum tipoDeConta, Set<IMovimentacao> contasPreExistentes) {
        
        //lista de contas que possuem a agencia e o numero informados
        Set<IMovimentacao> contasComMesmaAgenciaENumero = contasPreExistentes.stream().filter( conta -> {
            return conta.getAgencia().equals(agencia) && conta.getNumero().equals(numero);
        }).collect(Collectors.toSet());

        //verifica se a lista tem instancia de Conta Corrente
        boolean jaTemTipoCorrente = contasComMesmaAgenciaENumero.stream()
                                        .anyMatch( x -> {return x instanceof ContaCorrente;});
        
        //verifica se a lista tem instancia de Conta Poupança
        boolean jaTemTipoPoupanca = contasComMesmaAgenciaENumero.stream()
                                        .anyMatch( x -> {return x instanceof ContaPoupanca;});

        //Se procurou por conta corrente e ela já existe, retorna positiva
        if (tipoDeConta.equals(TipoDeContaEnum.CONTA_CORRENTE) && jaTemTipoCorrente) {
            return true;
        }

        //Se procurou por conta poupança e ela já existe, retorna positiva
        if (tipoDeConta.equals(TipoDeContaEnum.POUPANCA) && jaTemTipoPoupanca) {
            return true;
        }

        //retorna falso dizendo que a conta não existe e pode ser criada
        return false;
    }
    
}

package business;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class GerenciadorContas {

    //sem persistencia essa conferência poderia ser feita em um banco de dados.
    //mas é responsabilidade do Gerenciador saber quantas e quais contas existem.
    //para este exemplo iremos fornecer ela de fora do pacote como sendo uma lista válida.
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

    public static void realizarTransferencia(IMovimentacao remetente, IMovimentacao destinataria, BigDecimal valor, Set<IMovimentacao> contasPreExistentes){

        if(valorTransferidoInvalido(valor)){
            System.out.println("Informe um valor positivo e diferente de zero.");
            return;
        }

        if(!contasInformadasExistemNaLista(remetente, destinataria, contasPreExistentes)){
            //segurança:
            //nem sempre informamos que uma conta não existe, pois o usuário tentará uma brecha pra cadastrar maliciosamente.
            System.out.println("Contas informadas não estão válidas.");
            return;
        }

        boolean podeSacar = remetente.sacar(valor);

        if (podeSacar){
            destinataria.depositar(valor);
        } else {
            System.out.println("Transferência não pôde ser realizada");
        }
    }

    private static boolean contasInformadasExistemNaLista(IMovimentacao remetente, IMovimentacao destinataria, Set<IMovimentacao> contasPreExistentes) {

        boolean remetenteExiste = contasPreExistentes.contains(remetente);
        boolean destinatariaExiste = contasPreExistentes.contains(destinataria);

        return remetenteExiste && destinatariaExiste;
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

    private static boolean valorTransferidoInvalido(BigDecimal valor) {
        return (valor.signum() == -1 || valor.signum() == 0);
    }
    
}

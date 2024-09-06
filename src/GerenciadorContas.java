import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class GerenciadorContas {

    private List<Conta> contasExistentes;
    //private Map<String,String> currentConta = new HashMap<String,String>();

    public ContaCorrente criarContaCorrente(String agencia, String numero){
        
        

        return new ContaCorrente(agencia,numero);
    }

    public ContaPoupanca criarContaPoupanca(String agencia, String numero){

        return new ContaPoupanca(agencia,numero);
    }

    private boolean contaExiste(String agencia, String numero){
        boolean agenciaExiste = contasExistentes.stream()
                                    .map(x -> x.getAgencia())
                                    .anyMatch(x -> x.equals(agencia));

        boolean numeroExiste = contasExistentes.stream()
                                    .map(x -> x.getNumero())
                                    .anyMatch(x -> x.equals(numero));

        //TODO: terminar de implementar a verificação de conta
        //a classe gerenciadora vai ser quem verifica se uma conta existe, 
        //logo, aplicará o padrão factory pra que ela possa contar quantas contas existem e se pode criar o próximo tipo

        return agenciaExiste;
    }

    

    
}

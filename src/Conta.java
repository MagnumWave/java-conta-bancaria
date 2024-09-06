import java.math.BigDecimal;

public abstract class Conta {

    private String agencia;
    private String numero;
    private BigDecimal saldo;

    public String getAgencia(){
        return this.agencia;
    }

    public void setAgencia(String agencia){
        this.agencia = agencia;
    }

    public String getNumero(){
        return this.numero;
    }

    public void setNumero(String numero){
        this.numero = numero;
    }

    public BigDecimal getSaldo(){
        return this.saldo;
    }

    // public void setSaldo(BigDecimal saldo){
    //     this.saldo = saldo;
    // }

    public Conta(String agencia, String numero){
        this.agencia = agencia;
        this.numero = numero;
    }


    public void transferirPara(BigDecimal valor, String numero){
        // precisa utilizar um verificador da existência de outras contas, que estará em outra classe
        //precisa verificar se possui saldo para transferir
        saldoEhMaiorQue(valor);

    }

    public void depositar(BigDecimal valor){
        //usar verificador de terminal eletronico
    }

    public void sacar(BigDecimal valor){
        if (saldoEhMaiorQue(valor)) {
            BigDecimal diferenca = this.saldo.subtract(valor);
            this.saldo = diferenca; 
            System.out.println("Saque realizado com sucesso!");   
        } else {
            System.out.println("Não há saldo suficiente.");   
        }
        
    }

    private boolean saldoEhMaiorQue(BigDecimal valor) {
        return this.saldo.compareTo(valor) > 0;
    }
    
}
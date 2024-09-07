package business;

import java.math.BigDecimal;

public class ContaPoupanca extends Conta {

    private BigDecimal saldoCPoupanca;

    public BigDecimal getSaldoCPoupanca(){
        return this.saldoCPoupanca;
    }

    public void setSaldoCPoupanca(BigDecimal valor){
        this.saldoCPoupanca = valor;
    }

    ContaPoupanca(String agencia, String numero){
        //verificar se já existe contaPoupança com essa agencia e esse número
        super(agencia,numero);
        this.saldoCPoupanca = new BigDecimal(0);
    }

    @Override
    public boolean sacar(BigDecimal valor) {
        boolean podeSubtrair = podeSubtrair(valor);

        if (podeSubtrair) {
            BigDecimal diferenca = this.saldoCPoupanca.subtract(valor);
            this.saldoCPoupanca = diferenca; 
            System.out.println("Saque realizado com sucesso!");  
        } else {
            System.out.println("Não há saldo suficiente.");   
        }

        return podeSubtrair;

    }

    @Override
    public void depositar(BigDecimal valor) {
        this.saldoCPoupanca = this.saldoCPoupanca.add(valor);
    }

    @Override
    public void transferirPara(BigDecimal valor, String agencia, String numero) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'transferirPara'");
    }


    protected boolean podeSubtrair(BigDecimal valor) {
        return this.saldoCPoupanca.compareTo(valor) > 0;
    }

    @Override
    public void visualizarExtrato() {
        System.out.println("Saldo na Conta Poupança é: "+ Double.parseDouble(getSaldoCPoupanca().toString()));
    }

    
    
}

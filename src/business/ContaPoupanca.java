package business;

import java.math.BigDecimal;

public class ContaPoupanca extends Conta {

    private BigDecimal saldoCPoupanca;

    //default porque quem tá fora do pacote precisa passar pelo Gerenciador para ver e definir o saldo.
    BigDecimal getSaldoCPoupanca(){
        return this.saldoCPoupanca;
    }

    void setSaldoCPoupanca(BigDecimal valor){
        this.saldoCPoupanca = valor;
    }

    
    public String getAgencia(){
        return super.getAgencia();
    }

    public String getNumero(){
        return super.getNumero();
    }

    ContaPoupanca(String agencia, String numero){
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


    protected boolean podeSubtrair(BigDecimal valor) {
        return this.saldoCPoupanca.compareTo(valor) > 0;
    }

    //forma de emitir o extrato sem precisar do gerenciador, logo, é public
    @Override
    public void visualizarExtrato() {
        System.out.println("--------------------");
        System.out.println("Agência: " + getAgencia());
        System.out.println("Número: " + getNumero());
        System.out.println("Tipo de conta: " + TipoDeContaEnum.POUPANCA.getDescricao());
        System.out.println("Saldo: "+ Double.parseDouble(getSaldoCPoupanca().toString()));
        System.out.println("--------------------");
    }

    
    
}

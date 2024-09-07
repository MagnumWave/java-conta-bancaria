package business;

import java.math.BigDecimal;

public class ContaCorrente extends Conta {

    private BigDecimal saldoCCorrente;

    //default porque quem tá fora do pacote precisa passar pelo Gerenciador para ver e definir o saldo.
    BigDecimal getSaldoCCorrente(){
        return this.saldoCCorrente;
    }

    void setSaldoCCorrente(BigDecimal valor){
        this.saldoCCorrente = valor;
    }

    public String getAgencia(){
        return super.getAgencia();
    }

    public String getNumero(){
        return super.getNumero();
    }

    ContaCorrente(String agencia, String numero){
        super(agencia,numero);
        this.saldoCCorrente = new BigDecimal(0);
    }

    @Override
    public boolean sacar(BigDecimal valor) {
        boolean podeSubtrair = podeSubtrair(valor);

        if (podeSubtrair) {
            BigDecimal diferenca = this.saldoCCorrente.subtract(valor);
            this.saldoCCorrente = diferenca; 
            System.out.println("Saque realizado com sucesso!");  
        } else {
            System.out.println("Não há saldo suficiente.");   
        }

        return podeSubtrair;
    }

    @Override
    public void depositar(BigDecimal valor) {
        this.saldoCCorrente = this.saldoCCorrente.add(valor);
    }


    private boolean podeSubtrair(BigDecimal valor) {
        return this.saldoCCorrente.compareTo(valor) > 0;
    }

    //forma de emitir o extrato sem precisar do gerenciador, logo, é public
    @Override
    public void visualizarExtrato() {
        System.out.println("--------------------");
        System.out.println("Agência: " + getAgencia());
        System.out.println("Número: " + getNumero());
        System.out.println("Tipo de conta: " + TipoDeContaEnum.CONTA_CORRENTE.getDescricao());
        System.out.println("Saldo: "+ Double.parseDouble(getSaldoCCorrente().toString()));
        System.out.println("--------------------");
    }
}

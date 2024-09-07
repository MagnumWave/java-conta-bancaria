package business;
import java.math.BigDecimal;

public class ContaCorrente extends Conta {

    private BigDecimal saldoCCorrente;

    public BigDecimal getSaldoCCorrente(){
        return this.saldoCCorrente;
    }

    public void setSaldoCCorrente(BigDecimal valor){
        this.saldoCCorrente = valor;
    }

    public ContaCorrente(String agencia, String numero){
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

    @Override
    public void transferirPara(BigDecimal valor, String agencia, String numero) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'transferirPara'");
    }


    private boolean podeSubtrair(BigDecimal valor) {
        return this.saldoCCorrente.compareTo(valor) > 0;
    }

    @Override
    public void visualizarExtrato() {
        System.out.println("Saldo na Conta Corrente é: "+ Double.parseDouble(getSaldoCCorrente().toString()));
    }
}

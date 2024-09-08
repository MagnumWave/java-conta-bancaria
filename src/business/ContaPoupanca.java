package business;

import java.math.BigDecimal;
import java.util.Objects;

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
        System.out.println("Depósito de R$"+valor.toPlainString()+" realizado na conta "+getNumero());

    }

    @Override
    public void depositar(BigDecimal valor, boolean isFromTransferencia) {

        this.saldoCPoupanca = this.saldoCPoupanca.add(valor);

        if(!isFromTransferencia){
            System.out.println("Depósito de R$"+valor.toPlainString()+" realizado na conta "+getNumero());
        }

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

    @Override
    public void transferirPara(IMovimentacao conta, BigDecimal valor) {
        
        if (!validaTransferencia(conta, valor)) {
            return;
        }

        if (podeSubtrair(valor)) {
            BigDecimal diferenca = this.saldoCPoupanca.subtract(valor);
            this.saldoCPoupanca = diferenca; 
            conta.depositar(valor, true);
            //this.saldoCPoupanca = this.saldoCPoupanca.add(valor);
            System.out.println("--------------------");
            System.out.println("Transferência realizada com sucesso!");
            System.out.println("Conta "+getNumero()+
                            " transferiu R$"+valor.toPlainString()+
                            " para a conta "+conta.getNumero()+".");
            System.out.println("--------------------");
        } else {
            System.out.println("Não há saldo suficiente para realizar essa transferência.");
            return;
        }
        
    }

    private boolean validaTransferencia(IMovimentacao conta, BigDecimal valor) {

        if(Objects.isNull(conta)){
            System.out.println("Conta não informada.");
            return false;
        }

        if(Objects.isNull(valor)){
            System.out.println("Valor não informado.");
            return false;
        }

        if(Objects.equals(conta, this)){
            System.out.println("Contas remetente e destinatária são as mesmas.");
            return false;
        }

        if(valor.signum() <= 0){
            System.out.println("Valores negativos ou nulos não são válidos.");
            return false;
        }

        return true;

    }

    
    
}

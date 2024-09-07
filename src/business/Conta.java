package business;

public abstract class Conta implements IMovimentacao {

    private String agencia;
    private String numero;

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

    protected Conta(String agencia, String numero){
        this.agencia = agencia;
        this.numero = numero;
    }
    
}
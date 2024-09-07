package business;

public abstract class Conta implements IMovimentacao {

    //strings pois podem possuir separadores de dígito verificador, por exemplo.
    private String agencia;
    private String numero;

    public String getAgencia(){
        return this.agencia;
    }

    void setAgencia(String agencia){
        this.agencia = agencia;
    }

    public String getNumero(){
        return this.numero;
    }

    void setNumero(String numero){
        this.numero = numero;
    }

    protected Conta(String agencia, String numero){
        this.agencia = agencia;
        this.numero = numero;
    }
    
}
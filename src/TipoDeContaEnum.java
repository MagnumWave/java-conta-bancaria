public enum TipoDeContaEnum {
    CONTA_CORRENTE(0,"Conta Corrente"),
    POUPANCA(1,"Poupança");

    private int id;
    private String descricao;

    TipoDeContaEnum(int id, String descricao){
        this.id = id;
        this.descricao = descricao;
    }

    public int getId(){
        return this.id;
    }

    public String getDescricao(){
        return this.descricao;
    }
}

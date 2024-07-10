public class Cliente {
    private String nome;
    private String cnh;
    private String telefone;
    private String cpf;
    private boolean locacaoAtiva;

    public Cliente(String nome, String cnh, String telefone, String cpf, boolean locacaoAtiva) {
        this.nome = nome;
        this.cnh = cnh;
        this.telefone = telefone;
        this.cpf = cpf;
        this.locacaoAtiva = locacaoAtiva;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + nome + '\'' +
                ", cnh='" + cnh + '\'' +
                ", telefone='" + telefone + '\'' +
                ", cpf='" + cpf + '\'' +
                ", locacaoAtiva=" + locacaoAtiva +
                '}';
    }

    public String getCpf() {
        return cpf;
    }

    public String getCnh() {
        return cnh;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public boolean isLocacaoAtiva() {
        return locacaoAtiva;
    }

    public void setLocacaoAtiva(boolean locacaoAtiva) {
        this.locacaoAtiva = locacaoAtiva;
    }
}

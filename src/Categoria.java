import java.util.Objects;

public class Categoria {
    private int identificador;
    private String nome;

    public Categoria(int identificador) {
        this.identificador = identificador;
    }

    public Categoria(int identificador, String nome) {
        this.identificador = identificador;
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "identificador=" + identificador +
                ", nome='" + nome + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Categoria categoria)) return false;
        return getIdentificador() == categoria.getIdentificador() && Objects.equals(getNome(), categoria.getNome());
    }

    public int getIdentificador() {
        return identificador;
    }

    public String getNome() {
        return nome;
    }

}

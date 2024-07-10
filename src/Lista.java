public interface Lista {
    void insereInicio(Object info);
    void insereFim(Object info);
    boolean isVazia();
    void remove(Object info);
    int tamanho();
    void imprimir();
    void imprimirReverso();
    Noh busca(Object info);
}

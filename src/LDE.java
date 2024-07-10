import java.util.function.Predicate;

public class LDE<T> implements Lista{

    private Noh inicio;
    private Noh fim;
    private int tamanho;

    public LDE(){
        inicio = null;
        fim = null;
        tamanho = 0;
    }

    @Override
    public void insereInicio(Object info) {
        Noh valor = new Noh(info);
        if (inicio == null){
            fim = valor;
        }else{
            valor.setProximo(inicio);
            inicio.setAnterior(valor);
        }
        inicio = valor;
        tamanho++;
    }

    @Override
    public void insereFim(Object info) {
        Noh valor = new Noh(info);
        if (fim == null){ //inicio e/ou fim vazio signifca lista vazia
            inicio = valor;
        }else{
            fim.setProximo(valor);
            valor.setAnterior(fim);
        }
        fim = valor;
        tamanho++;
    }

    @Override
    public Noh busca(Object info) {
        Noh valor = inicio;
        while (valor != null && valor.getInfo() != info)
            valor = valor.getProximo();
        return valor;
    }

    /***
     * Utilizamos predicate para poder pesquisar por atributos específicos dos objetos através da lista.
     * Por exemplo: cpf de um cliente, placa de um carro, identificador de uma categoria
     * @param predicate expressao lambda que faz a comparação do atributo
     * @return 1: null, caso não encontre o objeto 2: o objeto encontrado, que poderá ser modificado
     * conforme necessidade posteriormente.
     */
    public T busca_atributo(Predicate<T> predicate) {
        Noh atual = inicio;
        while (atual != null) {
            // Verifica se o dado armazenado no nodo atual atende ao predicado
            if (predicate.test((T) atual.getInfo())) {
                return (T) atual.getInfo();
            }
            atual = atual.getProximo();
        }
        return null;
    }

    @Override
    public boolean isVazia() {
        return tamanho == 0;
    }

    @Override
    public void remove(Object info) {
        Noh valor = this.busca(info);

        if (valor == null){
            return;
        }else if (valor.equals(inicio)){ //primeiro elemento
            inicio = inicio.getProximo();
            if (inicio != null){
                inicio.setAnterior(null);
            }else{
                fim = null;
            }
        } else if (valor.getProximo() == null) { //ultimo elemento
            valor.getAnterior().setProximo(null);
            fim = valor.getAnterior();
        }else{
            valor.getAnterior().setProximo(valor.getProximo());
            valor.getProximo().setAnterior(valor.getAnterior());
        }

        tamanho--;
    }

    @Override
    public int tamanho() {
        return tamanho;
    }

    @Override
    public void imprimir() {
        Noh valor;
        valor = inicio;
        for (int i = 0; i < tamanho; i++) {
            System.out.println(valor.getInfo().toString() + " ");
            valor = valor.getProximo();
        }
    }

    @Override
    public void imprimirReverso(){
        Noh valor;
        valor = fim;
        for (int i = tamanho; i > 0; i--) {
            System.out.println(valor.getInfo().toString() + " ");
            valor = valor.getAnterior();
        }
    }

    public Noh getInicio() {
        return inicio;
    }
}
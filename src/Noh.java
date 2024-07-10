public class Noh {
    private Object info;
    private Noh ant;
    private Noh prox;

    public Noh(Object info){
        this.info = info;
        this.ant = null;
        this.prox = null;
    }

    public Object getInfo() {
        return info;
    }

    public Noh getAnterior() {
        return ant;
    }

    public Noh getProximo() {
        return prox;
    }

    public void setAnterior(Noh ant) {
        this.ant = ant;
    }

    public void setProximo(Noh prox) {
        this.prox = prox;
    }

    public Object getObject() {
        return info;
    }
}

package exceptions;

public class OrdenacaoInvalidaException extends RuntimeException{
    public OrdenacaoInvalidaException(String message){
        super(message);
    }
}

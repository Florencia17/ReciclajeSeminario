package ModeloException;

public class DataEmptyException extends Exception {
    //AGREGAR OTROS ATRIBUTOS EN CASO NECESARIO
    public DataEmptyException () {
    }
    public DataEmptyException (String message) {
        super(message);
    }
}

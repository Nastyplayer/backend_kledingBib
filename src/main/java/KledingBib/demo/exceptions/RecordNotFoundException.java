package KledingBib.demo.exceptions;



import org.springframework.http.HttpStatus;

public class RecordNotFoundException extends RuntimeException {




  //  private static final long serialVersionUID = 1L;
    public RecordNotFoundException() {
        super( "Not found");
    }
    public RecordNotFoundException(String message) {
        super(message);
    }


  //  public RecordNotFoundException(String no_found, HttpStatus notFound) {
   // }
}


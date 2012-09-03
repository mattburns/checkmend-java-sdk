package uk.co.mattburns.checkmend;

import java.util.List;

@SuppressWarnings("serial")
public class CheckmendError extends RuntimeException {

    private List<Error> errors;

    public CheckmendError(List<Error> errors) {
        this.errors = errors;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public class Error {
        private String message;
        private int id;

        public Error(String message, int id) {
            this.message = message;
            this.id = id;
        }

        public String getMessage() {
            return message;
        }

        public int getId() {
            return id;
        }
    }
}

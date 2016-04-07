package uk.co.mattburns.checkmend;

import java.util.List;

@SuppressWarnings("serial")
public class CheckmendError extends RuntimeException {

    private List<Error> errors;

    public CheckmendError() {
    }

    public CheckmendError(List<Error> errors) {
        this.errors = errors;
    }

    public List<Error> getErrors() {
        return errors;
    }

    @Override
    public String toString() {
        return super.toString() + errors.toString();
    }

    public static class Error {
        private String message;
        private int id;

        // no arg constructor for gson
        public Error() {

        }

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

        @Override
        public String toString() {
            return id + ":" + message;
        }
    }
}

package br.com.uds.acaipersonalizados.api.exception;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

final class ResponseFieldError implements Serializable {

    private final String field;

    private final List<String> messages;

    private ResponseFieldError(ResponseFieldErrorBuilder builder) {
        this.field = builder.field;
        this.messages = builder.messages;
    }

    public String getField() {
        return field;
    }

    public List<String> getMessages() {
        return Collections.unmodifiableList(messages);
    }

    static ResponseFieldErrorBuilder builder() {
        return new ResponseFieldErrorBuilder();
    }

    public static final class ResponseFieldErrorBuilder {
        private String field;

        private List<String> messages;

        private ResponseFieldErrorBuilder() {
        }

        public ResponseFieldErrorBuilder withField(String field) {
            this.field = field;
            return this;
        }

        public ResponseFieldErrorBuilder withMessages(List<String> messages) {
            this.messages = messages;
            return this;
        }

        public ResponseFieldError build() {
            return new ResponseFieldError(this);
        }
    }
}

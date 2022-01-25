package com.hardziyevich.gateway.command;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.hardziyevich.gateway.command.Field.DAY;
import static com.hardziyevich.gateway.command.Field.SERVICE;

public enum CommandProvider {

    FIND_ALL(List.of()),
    FIND_DAY(List.of(DAY)),
    FIND_SERVICE(List.of(SERVICE)),
    FIND_DAY_AND_SERVICE(List.of(DAY, SERVICE));

    private final List<Field> fields;

    CommandProvider(List<Field> fields) {
        this.fields = fields;
    }

    public List<Field> getFields() {
        return fields;
    }

    public String getValueFromField(Field field) {
        final String replace = "";
        return fields.stream()
                .filter(x -> x.equals(field))
                .map(x -> Optional.ofNullable(x.getValue()).orElse(replace))
                .findFirst()
                .orElse(replace);
    }

    public static Optional<CommandProvider> findRequestType(List<Field> fields) {
        return Arrays.stream(CommandProvider.values())
                .filter(r -> r.fields.containsAll(fields))
                .findFirst();
    }

}

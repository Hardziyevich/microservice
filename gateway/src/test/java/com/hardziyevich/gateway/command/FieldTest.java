package com.hardziyevich.gateway.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;

@ExtendWith(MockitoExtension.class)
public class FieldTest {


    @BeforeEach
    void init() {
        Arrays.stream(Field.values()).forEach(x -> x.setValue(null));
    }

    @Test
    @DisplayName("Fields contain a value")
    void fieldTest() {
        //given
        Field.DAY.setValue("1");
        Field.SERVICE.setValue("2");
        //when
        List<Field> allNotBlankFields = Field.findAllNotBlankFields();
        then(allNotBlankFields).isEqualTo(List.of(Field.DAY,Field.SERVICE));
    }

    @Test
    @DisplayName("Fields doesn`t exist")
    void fieldNotExist() {
        //when
        List<Field> allNotBlankFields = Field.findAllNotBlankFields();
        then(allNotBlankFields).isEqualTo(List.of());
    }

    @Test
    @DisplayName("Groomer request contains fields")
    void requestGroomerTest1() {
        //given
        Field.DAY.setValue("1");
        Field.SERVICE.setValue("2");
        List<Field> allNotBlankFields = Field.findAllNotBlankFields();
        List<Field> expect = List.of(Field.DAY,Field.SERVICE);
        //when
        Optional<CommandProvider> requestType = CommandProvider.findRequestType(allNotBlankFields);
        then(requestType).isEqualTo(Optional.of(CommandProvider.FIND_DAY_AND_SERVICE));
        then(requestType.get().getFields()).isEqualTo(expect);
    }

    @Test
    @DisplayName("Groomer request does`t contains fields")
    void requestGroomerTest2() {
        //given
        List<Field> allNotBlankFields = Field.findAllNotBlankFields();
        Optional<CommandProvider> requestType = CommandProvider.findRequestType(allNotBlankFields);
        then(requestType).isEqualTo(Optional.of(CommandProvider.FIND_ALL));
    }

    @Test
    @DisplayName("Test find field with null value")
    void findNullField(){
        //given
        CommandProvider commandProvider = CommandProvider.FIND_DAY;
        String result = "";
        //when
        String field = commandProvider.getValueFromField(Field.DAY);
        then(field).isEqualTo(result);
    }

    @Test
    @DisplayName("Test find field")
    void findField() {
        //given
        String result = "test";
        Field.DAY.setValue(result);
        CommandProvider commandProvider = CommandProvider.FIND_DAY;
        //when
        String field = commandProvider.getValueFromField(Field.DAY);
        then(field).isEqualTo(result);
    }

    @Test
    @DisplayName("Test getValue from Field from different cases")
    void fetFields() {
        //given
        String expect = "test";
        Field.SERVICE.setValue("test");
        //when
        String valueFromField1 = CommandProvider.FIND_SERVICE.getValueFromField(Field.SERVICE);
        String valueFromField2 = CommandProvider.FIND_SERVICE.getValueFromField(Field.SERVICE);
        then(valueFromField1).isEqualTo(expect);
        then(valueFromField2).isEqualTo("");
    }

}

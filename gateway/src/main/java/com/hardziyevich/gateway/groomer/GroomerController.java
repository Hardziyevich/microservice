package com.hardziyevich.gateway.groomer;

import com.hardziyevich.gateway.command.CommandProvider;
import com.hardziyevich.gateway.command.CommandRequestProvider;
import com.hardziyevich.gateway.command.Field;
import com.hardziyevich.resource.dto.GroomerDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.*;

import static com.hardziyevich.gateway.command.Field.*;

@RestController
@RequestMapping("/groomer")
public class GroomerController {

    private final CommandRequestProvider commandRequestProvider;

    public GroomerController(CommandRequestProvider commandRequestProvider) {
        this.commandRequestProvider = commandRequestProvider;
    }

    @PostMapping("/find")
    public ResponseEntity<?> findAllGroomer(@RequestBody @Valid GroomerDto groomerDto){
        ResponseEntity<?> response = ResponseEntity.badRequest().body("");
        DAY.setValue(groomerDto.getDay());
        SERVICE.setValue(groomerDto.getServiceType());
        List<Field> allNotBlankFields = findAllNotBlankFields();
        Optional<CommandProvider> requestType = CommandProvider.findRequestType(allNotBlankFields);
        if(requestType.isPresent()){
            CommandProvider commandProvider = requestType.get();
            CommandRequestGroomer command = commandRequestProvider.findCommand(commandProvider);
            response = command.defaultRequest();
        }
        return response;
    }
}

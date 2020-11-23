package digital.paisley.phonebook.apis;

import com.fasterxml.jackson.databind.ObjectMapper;
import digital.paisley.phonebook.dto.ContactDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

public interface ContactApi {

    Logger log = LoggerFactory.getLogger(ContactApi.class);

    default Optional<ObjectMapper> getObjectMapper() {
        return Optional.empty();
    }

    default Optional<HttpServletRequest> getRequest() {
        return Optional.empty();
    }

    @Operation(summary = "Add New Contact", tags = {"contact"}, operationId = "addContact")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = ContactDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Server Internal Error")})
    @PostMapping(value = "/contacts", produces = {"application/json"})
    default ResponseEntity<?> addContact(@Valid @RequestBody ContactDto contactDto) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    @Operation(description = "Search Contact", tags = {
            "contact"}, operationId = "searchContact")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ContactDto.class)))),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Server Internal Error")})
    @GetMapping(value = "/contacts", produces = {"application/json"})
    default ResponseEntity<?> getContacts(@RequestParam(value = "search") String search) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}

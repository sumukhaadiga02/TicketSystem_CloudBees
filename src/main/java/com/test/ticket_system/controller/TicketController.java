package com.test.ticket_system.controller;

import ch.qos.logback.core.util.StringUtil;
import com.test.ticket_system.dtos.InputDto;
import com.test.ticket_system.dtos.User;
import com.test.ticket_system.entity.TicketEntity;
import com.test.ticket_system.reposiroty.TicketRepository;
import com.test.ticket_system.service.SaveTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
public class TicketController {

    @Autowired
    SaveTicket saveTicket;

    @Autowired
    TicketRepository ticketRepository;
    @PostMapping(value = "v1/bookTicket")
    public ResponseEntity<String> book_ticket(@RequestBody InputDto inputDto) {

        if(inputDto.getPrice_Paid() != 20) {
            return new ResponseEntity<>("The amount is less or more than 20 please try paying again", HttpStatus.BAD_REQUEST);
        }
        if(!inputDto.getFrom_station().equalsIgnoreCase("LONDON") &&
                !inputDto.getTo_station().equalsIgnoreCase("FRANCE") ) {
            return new ResponseEntity<>("The from or to stations are wrong", HttpStatus.BAD_REQUEST);
        }
        if(!inputDto.getSection().equalsIgnoreCase("A") &&
                !inputDto.getSection().equalsIgnoreCase("B")) {
            return new ResponseEntity<>("Invalid section selected", HttpStatus.BAD_REQUEST);
        }

        try {
            saveTicket.save(inputDto);
            return new ResponseEntity<>("Saved ticket data", HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>("Unknown exception while saving data", HttpStatus.BAD_GATEWAY);
        }
    }

    @GetMapping(value = "v1/getTicket")
    public ResponseEntity<Optional<TicketEntity>> book_ticket(@RequestParam Long ticketId) {
        Optional<TicketEntity> ticketEntity = Optional.empty();
        if(ticketId<=0) {
            System.out.println("Invalid ticketId");
            return new ResponseEntity<>(ticketEntity, HttpStatus.BAD_REQUEST);
        } else {
            ticketEntity = saveTicket.getTicketDetails(ticketId);
            if(ticketEntity.isEmpty()) {
                System.out.println("Value Not found");
                return new ResponseEntity<>(ticketEntity, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(ticketEntity, HttpStatus.OK);
        }
    }

    @GetMapping(value = "v1/getUserBySection")
    public ResponseEntity<List<User>> book_ticket(@RequestParam String section) {
        List<User> names;
        if(!section.equalsIgnoreCase("A") &&
                !section.equalsIgnoreCase("B")) {
            System.out.println("Invalid section chosen");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else {
            names = saveTicket.getUserBySection(section);
            if(names.isEmpty()) {
                System.out.println("Value Not found");
                return new ResponseEntity<>(names, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(names, HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "v1/deleteUser")
    public Boolean delete_User(@RequestParam Long ticketId) {
        try {
            return saveTicket.deleteUser(ticketId);
        } catch (Exception e){
            System.out.println("Exception while user deletion "+ e.getMessage());
            return Boolean.FALSE;
        }
    }

    @PutMapping(value = "v1/modifyUser")
    public ResponseEntity<TicketEntity> modify_Ticket(@RequestBody InputDto inputDto) {
        TicketEntity res = null;
        if(inputDto.getPrice_Paid() != 20) {
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }
        if(!inputDto.getFrom_station().equalsIgnoreCase("LONDON") &&
                !inputDto.getTo_station().equalsIgnoreCase("FRANCE") ) {
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }
        if(!inputDto.getSection().equalsIgnoreCase("A") &&
                !inputDto.getSection().equalsIgnoreCase("B")) {
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }
        try {
            res = saveTicket.modifyUser(inputDto);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e){
            System.out.println("Exception while user deletion "+ e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

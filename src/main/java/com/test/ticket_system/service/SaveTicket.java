package com.test.ticket_system.service;

import com.test.ticket_system.dtos.InputDto;
import com.test.ticket_system.dtos.User;
import com.test.ticket_system.entity.TicketEntity;
import com.test.ticket_system.reposiroty.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SaveTicket {

    @Autowired
    TicketRepository ticketRepository;

    public void save(InputDto inputDto) {
        try {
            TicketEntity ticket = new TicketEntity();
            ticket.setFrom_station(inputDto.getFrom_station());
            ticket.setTo_station(inputDto.getTo_station());
            ticket.setFirstName(inputDto.getUser().getFirstName());
            ticket.setLastName(inputDto.getUser().getLastName());
            ticket.setPrice_Paid(inputDto.getPrice_Paid());
            ticket.setEmailId(inputDto.getUser().getEmail());
            ticket.setSection(inputDto.getSection());
            ticket.setSeatNumber(inputDto.getSeatNumber());
            ticketRepository.save(ticket);
        } catch(Exception e) {
            System.out.println("Database Exception" + e.getMessage());
        }
    }

    public Optional<TicketEntity> getTicketDetails(Long ticketId) {
        try {
            return ticketRepository.findById(ticketId);
        } catch(Exception e) {
            System.out.println("Database Exception" + e.getMessage());
        }
        return Optional.empty();
    }

    public List<User> getUserBySection(String section) {
        try {
            return ticketRepository.findBysection(section);
        } catch(Exception e) {
            System.out.println("Database Exception" + e.getMessage());
        }
        return null;
    }

    public Boolean deleteUser(Long ticketId) {
        try {
            if(ticketRepository.findById(ticketId).isPresent()) {
                ticketRepository.deleteById(ticketId);
                return Boolean.TRUE;
            } else {
                System.out.println("Data already deleted or not present in DB");
                return Boolean.FALSE;
            }
        } catch(Exception e) {
            System.out.println("Database Exception" + e.getMessage());
            return Boolean.FALSE;
        }
    }

    public TicketEntity modifyUser(InputDto inputDto) {
        TicketEntity ticket = ticketRepository.findBySeatNumber(inputDto.getSeatNumber());
        if(Objects.nonNull(ticket)) {
            ticket.setFrom_station(inputDto.getFrom_station());
            ticket.setTo_station(inputDto.getTo_station());
            ticket.setFirstName(inputDto.getUser().getFirstName());
            ticket.setLastName(inputDto.getUser().getLastName());
            ticket.setPrice_Paid(inputDto.getPrice_Paid());
            ticket.setEmailId(inputDto.getUser().getEmail());
            ticket.setSection(inputDto.getSection());
            ticket.setSeatNumber(inputDto.getSeatNumber());
            return ticketRepository.save(ticket);
        } else {
            System.out.println("User not bor present in the train DB");
            return null;
        }
    }
}

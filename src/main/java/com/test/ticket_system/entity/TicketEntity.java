package com.test.ticket_system.entity;


import com.test.ticket_system.dtos.User;
import jakarta.persistence.*;

@Entity
@Table(name = "TICKET_DETAILS")
public class TicketEntity {
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public int getPrice_Paid() {
        return price_Paid;
    }

    public void setPrice_Paid(int price_Paid) {
        this.price_Paid = price_Paid;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ticket_Id;

    private String firstName;
    private String lastName;
    private String emailId;
    private String From_station;

    public String getFrom_station() {
        return From_station;
    }

    public void setFrom_station(String from_station) {
        From_station = from_station;
    }

    public String getTo_station() {
        return To_station;
    }

    public void setTo_station(String to_station) {
        To_station = to_station;
    }

    private String To_station;
    private int price_Paid;
    private int seatNumber;

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    private String section;

    public Long getTicket_Id() {
        return ticket_Id;
    }

    public void setTicket_Id(Long ticket_Id) {
        this.ticket_Id = ticket_Id;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }
}

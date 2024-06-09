package com.test.ticket_system.reposiroty;

import com.test.ticket_system.dtos.User;
import com.test.ticket_system.entity.TicketEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface TicketRepository extends CrudRepository<TicketEntity, Long> {

    @Query("select new com.test.ticket_system.dtos.User(T.firstName, T.lastName, T.emailId) from TicketEntity as T where T.section = ?1")
    List<User> findBysection(String section);

    TicketEntity findBySeatNumber(int seatNumber);
}

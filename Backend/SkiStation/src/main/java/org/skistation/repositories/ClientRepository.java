package org.skistation.repositories;

import org.skistation.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Represents a client repository interface extending JpaRepository.
 * It provides methods to perform queries on the client table in the database.
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>
{
    /**
     * Finds clients by their email address.
     *
     * @param emailAddress the email address to search for
     * @return a list of clients with the specified email address
     */
    List<Client> findByEmail(String emailAddress);

    /**
     * Finds clients by their phone number.
     *
     * @param phone the phone number to search for
     * @return a list of clients with the specified phone number
     */
    List<Client> findByPhone(Integer phone);

    /**
     * Finds clients by their email address and phone number.
     *
     * @param emailAddress the email address to search for
     * @param phone the phone number to search for
     * @return a list of clients with the specified email address and phone number
     */
    List<Client> findByEmailAndPhone(String emailAddress, Integer phone);
}
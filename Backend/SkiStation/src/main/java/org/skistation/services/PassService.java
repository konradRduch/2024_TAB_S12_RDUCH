package org.skistation.services;

import org.skistation.models.Client;
import org.skistation.models.Pass;
import org.skistation.repositories.PassRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Represents a pass service class.
 * It provides methods to perform operations on the pass table in the database.
 */
@Service
public class PassService
{
    /**
     * The pass repository to perform operations on the pass table in the database.
     */
    private final PassRepository passRepository;

    /**
     * Constructs a new pass service with the specified pass repository.
     *
     * @param passRepository the pass repository to perform operations on the pass table in the database
     */
    public PassService(PassRepository passRepository) {
        this.passRepository = passRepository;
    }

    /**
     * Retrieves a pass by their ID.
     *
     * @param id the ID of the pass to retrieve
     * @return an Optional containing the pass if found, or an empty Optional if not
     */
    public Optional<Pass> getPassById(Integer id) {
        return passRepository.findById(id);
    }

    /**
     * Modifies a pass in the database.
     *
     * @param pass the pass to modify
     * @return an Optional containing the modified pass if found, or an empty Optional if not
     */
    public Optional<Pass> modifyPass(Pass pass) {
        Optional<Pass> passOptional = passRepository.findById(pass.getId());
        if (passOptional.isPresent()) {
            passRepository.save(pass);
        }
        return passOptional;
    }

    /**
     * Saves a pass to the database.
     *
     * @param pass the pass to save
     * @return the saved pass
     */
    public Pass savePass(Pass pass) {
        return passRepository.save(pass);
    }

    /**
     * Deletes a pass from the database by their ID.
     *
     * @param id the ID of the pass to delete
     */
    public void deletePass(Integer id) {
        passRepository.deleteById(id);
    }

    /**
     * Retrieves all passes from the database.
     *
     * @return a list of all passes
     */
    public List<Pass> getAllPass() {
        return passRepository.findAll();
    }

    /**
     * Retrieves all passes by the specified order ID from the database.
     *
     * @param orderId the ID of the order
     * @return a list of all passes by the specified order ID
     */
    public List<Pass> getPassByOrderId(Integer orderId) {
        return passRepository.findByOrderId(orderId);
    }

    /**
     * Retrieves all passes by the specified price list ID from the database.
     *
     * @param priceListId the ID of the price list
     * @return a list of all passes by the specified price list ID
     */
    public List<Pass> getPassByPriceListId(Integer priceListId) {
        return passRepository.findByPriceListId(priceListId);
    }

    /**
     * Suspends a pass by their ID if the given email and phone match the client's.
     *
     * @param passId the ID of the pass to suspend
     * @param email  the email of the client
     * @param phone  the phone of the client
     * @return an Optional containing the suspended pass if found and suspended, or an empty Optional if not
     */
    public Optional<Pass> suspendPass(Integer passId, String email, Integer phone) {
        Optional<Pass> passToSuspend = getPassById(passId);
        if (passToSuspend.isPresent()) {
            Pass pass = passToSuspend.get();

            Client client = pass.getOrder().getClient();
            if (!client.getEmail().equals(email) || !client.getPhone().equals(phone)) {
                return Optional.empty();
            }

            if (pass.getSuspensionDate() == null) {
                pass.setSuspensionDate(LocalDateTime.now());
                pass.setActive(false);
                passRepository.save(pass);
            }
        }
        return passToSuspend;
    }

    /**
     * Resumes a pass by their ID if the given email and phone match the client's.
     *
     * @param passId the ID of the pass to resume
     * @param email  the email of the client
     * @param phone  the phone of the client
     * @return an Optional containing the resumed pass if found and resumed, or an empty Optional if not
     */
    public Optional<Pass> resumePass(Integer passId, String email, Integer phone) {
        Optional<Pass> passToResume = getPassById(passId);
        if (passToResume.isPresent()) {
            Pass pass = passToResume.get();

            Client client = pass.getOrder().getClient();
            if (!client.getEmail().equals(email) || !client.getPhone().equals(phone)) {
                return Optional.empty();
            }

            if (pass.getSuspensionDate() != null) {
                Duration timeLeft = Duration.between(pass.getSuspensionDate(), pass.getTimeEnd());
                pass.setTimeEnd(pass.getTimeEnd().plus(timeLeft));
                pass.setActive(true);
                pass.setSuspensionDate(null);
                passRepository.save(pass);
            }
        }
        return passToResume;
    }
}
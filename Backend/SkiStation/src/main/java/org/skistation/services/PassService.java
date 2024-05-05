package org.skistation.services;

import org.skistation.models.Pass;
import org.skistation.repositories.PassRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PassService
{
    private final PassRepository passRepository;

    public PassService(PassRepository passRepository) {
        this.passRepository = passRepository;
    }

    Optional<Pass> getPassById(Integer id) {
        return passRepository.findById(id);
    }

    Optional<Pass> modifyPass(Pass pass) {
        Optional<Pass> passOptional = passRepository.findById(pass.getId());
        if (passOptional.isPresent()) {
            passRepository.save(pass);
        }
        return passOptional;
    }

    Pass savePass(Pass pass) {
        return passRepository.save(pass);
    }

    void deletePass(Integer id) {
        passRepository.deleteById(id);
    }

    List<Pass> getAllPass() {
        return passRepository.findAll();
    }

    List<Pass> getPassByOrderId(Integer orderId) {
        return passRepository.findByOrderId(orderId);
    }

    List<Pass> getPassByPriceListId(Integer priceListId) {
        return passRepository.findByPriceListId(priceListId);
    }
}

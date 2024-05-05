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

    public Optional<Pass> getPassById(Integer id) {
        return passRepository.findById(id);
    }

    public Optional<Pass> modifyPass(Pass pass) {
        Optional<Pass> passOptional = passRepository.findById(pass.getId());
        if (passOptional.isPresent()) {
            passRepository.save(pass);
        }
        return passOptional;
    }

    public Pass savePass(Pass pass) {
        return passRepository.save(pass);
    }

    public void deletePass(Integer id) {
        passRepository.deleteById(id);
    }

    public List<Pass> getAllPass() {
        return passRepository.findAll();
    }

    public List<Pass> getPassByOrderId(Integer orderId) {
        return passRepository.findByOrderId(orderId);
    }

    public List<Pass> getPassByPriceListId(Integer priceListId) {
        return passRepository.findByPriceListId(priceListId);
    }
}

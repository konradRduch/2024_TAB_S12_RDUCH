package org.skistation.services;

import org.skistation.models.Pass;
import org.skistation.repositories.PassRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
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

    public Optional<Pass> suspendPass(Integer passId) {
        Optional<Pass> passToSuspend = getPassById(passId);
        if (passToSuspend.isPresent()) {
            Pass pass = passToSuspend.get();
            if (pass.getSuspensionDate() == null) {
                pass.setSuspensionDate(LocalDateTime.now());
                pass.setActive(false);
                passRepository.save(pass);
            }
        }
        return passToSuspend;
    }

    public Optional<Pass> resumePass(Integer passId) {
        Optional<Pass> passToResume = getPassById(passId);
        if (passToResume.isPresent()) {
            Pass pass = passToResume.get();
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

package org.skistation.services;

import org.skistation.models.SkiSchedule;
import org.skistation.repositories.SkiScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkiScheduleService
{
    private final SkiScheduleRepository skiScheduleRepository;

    public SkiScheduleService(SkiScheduleRepository skiScheduleRepository) {
        this.skiScheduleRepository = skiScheduleRepository;
    }

    Optional<SkiSchedule> getSkiScheduleById(Integer id) {
        return skiScheduleRepository.findById(id);
    }

    Optional<SkiSchedule> modifySkiSchedule(SkiSchedule skiSchedule) {
        Optional<SkiSchedule> skiScheduleOptional = skiScheduleRepository.findById(skiSchedule.getId());
        if (skiScheduleOptional.isPresent()) {
            skiScheduleRepository.save(skiSchedule);
        }
        return skiScheduleOptional;
    }

    SkiSchedule saveSkiSchedule(SkiSchedule skiSchedule) {
        return skiScheduleRepository.save(skiSchedule);
    }

    void deleteSkiSchedule(Integer id) {
        skiScheduleRepository.deleteById(id);
    }

    List<SkiSchedule> getAllSkiSchedules() {
        return skiScheduleRepository.findAll();
    }

    List<SkiSchedule> getSkiSchedulesByLiftId(Integer liftId) {
        return skiScheduleRepository.findByLiftId(liftId);
    }
}

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

    public Optional<SkiSchedule> getSkiScheduleById(Integer id) {
        return skiScheduleRepository.findById(id);
    }

    public Optional<SkiSchedule> updateSkiSchedule(SkiSchedule skiSchedule) {
        Optional<SkiSchedule> skiScheduleOptional = skiScheduleRepository.findById(skiSchedule.getId());
        if (skiScheduleOptional.isPresent()) {
            skiScheduleRepository.save(skiSchedule);
        }
        return skiScheduleOptional;
    }

    public SkiSchedule saveSkiSchedule(SkiSchedule skiSchedule) {
        return skiScheduleRepository.save(skiSchedule);
    }

    public void deleteSkiSchedule(Integer id) {
        skiScheduleRepository.deleteById(id);
    }

    public List<SkiSchedule> getAllSkiSchedules() {
        return skiScheduleRepository.findAll();
    }

    public List<SkiSchedule> getSkiSchedulesByLiftId(Integer liftId) {
        return skiScheduleRepository.findByLiftId(liftId);
    }
}

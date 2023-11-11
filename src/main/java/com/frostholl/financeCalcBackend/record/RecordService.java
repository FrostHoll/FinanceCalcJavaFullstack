package com.frostholl.financeCalcBackend.record;

import com.frostholl.financeCalcBackend.user.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecordService {

    private final RecordRepository recordRepository;

    public RecordService(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    public List<Record> getUserRecords(User user) {
        Optional<List<Record>> records = recordRepository.findByUserId(user.getId());
        return records.orElse(null);
    }
}

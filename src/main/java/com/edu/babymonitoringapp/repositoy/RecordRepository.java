package com.edu.babymonitoringapp.repositoy;

import com.edu.babymonitoringapp.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Record, Long> {
}

package com.edu.babymonitoringapp.service;

import com.edu.babymonitoringapp.dto.RecordDto;
import com.edu.babymonitoringapp.model.Record;
import java.util.List;

public interface RecordService {
    Record createRecord(RecordDto recordDto, Long accountId);
    RecordDto getRecordById(Long id);
    List<RecordDto> getAllRecords();
    Record updateRecord(Long id, RecordDto recordDto);
    void deleteRecord(Long id);

}

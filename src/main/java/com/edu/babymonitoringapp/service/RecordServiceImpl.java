package com.edu.babymonitoringapp.service;

import com.edu.babymonitoringapp.dto.RecordDto;
import com.edu.babymonitoringapp.model.Record;
import com.edu.babymonitoringapp.repositoy.RecordRepository;
import java.util.List;
import java.util.Optional;

public class RecordServiceImpl implements RecordService{

    private RecordRepository recordRepository;
    @Override
    public Record createRecord(RecordDto recordDto) {
        Record record = recordDto.toRecord();
        return recordRepository.save(record);
    }

    @Override
    public RecordDto getRecordById(Long id) {
        Optional<Record> record = recordRepository.findById(id);
        return record.map(RecordDto::new).orElse(null);
    }

    @Override
    public List<RecordDto> getAllRecords() {
        List<Record> records= recordRepository.findAll();
        return RecordDto.toListRecordDto(records);
    }

    @Override
    public Record updateRecord(Long id, RecordDto recordDto) {
        Optional<Record> record = recordRepository.findById(id);
        if (record.isPresent()) {
            Record record1 = record.get();
            record1.setMl(recordDto.getMl());
            record1.setDuration(recordDto.getDuration());
            record1.setDate(java.sql.Date.valueOf(recordDto.getDate()));
            record1.setAccount(recordDto.getAccount());
            return recordRepository.save(record1);
        }
        else {
            return null;
        }
    }

    @Override
    public void deleteRecord(Long id) {
        recordRepository.deleteById(id);
    }
}

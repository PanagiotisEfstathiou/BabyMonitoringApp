package com.edu.babymonitoringapp.dto;

import com.edu.babymonitoringapp.model.Account;
import com.edu.babymonitoringapp.model.Record;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecordDto {
    private double ml;
    private double duration;
    private LocalDateTime date;

    private Account account;

    public RecordDto(Record record) {
        this.ml = record.getMl();
        this.duration = record.getDuration();
        this.date = record.getDate();
        this.account = record.getAccount();
    }

    public static List<RecordDto> toListRecordDto(List<Record> records) {
        List<RecordDto> recordDtos = new ArrayList<>();
        for (Record record : records) {
            recordDtos.add(new RecordDto(record));
        }
        return recordDtos;
    }

    public Record toRecord() {
        Record record = new Record();
        record.setMl(this.ml);
        record.setDuration(this.duration);
        record.setDate(this.date);
        record.setAccount(this.account);
        return record;
    }
}

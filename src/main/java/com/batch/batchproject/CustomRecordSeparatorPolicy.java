package com.batch.batchproject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.item.file.separator.SimpleRecordSeparatorPolicy;

public class CustomRecordSeparatorPolicy extends SimpleRecordSeparatorPolicy {

    @Override
    public boolean isEndOfRecord(String line) {
        if (StringUtils.isBlank(line)) {
            return false;
        }
        return super.isEndOfRecord(line);
    }

    @Override
    public String postProcess(String record) {
        if (StringUtils.isBlank(record)) {
            return null;
        }
        return super.postProcess(record);
    }
}
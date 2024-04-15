package com.nju.edu.gtms.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileStoreVO {

    private String thesis_id;

    private String type;

    public String getThesis_id() {
        return thesis_id;
    }

    public String getType() {
        return type;
    }
}

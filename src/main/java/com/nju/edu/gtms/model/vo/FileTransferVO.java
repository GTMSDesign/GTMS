package com.nju.edu.gtms.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileTransferVO {

    private String id = "000";

    private String type;

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

}

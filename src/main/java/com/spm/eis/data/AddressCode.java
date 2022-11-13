package com.spm.eis.data;

import lombok.Data;

@Data
public class AddressCode {
    private Long code;
    private String name;
    private int level;
    private Long pcode;
}

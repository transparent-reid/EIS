package com.spm.eis.data;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("address_code")
public class AddressCode {

    @TableId("code")
    @TableField("code")
    private Long code;
    @TableField("name")
    private String name;
    @TableField("level")
    private int level;
    @TableField("pcode")
    private Long pcode;
}

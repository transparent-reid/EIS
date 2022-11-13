package com.spm.eis.data;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("code_info")
public class CodeInfo {
    private Long id;
    private String code;
    private String filePath;
}

package com.spm.eis.utils;

import com.spm.eis.data.CodeInfo;
import com.spm.eis.mapper.CodeInfoMapper;


import javax.annotation.PostConstruct;

import java.io.File;



public class BackFile {
    public String getCode() {
        return code;
    }

    private String code;
    private CodeInfoMapper codeInfoMapper;
    private static BackFile backFile;
    @PostConstruct
    public void init(){
        backFile = this;
        backFile.codeInfoMapper = this.codeInfoMapper;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public File searchFile(String code){
        CodeInfo codeInfo = backFile.codeInfoMapper.selectById(code);
        return new File(codeInfo.getFilePath());
    }

}

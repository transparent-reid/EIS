package com.spm.eis.controller;

import com.spm.eis.data.CodeInfo;
import com.spm.eis.mapper.CodeInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/upload")
@CrossOrigin
public class UploadController {

    private CodeInfo codeInfo = new CodeInfo();

    @Autowired(required = false)
    private CodeInfoMapper codeInfoMapper;

    @PostMapping(value = "/code", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String uploadCode(@RequestBody Map<String, Object> params){
        codeInfo.setId(new Random().nextLong());
        codeInfo.setCode((String) params.get("code"));
        codeInfoMapper.insert(codeInfo);
        return "upload success";
    }
}

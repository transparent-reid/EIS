package com.spm.eis.controller;

import com.spm.eis.data.CodeInfo;
import com.spm.eis.mapper.CodeInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/upload")
@CrossOrigin
public class UploadController {

    private CodeInfo codeInfo = new CodeInfo();

    @Autowired(required = false)
    private CodeInfoMapper codeInfoMapper;

    @PostMapping("/code")
    public String uploadCode(@RequestParam Map<String, String> params){
        codeInfo.setId(new Random().nextLong());
        codeInfo.setCode(params.get("code"));
        codeInfoMapper.insert(codeInfo);
        return "upload success";
    }
}

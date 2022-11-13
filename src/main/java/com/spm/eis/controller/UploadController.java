package com.spm.eis.controller;

import com.spm.eis.data.CodeInfo;
import com.spm.eis.mapper.CodeInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Random;

@RestController
@RequestMapping("/upload")
public class UploadController {

    private CodeInfo codeInfo = new CodeInfo();

    @Autowired(required = false)
    private CodeInfoMapper codeInfoMapper;

    @PostMapping("/code")
    public void uploadCode(@RequestParam("code") String code, HttpServletResponse response){
        codeInfo.setId(new Random().nextLong());
        codeInfo.setCode(code);
        codeInfoMapper.insert(codeInfo);
        response.setStatus(200);
    }
}

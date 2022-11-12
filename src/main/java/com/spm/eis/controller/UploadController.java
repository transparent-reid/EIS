package com.spm.eis.controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/upload")
public class UploadController {
    @PostMapping("/code")
    public void uploadCode(@RequestParam("code") String code, HttpServletResponse response){
        /*
        将code存入数据库
         */
        response.setStatus(200);
    }
}

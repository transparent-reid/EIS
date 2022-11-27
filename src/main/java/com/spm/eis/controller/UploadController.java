package com.spm.eis.controller;

import com.spm.eis.data.CodeInfo;
import com.spm.eis.mapper.CodeInfoMapper;
import com.spm.eis.service.ImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static java.lang.StrictMath.abs;

@RestController
@RequestMapping("/upload")
@CrossOrigin
public class UploadController {

    private CodeInfo codeInfo = new CodeInfo();

    @Autowired(required = false)
    private CodeInfoMapper codeInfoMapper;

    @Autowired
    private ImportService importService;

    @PostMapping(value = "/code", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String uploadCode(@RequestBody Map<String, Object> params){
        codeInfo.setId(abs(new Random().nextLong()));
        codeInfo.setCode((String) params.get("code"));
        codeInfoMapper.insert(codeInfo);
        return "upload success";
    }

    @PostMapping(value = "/file/excel")
    public String uploadExcel(@RequestParam("file") MultipartFile file) throws Exception {
        if(file.isEmpty()) {
            return "文件不能为空！";
        }
        InputStream inputStream = file.getInputStream();
        List<List<Object>> list = importService.getListByExcel(inputStream, file.getOriginalFilename());
        inputStream.close();

        for(int i = 0; i < list.size(); i++) {
            codeInfo.setId(abs(new Random().nextLong()));
            codeInfo.setCode((String) list.get(i).get(0));
            codeInfo.setText((String) list.get(i).get(1));
            codeInfoMapper.insert(codeInfo);
        }
        return "上传成功！";
    }
}

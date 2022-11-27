package com.spm.eis.controller;

import com.spm.eis.utils.Decoding;
import com.spm.eis.data.CodeInfo;
import com.spm.eis.mapper.CodeInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/data")
@CrossOrigin
public class DataController {

    @Autowired(required = false)
    private CodeInfoMapper codeInfoMapper;

    @GetMapping("/all")
    public List<HashMap<String, String>> getAllData() {
        List<CodeInfo> codeInfos = codeInfoMapper.selectList(null);
        List<HashMap<String, String>> output = new LinkedList<>();
        for(CodeInfo info: codeInfos){
            Decoding decoding = new Decoding();
            decoding.decoding(info.getCode());
            HashMap<String, String> cell = new HashMap<>(decoding.getTextInfo());
            cell.put("text", info.getText());
            cell.put("media", info.getFilePath());
            output.add(cell);
        }
        return output;
    }
}

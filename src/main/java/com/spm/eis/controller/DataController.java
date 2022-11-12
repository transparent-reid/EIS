package com.spm.eis.controller;

import com.spm.eis.Decoding;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController("/data")
public class DataController {
    Decoding decoding = new Decoding();

    @GetMapping("/{code}")
    public HashMap<String, String> getData(@PathVariable("code") String code) {
        decoding.decoding(code);
        return decoding.getTextInfo();
    }
}

package com.spm.eis.controller;


import com.spm.eis.utils.BackFile;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/file")
@CrossOrigin
public class FileController {


    @RequestMapping(value = "/downloadfile/{code}",method = RequestMethod.GET)
    public void downloadFile(@PathVariable("code") String code, HttpServletResponse response) throws IOException {
        BackFile backFile = new BackFile();
        backFile.setCode(code);
        File file = backFile.searchFile(backFile.getCode());
        FileInputStream fis = null;
        ServletOutputStream gos = null;
        try {
            String fileName = file.getName();
            response.setHeader("Content-Disposition", "attachment;filename="+fileName);
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            fis  = new FileInputStream(file);
            gos = response.getOutputStream();
            int len;
            byte[] buffer = new byte[1024];
            while ((len = fis.read(buffer)) != -1) {
                gos.write(buffer,0,len);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            assert fis != null;
            assert gos != null;
            fis.close();
            gos.flush();
            gos.close();
        }
    }

}

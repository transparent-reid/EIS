package com.spm.eis.service;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImportService {
    public List getListByExcel(InputStream in, String fileName) throws Exception {
        List list = new ArrayList<>();
        //创建excel表格
        Workbook workbook = this.getWorkbook(in, fileName);
        if(null == workbook) {
            throw new Exception("创建Excel工作簿为空！");
        }
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;

        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            sheet = workbook.getSheetAt(i);
            if(sheet == null) {
                continue;
            }
            for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
                row = sheet.getRow(j);
                if(row == null) {
                    continue;
                }
                List<Object> li = new ArrayList<>();
                for (int k = row.getFirstCellNum(); k < row.getLastCellNum(); k++) {
                    cell = row.getCell(k);
                    li.add(new DataFormatter().formatCellValue(cell));
                }
                list.add(li);
            }
        }
        workbook.close();
        return list;
    }

    public Workbook getWorkbook(InputStream inStr, String filename) throws Exception {
        Workbook workbook = null;
        String fileType = filename.substring(filename.lastIndexOf("."));
        if(".xls".equals(fileType)) {
            workbook = new HSSFWorkbook(inStr);
        }
        else if(".xlsx".equals(fileType)) {
            workbook = new XSSFWorkbook(inStr);
        }
        else {
            throw new Exception("请上传文件！");
        }
        return workbook;
    }
}

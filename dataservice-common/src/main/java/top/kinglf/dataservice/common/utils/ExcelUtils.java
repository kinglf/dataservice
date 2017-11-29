package top.kinglf.dataservice.common.utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.format.CellFormatType;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import top.kinglf.dataservice.common.TableField;
import top.kinglf.dataservice.common.model.Car;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/8.
 */
public class ExcelUtils {
    private static final int MAX_ROWS_EVERY_SHEET = 10 * 10000;


    /**
     * 导入
     *
     * @param inputStream Excel文件(路径+文件)
     * @return
     */
    public static List<Map> importExcel(InputStream inputStream) {
        List<Map> dataList = new ArrayList<Map>();
        try {
            XSSFWorkbook workBook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workBook.getSheetAt(0);
            if (sheet != null) {
                XSSFRow keys = sheet.getRow(0);
                for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                    Map data = new HashMap();
                    XSSFRow rows = sheet.getRow(i);
                    for (int j = 0; j < keys.getPhysicalNumberOfCells(); j++) {
                        String key = keys.getCell(j).toString().replaceAll("[\\s\\u00A0]+$", "");
                        String value = rows.getCell(j).toString().replaceAll("[\\s\\u00A0]+$", "");
                        data.put(key, value);
                    }
                    dataList.add(data);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataList;
    }

    /**
     * 将list导出到Excel文件,文件不存在自动创建
     * * @param fileName
     *
     * @param list
     * @return
     */
    public static boolean saveBeanList2Excel(String fileName, List list) {
        int i = 1;
        while (true) {
            if (list.size() < MAX_ROWS_EVERY_SHEET) {
                fillData2workBook(fileName, list);
                break;
            } else {
                String newFileName = fileName.replace(".xlsx", "-" + i + ".xlsx");
                List list1 = list.subList(0, MAX_ROWS_EVERY_SHEET);
                fillData2workBook(newFileName, list1);
                list.subList(0, MAX_ROWS_EVERY_SHEET).clear();
                i++;
            }

        }
        return true;

    }

    private static boolean fillData2workBook(String fileName, List dataList) {
        File xlsFile = new File(fileName);
        if (!xlsFile.getParentFile().exists()) {
            xlsFile.getParentFile().mkdirs();
        }
        if (xlsFile.isDirectory()) {
            return false;
        }
        Field[] fields = dataList.get(0).getClass().getDeclaredFields();
        List<Field> fieldList = new ArrayList<>();
        List<String> excelHeaderList = new ArrayList<>();
        for (Field field : fields) {
            if (field.isAnnotationPresent(TableField.class)) {
                field.setAccessible(true);
                fieldList.add(field);
                TableField fieldAnnotation = field.getAnnotation(TableField.class);
                String field1 = fieldAnnotation.field();
                excelHeaderList.add(field1);
            }
        }
        XSSFWorkbook workBook = new XSSFWorkbook();
        Sheet sheet = workBook.createSheet();
        Row row = sheet.createRow(0);
        for (int i = 0; i < excelHeaderList.size(); i++) {
            row.createCell(i).setCellType(HSSFCell.CELL_TYPE_STRING);
            row.getCell(i).setCellValue(excelHeaderList.get(i));
        }
        for (int i = 0; i < dataList.size(); i++) {
            Object obj = dataList.get(i);
            Row row1 = sheet.createRow(i + 1);
            for (int j = 0; j < fieldList.size(); j++) {
                row1.createCell(j).setCellType(HSSFCell.CELL_TYPE_STRING);
                try {
                    Object o = fieldList.get(j).get(obj);
                    row1.getCell(j).setCellValue(String.valueOf(o));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            FileOutputStream fos = new FileOutputStream(xlsFile);
            workBook.write(fos);
            fos.flush();
            fos.close();
//            workBook.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;


    }
}


package com.firnice.test.csv;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileWriter;
import cn.hutool.core.lang.Console;
import cn.hutool.core.text.csv.CsvData;
import cn.hutool.core.text.csv.CsvReader;
import cn.hutool.core.text.csv.CsvRow;
import cn.hutool.core.text.csv.CsvUtil;

import java.util.List;

public class CsvTest {

    public static void main(String[] args) {
        CsvReader reader = CsvUtil.getReader();
//从文件中读取CSV数据
        CsvData data = reader.read(FileUtil.file("/Users/b612/Downloads/ddd.csv"));
        List<CsvRow> rows = data.getRows();
        System.out.println(rows.size());
//遍历行
        FileWriter writer = new FileWriter("/Users/b612/Downloads/d2.txt");


        for (int i = 0; i < rows.size(); i++) {
            //getRawList返回一个List列表，列表的每一项为CSV中的一个单元格（既逗号分隔部分）
//            Console.log(rows.get(i).getRawList());
//            System.out.println(rows.get(i).get(0));

            writer.append(rows.get(i).get(0)+"\t\n");
//            if (i > 100) {
//                break;
//            }
        }
        System.out.println("done!!");

    }
}

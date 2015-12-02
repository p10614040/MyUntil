/**
 *
 */
package com.iceblock.myuntil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Oralce 向 MySQL 迁移的 SQL 语句转换
 * <ul>
 * <li>Oralce : MySQL</li>
 * <li>number(<11)> ： int</li>
 * <li>number(>11) : bigint</li>
 * <li>varchar2(<255)> : varchar</li>
 * <li>varchar2(>255) : text</li>
 * <li>clob : text</li>
 * <li>date : datetime</li>
 * <li>number(10,2) : decimal</li>
 * <li></li>
 * </ul>
 *
 * @author yan.liang@ustcinfo.com
 */
public class OralceToMysqlSqlTransfor {

    /**
     * @author yan.liang
     * @date 2015年10月19日 下午5:43:36
     * @Description SQL语句转换
     */
    public static void transforSql(String oracleFile, String mysqlFileName) {
        File file = new File(oracleFile);
        BufferedReader reader = null;
        FileWriter fileWritter = null;
        try {
            File mysqlFile = new File(mysqlFileName);

            // if file doesnt exists, then create it
            if (!mysqlFile.exists()) {
                mysqlFile.createNewFile();
            }
            fileWritter = new FileWriter(mysqlFileName, true);
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                System.out.println("line " + line + ": " + tempString);
                line++;
                bufferWritter.write(tempString);
                bufferWritter.newLine();
            }
            reader.close();
            bufferWritter.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    public static void main(String[] args) {
        OralceToMysqlSqlTransfor.transforSql(
                "D:/我的文档/我的文档/工作资料/网管/适配平台/OracleToMySQL/UAP.sql",
                "D:/我的文档/我的文档/工作资料/网管/适配平台/OracleToMySQL/UAP_MYSQL.sql");
    }
}

package steps;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class ExelTest {
    public static void main(String[] args) {


        String path = "/Users/phillydisteak/IdeaProjects/MindtekCucumberAutomation1/src/test/resources/TestData/TestData1.xlsx";


        {
            try {
                FileInputStream input = new FileInputStream(path);
                Workbook workbook = new XSSFWorkbook(input);
                Sheet sheet1 = workbook.getSheet("Sheet1");
                String firsName = sheet1.getRow(1).getCell(0).toString();
                System.out.println(firsName);

                firsName=sheet1.getRow(2).getCell(0).toString();
                System.out.println(firsName);

                sheet1.getRow(2).getCell(1).setCellValue("Harsh");

                sheet1.createRow(3).createCell(0).setCellValue("Kim");
                sheet1.getRow(3).createCell(1).setCellValue("Yan");

                int numbOfRows=sheet1.getPhysicalNumberOfRows();
                System.out.println(numbOfRows);

                int numberOfCellsRow1=sheet1.getRow(1).getPhysicalNumberOfCells();
                System.out.println(numberOfCellsRow1);

                for (int i=0;i<numberOfCellsRow1;i++){
                    System.out.println(sheet1.getRow(1).getCell(i).toString()+",");
                }





                FileOutputStream output=new FileOutputStream(path);
                workbook.write(output);

                System.out.println(sheet1.getRow(2).getCell(1).toString());

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

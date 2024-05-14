package inputOutput;

import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.write.*;
import org.junit.Test;

import java.io.File;
import java.util.Date;


public class InputOutputTest {


    @Test
    public void testIn() throws Exception {

        //1,读取excel文件
        Workbook workbook = Workbook.getWorkbook(new File("inputsource.xls"));
        //2,获取sheet页
        Sheet sheet = workbook.getSheet(0);
        //3,获取excel中的列数
        int columnCount = sheet.getColumns();
        //4,获取行数
        int rowCount = sheet.getRows();
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < columnCount; col++) {
                System.out.print(sheet.getCell(col, row).getContents() + "\t");
            }
            System.out.println();
        }
        workbook.close();
    }

    @Test
    public void testOut() throws Exception {

        //1,创建excel的工作表,放入到当前项目中的out.xls
        WritableWorkbook wb = Workbook.createWorkbook(new File("ouput.xls"));

        //2,创建工作本sheet
        WritableSheet sheet = wb.createSheet("第一表单", 0);
            /*//设置宽和高
            sheet.setColumnView(0, 50);
            sheet.setRowView(0, 500);
            //合并单元格
            sheet.mergeCells(0, 0, 5, 5);*/
        //合并之后内容由左上角的单元格决定
        //3,创建单元格,并添加
        /*Label cell = new Label(0,0,"第一个单元格");*/
        DateFormat dateFormat = new DateFormat("yyyy-MM-dd HH:mm:ss");
        WritableCellFormat wcf = new WritableCellFormat(dateFormat);

        //设置水平居中
        wcf.setAlignment(Alignment.LEFT);
        DateTime dt = new DateTime(0, 0, new Date(), wcf);
        sheet.addCell(dt);
        //设置垂直居中
        //4,将表格写出,关闭资源
        wb.write();
        wb.close();

    }

}

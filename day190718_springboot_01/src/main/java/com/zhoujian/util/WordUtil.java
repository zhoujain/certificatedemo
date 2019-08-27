package com.zhoujian.util;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.rtf.RtfWriter2;
import org.apache.poi.hssf.util.AreaReference;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
public class WordUtil {
    public static void excelToWord(String wordPath,String excelPath,String tableName){
        FileOutputStream out = null;
        try{
            // 创建word文档,并设置纸张的大小
            out = new FileOutputStream(new File(wordPath));
            Document document = new Document(PageSize.A4);//word对像 document
            RtfWriter2.getInstance(document, out);

            document.open();

            Workbook workbook = ReadExcelUtil.createWorkbook(excelPath);
            Sheet sheet = workbook.getSheetAt(0);
            int namedCelldx = workbook.getNameIndex(tableName);
            org.apache.poi.ss.usermodel.Name name = workbook.getNameAt(namedCelldx);
            org.apache.poi.ss.util.AreaReference[] areaReferences = org.apache.poi.ss.util.AreaReference.generateContiguous(name.getRefersToFormula());
            Table table =null;
            for(int i =0;i<areaReferences.length;i++){
                int a =areaReferences[i].getFirstCell().getRow();//7
                int b =areaReferences[i].getFirstCell().getCol();//7
                int a1 = areaReferences[i].getLastCell().getRow();//10
                int b1 =areaReferences[i].getLastCell().getCol();//19
                int a2 =a1-a+1;
                int b2 =b1-b+1;
                table = new Table(b2, a2);// 最后一行和一列(5,10)
                //Table table = new Table(3, 8);
                table.setBorderWidth(1);
                table.setBorderColor(Color.BLACK);
                table.setPadding(0);
                table.setSpacing(0);

                for(int k=a;k<=a1;k++){
                    org.apache.poi.ss.usermodel.Row row = sheet.getRow(k);
                    if(row==null) {
                        for(int x=b;x<=b1;x++){
                            com.lowagie.text.Cell cell1 = new com.lowagie.text.Cell(new Paragraph(" "));
                            cell1.setBorder(0);
                            table.addCell(cell1);
                        }
                        continue;
                    }
                    for(int x=b;x<=b1;x++){
                        //首行不读取​
                        if(row.getCell(x)==null||row.getCell(x).equals("")){
                            com.lowagie.text.Cell cell1 = new com.lowagie.text.Cell(new Paragraph(" "));
                            cell1.setBorder(0);
                            table.addCell(cell1);
                            continue;
                        }
                        //获取样式
                        Cell cell = row.getCell(x);
                        CellStyle cellStyle = cell.getCellStyle();
                        //填充色
                        //short foregroundColorIndex = cellStyle.getFillForegroundColor();
                        //字体
                        short fontIndex = cellStyle.getFontIndex();
                        int colorIndex = workbook.getFontAt(fontIndex).getColor();
                        //值
                        String str = ReadExcelUtil.getCellValue(cell);
                        //设置新的cell
                        Color color = createColorByIndex(colorIndex);
                        Font font = createFont(color);
                        com.lowagie.text.Cell cell1 = new com.lowagie.text.Cell(new Paragraph(str,font));
                        //设置居中和边框
                        short alignment = cellStyle.getAlignment();
                        short borderBottom = cellStyle.getBorderBottom();
                        short borderleft = cellStyle.getBorderLeft();
                        short borderRight = cellStyle.getBorderRight();
                        short borderTop = cellStyle.getBorderTop();
                        if(borderBottom ==0&&borderleft==0&&borderRight==0&&borderTop==0){
                            cell1.setBorder(0);
                        }
                        cell1.setHorizontalAlignment(alignment-1);
                        table.addCell(cell1);
                    }
                }

//                org.apache.poi.ss.util.CellReference[] cellReferences = areaReferences[i].getAllReferencedCells();
//                for(int j=0;j<cellReferences.length;j++){
//                    Sheet s = workbook.getSheet(cellReferences[j].getSheetName());
//                    int a = s.getLastRowNum();
//                    int b = s.getFirstRowNum();
//                    org.apache.poi.ss.usermodel.Row r =s.getRow(cellReferences[j].getRow());
//                    int c1 =r.getLastCellNum();
//                    int d1 = r.getFirstCellNum();
//                    Cell c = r.getCell(cellReferences[j].getCol());
//                    String str = c.getStringCellValue();
//                }
            }
            //document.add(new Paragraph("用户信息"));
            document.add(table);
//            //工作表对象
//            Sheet sheet = workbook.getSheetAt(0);
//            //Table table = new Table(5,10);
//            //循环读取表格数据​
//            for(org.apache.poi.ss.usermodel.Row row:sheet) {
//                //首行不读取​
//
//                for (int i=0;i<5;i++) {
//                    if(row.getCell(i)==null||row.getCell(i).equals("")){
//                        com.lowagie.text.Cell cell1 = new com.lowagie.text.Cell(new Paragraph(" "));
//                        cell1.setBorder(0);
//                        table.addCell(cell1);
//                        continue;
//                    }
//                    //获取样式
//                    Cell cell = row.getCell(i);
//                    CellStyle cellStyle = cell.getCellStyle();
//                    //填充色
//                    //short foregroundColorIndex = cellStyle.getFillForegroundColor();
//                    //字体
//                    short fontIndex = cellStyle.getFontIndex();
//                    int colorIndex = workbook.getFontAt(fontIndex).getColor();
//                    //值
//                    String str = ReadExcelUtil.getCellValue(cell);
//                    //设置新的cell
//                    Color color = createColorByIndex(colorIndex);
//                    Font font = createFont(color);
//                    com.lowagie.text.Cell cell1 = new com.lowagie.text.Cell(new Paragraph(str,font));
//                    //设置居中和边框
//                    short alignment = cellStyle.getAlignment();
//                    short borderBottom = cellStyle.getBorderBottom();
//                    short borderleft = cellStyle.getBorderLeft();
//                    short borderRight = cellStyle.getBorderRight();
//                    short borderTop = cellStyle.getBorderTop();
//                    if(borderBottom ==0&&borderleft==0&&borderRight==0&&borderTop==0){
//                        cell1.setBorder(0);
//                    }
//                    cell1.setHorizontalAlignment(alignment-1);
//                    table.addCell(cell1);
//                }
//                //System.out.println();
//            }
//            //table.addCell("111");
//            document.add(new Paragraph("用户信息"));
//            document.add(table);

            document.close();
        }catch(Exception e){
            e.printStackTrace();

        }finally {
            try {

                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 通过Excel表格中颜色index获取AWT中Color
     *
     * @param colorIndex
     * @return
     */
    private static Color createColorByIndex(int colorIndex) {
        Map<Integer, HSSFColor> indexHash = HSSFColor.getIndexHash();
        HSSFColor hssfColor = indexHash.get(colorIndex);
        if (hssfColor != null) {
            short[] triplet = hssfColor.getTriplet();
            Color color = new Color(triplet[0], triplet[1], triplet[2]);
            return color;
        }
        return null;

    }

    /**
     * 根据颜色创建字体
     *
     * @param color
     * @return
     * @throws DocumentException
     * @throws IOException
     */
    private static com.lowagie.text.Font createFont(Color color) throws DocumentException, IOException {
        BaseFont bfSun = BaseFont.createFont("c:\\WINDOWS\\fonts\\SIMSUN.TTC,1", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);

        com.lowagie.text.Font font = new com.lowagie.text.Font(bfSun, 11);
        font.setColor(color);
        return font;
    }
}

package cif.excel;

import cif.adminstration.utiles.FieldDescriptor;
import cif.adminstration.utiles.ValueDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class XLSDocument 
{
    XSSFWorkbook wb;
    public XLSDocument()
    {
        wb = new XSSFWorkbook();
    }

    public void addSheet(String name)
    {
        wb.createSheet(name);
    }

    public void WriteRow(int fila,String sheet,ValueDescriptor[] v_desc)
    {
        XSSFSheet hoja = wb.getSheet(sheet);
        Row row = hoja.createRow(fila);
        for(int i=0; i<v_desc.length; i++)
        {
            Cell cell = row.createCell(i+1);
            if(v_desc[i] != null)
            {
                if(v_desc[i].getType() == FieldDescriptor.NUMBER)
                {
                    cell.setCellValue(Double.parseDouble(v_desc[i].getValue().toString()));
                }
                else
                {
                    cell.setCellValue(v_desc[i].getValue().toString());
                }
            }
            SetBorder(cell);
        }
    }

    public void SetColumnNames(int fila,String sheet,String[] names)
    {
        XSSFSheet hoja = wb.getSheet(sheet);
        Row row = hoja.createRow(fila);
        Cell cell;
        for(int i=0; i<names.length; i++)
        {
            cell = row.createCell(i+1);
            row.createCell(i+1).setCellValue(names[i]);
            SetBorder(cell);
//			SetBorderColor(cell,IndexedColors.DARK_GREEN.getIndex());
        }
    }

    public void Save(String file) throws IOException
    {
        FileOutputStream fileOut = new FileOutputStream(file);
        wb.write(fileOut);
        fileOut.close();
    }

    public void MergeRow(int fila,String sheet, Object value,int longitud)
    {
        XSSFSheet hoja = wb.getSheet(sheet);
        Row row = hoja.createRow(fila);
        Cell cell = row.createCell(1);
        cell.setCellValue(value.toString());
        hoja.addMergedRegion(new CellRangeAddress(
                1, //first row (0-based)
                1, //last row  (0-based)
                1, //first column (0-based)
                longitud  //last column  (0-based)
        ));
    //    Cell c = row.getCell(1);
        SetBorder(cell);
    //    SetBorderColor(cell,color);
        for(int i=0; i<longitud-1; i++)
        {
            Cell c = row.createCell(2+i);
            SetBorder(c);
    //    	SetBorderColor(cell,color);
        }
        CellStyle cellStyle = cell.getCellStyle();
        cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        cell.setCellStyle(cellStyle);
    }

    public void WriteTotalRow(int fila,String sheet,ValueDescriptor[] v_desc)
    {
        XSSFSheet hoja = wb.getSheet(sheet);
        Row row = hoja.createRow(fila);
        row.createCell(0).setCellValue("Total");
        for(int i=0; i<v_desc.length; i++)
        {
            Cell cell = row.createCell(i+1);
            if(v_desc[i] != null)
            {
                cell.setCellValue(Double.parseDouble(v_desc[i].getValue().toString()));
            }
            SetBorder(cell);
        }
    }

    public void SetBorder(Cell cell)
    {
        CellStyle style = wb.createCellStyle();
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setBorderTop(CellStyle.BORDER_THIN);
        cell.setCellStyle(style);
    }

/*	public void SetBorderColor(Cell cell,short color)
    {
            CellStyle style = cell.getCellStyle();
        style.setTopBorderColor(color);
        style.setBottomBorderColor(color);
        style.setLeftBorderColor(color);
        style.setRightBorderColor(color);
    //    style.setFillPattern(CellStyle.SPARSE_DOTS);
        cell.setCellStyle(style);
    }*/
}

package adri.suivi_courrier.data.export;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import adri.suivi_courrier.data.entity.CourriersRecu;

import org.springframework.stereotype.Component;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;


@Component
public class ExcelExporter {

    public byte[] exportToExcel(List<CourriersRecu> courriers,String departementName) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Courriers");
    
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Document");
        headerRow.createCell(2).setCellValue("Description");
        headerRow.createCell(3).setCellValue("Expeditor");
        headerRow.createCell(4).setCellValue("Date of treatment");
        headerRow.createCell(5).setCellValue("Receive by");
        headerRow.createCell(6).setCellValue("Departement");
    
        int rowIndex = 1;
        for (CourriersRecu courrier : courriers) {
            Row row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(courrier.getId_courrier());
            row.createCell(1).setCellValue(courrier.getNom_courrier());
            row.createCell(2).setCellValue(courrier.getDescription());
            row.createCell(3).setCellValue(courrier.getExpediteur());
            
            // Définissez la date en utilisant une chaîne de caractères
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String dateStr = sdf.format(courrier.getDate_traitement());
            Cell cell = row.createCell(4);
            cell.setCellValue(dateStr);
            CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setDataFormat(workbook.createDataFormat().getFormat("dd/MM/yyyy"));
            cell.setCellStyle(cellStyle);
    
            row.createCell(5).setCellValue(courrier.getNom_utilisateur());
            row.createCell(6).setCellValue(departementName);
            rowIndex++;
        }
    
        sheet.setColumnWidth(4, 30 * 256);
    
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        workbook.write(bos);
        bos.close();
    
        return bos.toByteArray();
    }

}

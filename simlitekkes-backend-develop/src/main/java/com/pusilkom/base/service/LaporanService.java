package com.pusilkom.base.service;

import com.pusilkom.base.dto.LaporanAnggaranDTO;
import com.pusilkom.base.dto.PenelitianDTO;
import com.pusilkom.base.model.mapper.PenelitianMapper;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LaporanService {

    @Autowired
    PenelitianMapper penelitianMapper;

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public List<String> getTahunUsulanForLaporanAnggaran(Integer idOrganisasi) {
        List<String> tahunUsulan = new ArrayList<String>();
        List<PenelitianDTO> penelitianList = penelitianMapper.getTahunUsulanForLaporanAnggaran(idOrganisasi);
        for(PenelitianDTO penelitian: penelitianList) {
            tahunUsulan.add(penelitian.getTahunUsulan());
        }
        return tahunUsulan;
    }

    public XSSFWorkbook generateLaporanAnggaran(String tahunUsulan, Integer idOrganisasi) {
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet(tahunUsulan);

        CellStyle style = workbook.createCellStyle();
        style.setWrapText(true);
        style.setVerticalAlignment(VerticalAlignment.TOP);

        CellStyle styleForNumeric = workbook.createCellStyle();
        styleForNumeric.setAlignment(HorizontalAlignment.RIGHT);
        styleForNumeric.setVerticalAlignment(VerticalAlignment.TOP);

        XSSFFont headerFont = workbook.createFont();
        headerFont.setBold(true);
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFont(headerFont);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);

        // create header row
        Row headerRow = sheet.createRow(0);
        String[] judulHeader = {"No", "Nama Peneliti", "NIDN", "Institusi", "Skema Penelitian", "Judul Penelitian", "Biaya Penelitian", "Dana Kontribusi Mitra", "Barang Kontribusi Mitra"};
        for(int i=0; i < judulHeader.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellStyle(headerStyle);
            cell.setCellValue(judulHeader[i]);
        }

        sheet.setColumnWidth(0, 1000);
        sheet.setColumnWidth(1, 10000);
        sheet.setColumnWidth(2, 5000);
        sheet.setColumnWidth(3, 10000);
        sheet.setColumnWidth(4, 10000);
        sheet.setColumnWidth(5, 10000);
        sheet.setColumnWidth(6, 5000);
        sheet.setColumnWidth(7, 5000);
        sheet.setColumnWidth(8, 18000);

        DecimalFormat df = (DecimalFormat)DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols dfsIndonesia = new DecimalFormatSymbols();
        dfsIndonesia.setMonetaryDecimalSeparator(',');
        dfsIndonesia.setGroupingSeparator('.');
        dfsIndonesia.setCurrencySymbol("Rp. ");
        df.setDecimalFormatSymbols(dfsIndonesia);

        List<LaporanAnggaranDTO> laporanAnggaranDTOList = penelitianMapper.getLaporanAnggaran(idOrganisasi, tahunUsulan);
        int rowCount = 0;
        for(LaporanAnggaranDTO laporan: laporanAnggaranDTOList) {
            Row row = sheet.createRow(++rowCount);

            Cell cellNo = row.createCell(0);
            cellNo.setCellStyle(style);
            cellNo.setCellValue(rowCount);

            Cell cellPeneliti = row.createCell(1);
            cellPeneliti.setCellStyle(style);
            cellPeneliti.setCellValue(laporan.getPeneliti());

            Cell cellNidn = row.createCell(2);
            cellNidn.setCellStyle(style);
            cellNidn.setCellValue(laporan.getNidn());

            Cell cellInstitusi = row.createCell(3);
            cellInstitusi.setCellStyle(style);
            cellInstitusi.setCellValue(laporan.getInstitusi());

            Cell cellSkema = row.createCell(4);
            cellSkema.setCellStyle(style);
            cellSkema.setCellValue(laporan.getSkema());

            Cell cellJudul = row.createCell(5);
            cellJudul.setCellStyle(style);
            cellJudul.setCellValue(laporan.getJudulPenelitian());

            Cell cellBiaya = row.createCell(6);
            cellBiaya.setCellStyle(styleForNumeric);
            cellBiaya.setCellValue(df.format(laporan.getBiaya()));

            Cell cellDana = row.createCell(7);
            cellDana.setCellStyle(styleForNumeric);
            cellDana.setCellValue(df.format(laporan.getDanaKontribusiMitra()));

            Cell cellBarang = row.createCell(8);
            cellBarang.setCellStyle(style);
            cellBarang.setCellValue(laporan.getBarangKontribusiMitra());
        }


        return workbook;
    }
}

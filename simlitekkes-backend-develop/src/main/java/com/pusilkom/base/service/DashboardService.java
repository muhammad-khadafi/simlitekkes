package com.pusilkom.base.service;

import com.pusilkom.base.dto.ColumnChartSeriesDTO;
import com.pusilkom.base.dto.DashboardColumnChartDTO;
import com.pusilkom.base.dto.DashboardPieChartDTO;
import com.pusilkom.base.dto.DashboardTableDTO;
import com.pusilkom.base.model.mapper.PenelitiMapper;
import com.pusilkom.base.model.mapper.PenelitianMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class DashboardService {

    @Autowired
    PenelitiMapper penelitiMapper;

    @Autowired
    PenelitianMapper penelitianMapper;

    public List<DashboardPieChartDTO> getPenelitiPerPendidikanTerakhir(Integer idOrganisasi) {
        return penelitiMapper.getPenelitiPerPendidikanTerakhir(idOrganisasi);
    }

    public List<DashboardTableDTO> getPenelitiReviewerPerBidangIlmu(Integer idOrganisasi) {
         int currentYear = Calendar.getInstance().get(Calendar.YEAR);
         return penelitiMapper.getPenelitiReviewerPerBidangIlmu(idOrganisasi, String.valueOf(currentYear));
    }

    public List<DashboardTableDTO> getProposalPerSkemaForTable(Integer idOrganisasi) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return penelitianMapper.getProposalPerSkema(idOrganisasi, String.valueOf(currentYear));
    }

    public DashboardColumnChartDTO getProposalPerSkemaForChart(Integer idOrganisasi) {
        DashboardColumnChartDTO columnChartDTO = new DashboardColumnChartDTO();
        List<String> categories = new ArrayList<String>();
        List<ColumnChartSeriesDTO> series = new ArrayList<ColumnChartSeriesDTO>();
        ColumnChartSeriesDTO diajukanSeries, dalamProsesSeries, diterimaSeries, ditolakSeries, telatSeries;
        diajukanSeries = new ColumnChartSeriesDTO("Diajukan");
        dalamProsesSeries = new ColumnChartSeriesDTO("Dalam Proses");
        diterimaSeries = new ColumnChartSeriesDTO("Diterima");
        ditolakSeries = new ColumnChartSeriesDTO("Ditolak");
        telatSeries = new ColumnChartSeriesDTO("Telat");

        List<Integer> diajukanData, dalamProsesData, diterimaData, ditolakData, telatData;
        diajukanData = new ArrayList<Integer>();
        dalamProsesData = new ArrayList<Integer>();
        diterimaData = new ArrayList<Integer>();
        ditolakData = new ArrayList<Integer>();
        telatData = new ArrayList<Integer>();

        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        List<DashboardTableDTO> tableDTO = penelitianMapper.getProposalPerSkema(idOrganisasi, String.valueOf(currentYear));
        for(DashboardTableDTO table: tableDTO) {
            categories.add(table.getSingkatan());
            diajukanData.add(table.getDiajukan());
            dalamProsesData.add(table.getDalamProses());
            diterimaData.add(table.getDiterima());
            ditolakData.add(table.getDitolak());
            telatData.add(table.getTelat());
        }

        diajukanSeries.setData(diajukanData);
        dalamProsesSeries.setData(dalamProsesData);
        diterimaSeries.setData(diterimaData);
        ditolakSeries.setData(ditolakData);
        telatSeries.setData(telatData);

        series.add(diajukanSeries);
        series.add(dalamProsesSeries);
        series.add(diterimaSeries);
        series.add(ditolakSeries);
        series.add(telatSeries);

        columnChartDTO.setCategories(categories);
        columnChartDTO.setSeries(series);

        return columnChartDTO;
    }

    public List<DashboardTableDTO> getProposalPerKelompokTktForTable(Integer idOrganisasi) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return penelitianMapper.getProposalPerKelompokTkt(idOrganisasi, String.valueOf(currentYear));
    }

    public DashboardColumnChartDTO getProposalPerKelompokTktForChart(Integer idOrganisasi) {
        DashboardColumnChartDTO columnChartDTO = new DashboardColumnChartDTO();

        List<String> categories = new ArrayList<String>();
        List<ColumnChartSeriesDTO> series = new ArrayList<ColumnChartSeriesDTO>();
        ColumnChartSeriesDTO level1Series, level2Series, level3Series, level4Series, level5Series,
                level6Series, level7Series, level8Series, level9Series;

        level1Series = new ColumnChartSeriesDTO("Level 1");
        level2Series = new ColumnChartSeriesDTO("Level 2");
        level3Series = new ColumnChartSeriesDTO("Level 3");
        level4Series = new ColumnChartSeriesDTO("Level 4");
        level5Series = new ColumnChartSeriesDTO("Level 5");
        level6Series = new ColumnChartSeriesDTO("Level 6");
        level7Series = new ColumnChartSeriesDTO("Level 7");
        level8Series = new ColumnChartSeriesDTO("Level 8");
        level9Series = new ColumnChartSeriesDTO("Level 9");

        List<Integer> level1Data, level2Data, level3Data, level4Data, level5Data, level6Data,
                level7Data, level8Data, level9Data;

        level1Data = new ArrayList<Integer>();
        level2Data = new ArrayList<Integer>();
        level3Data = new ArrayList<Integer>();
        level4Data = new ArrayList<Integer>();
        level5Data = new ArrayList<Integer>();
        level6Data = new ArrayList<Integer>();
        level7Data = new ArrayList<Integer>();
        level8Data = new ArrayList<Integer>();
        level9Data = new ArrayList<Integer>();

        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        List<DashboardTableDTO> tableDTO = penelitianMapper.getProposalPerKelompokTkt(idOrganisasi, String.valueOf(currentYear));
        for(DashboardTableDTO table: tableDTO) {
            categories.add(table.getNama());
            level1Data.add(table.getLevel1());
            level2Data.add(table.getLevel2());
            level3Data.add(table.getLevel3());
            level4Data.add(table.getLevel4());
            level5Data.add(table.getLevel5());
            level6Data.add(table.getLevel6());
            level7Data.add(table.getLevel7());
            level8Data.add(table.getLevel8());
            level9Data.add(table.getLevel9());
        }

        level1Series.setData(level1Data);
        level2Series.setData(level2Data);
        level3Series.setData(level3Data);
        level4Series.setData(level4Data);
        level5Series.setData(level5Data);
        level6Series.setData(level6Data);
        level7Series.setData(level7Data);
        level8Series.setData(level8Data);
        level9Series.setData(level9Data);

        series.add(level1Series);
        series.add(level2Series);
        series.add(level3Series);
        series.add(level4Series);
        series.add(level5Series);
        series.add(level6Series);
        series.add(level7Series);
        series.add(level8Series);
        series.add(level9Series);

        columnChartDTO.setCategories(categories);
        columnChartDTO.setSeries(series);

        return columnChartDTO;
    }

    public List<DashboardTableDTO> getProposalPerTopik(Integer idOrganisasi) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return penelitianMapper.getProposalPerTopik(idOrganisasi, String.valueOf(currentYear));
    }

    public List<DashboardTableDTO> getProposalPerTemaForTable(Integer idOrganisasi) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return penelitianMapper.getProposalPerTema(idOrganisasi, String.valueOf(currentYear));
    }

    public DashboardColumnChartDTO getProposalPerTemaForChart(Integer idOrganisasi) {
        DashboardColumnChartDTO columnChartDTO = new DashboardColumnChartDTO();
        List<String> categories = new ArrayList<String>();
        List<ColumnChartSeriesDTO> series = new ArrayList<ColumnChartSeriesDTO>();
        ColumnChartSeriesDTO diajukanSeries, dalamProsesSeries, diterimaSeries, ditolakSeries, telatSeries;
        diajukanSeries = new ColumnChartSeriesDTO("Diajukan");
        dalamProsesSeries = new ColumnChartSeriesDTO("Dalam Proses");
        diterimaSeries = new ColumnChartSeriesDTO("Diterima");
        ditolakSeries = new ColumnChartSeriesDTO("Ditolak");
        telatSeries = new ColumnChartSeriesDTO("Telat");

        List<Integer> diajukanData, dalamProsesData, diterimaData, ditolakData, telatData;
        diajukanData = new ArrayList<Integer>();
        dalamProsesData = new ArrayList<Integer>();
        diterimaData = new ArrayList<Integer>();
        ditolakData = new ArrayList<Integer>();
        telatData = new ArrayList<Integer>();

        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        List<DashboardTableDTO> tableDTO = penelitianMapper.getProposalPerTema(idOrganisasi, String.valueOf(currentYear));
        for(DashboardTableDTO table: tableDTO) {
            categories.add(table.getNama());
            diajukanData.add(table.getDiajukan());
            dalamProsesData.add(table.getDalamProses());
            diterimaData.add(table.getDiterima());
            ditolakData.add(table.getDitolak());
            telatData.add(table.getTelat());
        }

        diajukanSeries.setData(diajukanData);
        dalamProsesSeries.setData(dalamProsesData);
        diterimaSeries.setData(diterimaData);
        ditolakSeries.setData(ditolakData);
        telatSeries.setData(telatData);

        series.add(diajukanSeries);
        series.add(dalamProsesSeries);
        series.add(diterimaSeries);
        series.add(ditolakSeries);
        series.add(telatSeries);

        columnChartDTO.setCategories(categories);
        columnChartDTO.setSeries(series);
        return columnChartDTO;
    }
}

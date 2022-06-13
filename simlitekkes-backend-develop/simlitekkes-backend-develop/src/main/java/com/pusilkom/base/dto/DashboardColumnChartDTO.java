package com.pusilkom.base.dto;

import java.util.List;

public class DashboardColumnChartDTO {
    private List<String> categories;
    private List<ColumnChartSeriesDTO> series;

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<ColumnChartSeriesDTO> getSeries() {
        return series;
    }

    public void setSeries(List<ColumnChartSeriesDTO> series) {
        this.series = series;
    }
}

package com.pusilkom.base.dto;

import java.util.List;

public class ColumnChartSeriesDTO {
    private String name;
    private List<Integer> data;

    public ColumnChartSeriesDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }
}

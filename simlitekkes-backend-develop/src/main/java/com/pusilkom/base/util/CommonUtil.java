package com.pusilkom.base.util;

import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

@Component
public class CommonUtil {

    public String removeTimeStamp(String originalName){
        String nameWithoutTimeStamp = "";
        String[] parts = originalName.split("-");
        for (int i = 0; i < parts.length ; i++) {
            if (i < parts.length - 1) {
                if (i == 0){
                    nameWithoutTimeStamp = parts[i];
                } else {
                    nameWithoutTimeStamp = nameWithoutTimeStamp + "-" + parts[i];
                }
            } else {
                String[] parts2 = parts[i].split("\\.");
                String ext = parts2[parts2.length-1];
                nameWithoutTimeStamp = nameWithoutTimeStamp + "." + ext;
            }
        }
        return nameWithoutTimeStamp;
    }

    public String toIdrFormat(Double value){
        DecimalFormat idrFormat = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols rpSymbol = new DecimalFormatSymbols();

        rpSymbol.setCurrencySymbol("Rp.");
        rpSymbol.setMonetaryDecimalSeparator(',');
        rpSymbol.setGroupingSeparator('.');

        idrFormat.setDecimalFormatSymbols(rpSymbol);
        return idrFormat.format(value);
    }

}

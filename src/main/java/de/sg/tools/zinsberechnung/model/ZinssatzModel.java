package de.sg.tools.zinsberechnung.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class ZinssatzModel {

    private BigDecimal jahreszins;

    private LocalDate gueltigAb;

    private LocalDate gueltigBis;

}

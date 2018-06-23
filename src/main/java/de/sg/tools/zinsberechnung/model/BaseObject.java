package de.sg.tools.zinsberechnung.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

/**
 * @author Sita Geßner
 */
@Data
public abstract class BaseObject {

    private BigDecimal betrag;

    private LocalDate datum;

}

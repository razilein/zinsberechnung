package de.sg.tools.zinsberechnung.model;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Sita Geßner
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Zinsberechnung extends BaseObject {

    private BigDecimal zinssatz;

    private List<Zahlung> zahlungen;

    private List<Sollaenderung> sollaenderungen;

}

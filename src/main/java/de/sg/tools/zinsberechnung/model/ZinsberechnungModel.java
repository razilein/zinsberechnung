package de.sg.tools.zinsberechnung.model;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Sita Geßner
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ZinsberechnungModel extends BaseObject {

    private LocalDate berechnungsEnde;

    private List<Zahlung> zahlungen;

    private List<Sollaenderung> sollaenderungen;

    private Zinstyp zinstyp;

}

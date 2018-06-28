package de.sg.tools.zinsberechnung.model;

import java.math.BigDecimal;
import java.util.List;

import de.data_team.commons.avviso.model.types.Rechenweise;
import de.data_team.commons.avviso.model.types.Zinsmethode;
import lombok.Data;

@Data
public class Zinstyp {

    private BigDecimal abrundenAuf;

    private List<ZinssatzModel> zinssaetze;

    private Rechenweise rechenweise;

    private Zinsmethode zinsmethode;

    public void setRechenweise(final int value) {
        rechenweise = Rechenweise.getByCode(value);
    }

    public void setZinsmethode(final int value) {
        zinsmethode = Zinsmethode.getByCode(value);
    }

}

package de.sg.tools.zinsberechnung.rest;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.web.bind.annotation.RequestBody;

import de.data_team.commons.avviso.model.DefinitionZinssatz;
import de.data_team.commons.avviso.model.Zinssatz;
import de.data_team.commons.avviso.model.Ztyp;
import de.data_team.commons.avviso.model.types.Rechenweise;
import de.data_team.commons.avviso.model.types.Zinsmethode;
import de.data_team.zinsberechnung.Zinsberechnung;
import de.data_team.zinsberechnung.model.Zinsprotokoll;
import de.sg.tools.zinsberechnung.model.ZinsberechnungModel;
import de.sg.tools.zinsberechnung.model.ZinssatzModel;
import de.sg.tools.zinsberechnung.model.Zinstyp;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ZinsberechnungService {

    private static final Zinsberechnung zinsberechnung = Zinsberechnung.INSTANCE;

    public static BigDecimal berechnen(@RequestBody final ZinsberechnungModel model) {
        final Ztyp zinstyp = getZinstyp(model.getZinstyp());
        final Zinsprotokoll zinsprotokoll = zinsberechnung.berechne(model.getBetrag(), zinstyp, model.getDatum(),
                model.getBerechnungsEnde() == null ? LocalDate.now() : model.getBerechnungsEnde());

        return zinsprotokoll.getZinsbetrag();
    }

    private Ztyp getZinstyp(final Zinstyp zinstyp) {
        final DefinitionZinssatz definitionZinssatz = new DefinitionZinssatz();
        for (final ZinssatzModel zinssatzModel : zinstyp.getZinssaetze()) {
            final Zinssatz zinssatzDef = new Zinssatz();
            zinssatzDef.setGueltigAb(zinssatzModel.getGueltigAb());
            zinssatzDef.setGueltigBis(zinssatzModel.getGueltigBis());
            zinssatzDef.setJahreszins(zinssatzModel.getJahreszins());
            definitionZinssatz.getZinssaetze().add(zinssatzDef);
        }

        final Ztyp result = new Ztyp();
        result.setId("" + RandomUtils.nextLong());
        result.setKuerzel(result.getId());
        result.setDefinitionZinssatz(definitionZinssatz);
        result.setBetragAbrundenAuf(zinstyp.getAbrundenAuf());
        result.setRechenweise(zinstyp.getRechenweise() == null ? Rechenweise.TAGE : zinstyp.getRechenweise());
        result.setZinsmethode(zinstyp.getZinsmethode() == null ? Zinsmethode.T_30_J_360 : zinstyp.getZinsmethode());

        return result;
    }

}

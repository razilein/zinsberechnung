package de.sg.tools.zinsberechnung.rest;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import de.sg.tools.zinsberechnung.model.ZinsberechnungModel;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Sita Geßner
 */
@RestController
@Slf4j
public class ZinsberechnungRestController {

    @PostMapping("/berechnen")
    public String berechnen(@RequestBody final ZinsberechnungModel model) {
        log.info(model.toString());
        return ZinsberechnungService.berechnen(model).toString();
    }

    @GetMapping(value = "/enums")
    public Map<String, Map<String, Object>> getEnums() {
        return ZinsberechnungService.getEnumValues();
    }

}

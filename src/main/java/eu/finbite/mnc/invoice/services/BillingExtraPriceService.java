package eu.finbite.mnc.invoice.services;

import eu.finbite.mnc.invoice.model.ServicePrice;
import eu.finbite.mnc.invoice.utility.CsvLoader;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Getter
@Slf4j
@Service
public class BillingExtraPriceService {

    private static final String SOURCE_FILE = "data/bill-extra-price.csv";

    private List<ServicePrice> priceList;

    @PostConstruct
    void load() {
        try {
            priceList = CsvLoader.readFileToEntity(ServicePrice.class, SOURCE_FILE);
        } catch (IOException e) {
            log.error("Price list can't be loaded, error: {}", e.getMessage());
        }
    }
}

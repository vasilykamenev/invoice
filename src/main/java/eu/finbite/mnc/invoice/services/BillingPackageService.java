package eu.finbite.mnc.invoice.services;

import eu.finbite.mnc.invoice.exception.PackageNotFound;
import eu.finbite.mnc.invoice.model.BillPackage;
import eu.finbite.mnc.invoice.model.BillPackageType;
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
public class BillingPackageService {

    private static final String SOURCE_FILE = "data/bill-packages.csv";

    private List<BillPackage> packages;

    @PostConstruct
    void load() {
        try {
            packages = CsvLoader.readFileToEntity(BillPackage.class, SOURCE_FILE);
        } catch (IOException e) {
            log.error("Billing Packages can't be loaded, error: {}", e.getMessage());
        }
    }

    public BillPackage getPackage(BillPackageType billPackageType) {
        return packages.stream()
            .filter(p -> billPackageType.equals(p.getBillPackageType()))
            .findFirst()
            .orElseThrow( () -> new PackageNotFound("Package does not exist"));
    }
}

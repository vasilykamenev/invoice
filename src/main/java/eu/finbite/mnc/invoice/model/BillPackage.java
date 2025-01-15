package eu.finbite.mnc.invoice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Currency;
import java.util.Locale;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BillPackage {
    private BillPackageType billPackageType;
    private Integer maxMinutes;
    private Integer maxSms;
    private Double price;

    @Override
    public String toString() {
        return "Name: %s, minutes: %s, sms: %s, price: %.2f %s".formatted(billPackageType.getName(), maxMinutes, maxSms, price, Currency.getInstance(Locale.getDefault()));
    }
}

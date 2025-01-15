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
public class ServicePrice {
    private ServiceCode code;
    private String name;
    private Double value;

    @Override
    public String toString() {
        return "%s: %.2f %S".formatted(name, value, Currency.getInstance(Locale.getDefault()));
    }
}

package eu.finbite.mnc.invoice.model;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class Bill {

    private static final String HEADER_FORMAT = "%40s | %10s | %s".formatted("Name of service", "Amount", "Price");
    private static final String ROW_FORMAT = "%40s | %10s | %.2f";
    private static final String TOTAL_FORMAT = "%45s Total: %.2f %s";

    private List<BillRow> rows = new ArrayList<>(0);

    public void add(BillRow chargeRow) {
        rows.add(chargeRow);
    }

    public Double getPaymentTotal() {
        return rows.stream().mapToDouble(BillRow::getPrice).sum();
    }

    public String print() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(HEADER_FORMAT).append(System.lineSeparator());
        rows.stream()
            .map(m -> ROW_FORMAT.formatted(m.getCode(), m.getAmount(), m.getPrice()))
            .forEach(row -> stringBuilder.append(row).append(System.lineSeparator()));
        stringBuilder.append(TOTAL_FORMAT.formatted("", getPaymentTotal(), Currency.getInstance(Locale.getDefault())));
        return stringBuilder.toString();
    }
}

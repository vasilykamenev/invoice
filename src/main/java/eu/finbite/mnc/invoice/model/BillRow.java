package eu.finbite.mnc.invoice.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BillRow {
    private String code;
    private Integer amount;
    private Double price;
}

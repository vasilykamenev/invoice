package eu.finbite.mnc.invoice.model;

import lombok.Getter;

import java.io.Serializable;

@Getter
public enum BillPackageType implements Serializable {
    S("Package S"), M("Package M"), L("Package L");

    private final String name;

    BillPackageType(String name) {
        this.name = name;
    }
}

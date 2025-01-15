package eu.finbite.mnc.invoice.services;

import eu.finbite.mnc.invoice.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BillingService {

    private final BillingExtraPriceService billingExtraPrice;

    public Bill calculate(BillPackage billPackage, Integer minutesAmount, Integer smsAmount) {
        Bill bill = new Bill();
        bill.add(BillRow.builder().code(billPackage.getBillPackageType().getName()).amount(1).price(billPackage.getPrice()).build());
        bill.add(createNewChargeLine(ServiceCode.MINUTE, minutesAmount, billPackage.getMaxMinutes()));
        bill.add(createNewChargeLine(ServiceCode.SMS, smsAmount, billPackage.getMaxSms()));

        return bill;
    }

    private BillRow createNewChargeLine(ServiceCode serviceCode, Integer smsAmount, Integer billPackage) {
        var servicePrice = billingExtraPrice.getPriceList().stream()
            .filter(p -> serviceCode.equals(p.getCode()))
            .findFirst()
            .orElse(new ServicePrice());
        return BillRow.builder()
            .code(servicePrice.getName())
            .amount(smsAmount)
            .price(calculatePayment(billPackage, smsAmount, servicePrice.getValue()))
            .build();
    }

    private Double calculatePayment(Integer maxValue, Integer amountValue, Double price) {
        var extraValue = (amountValue - maxValue) * price; return extraValue > 0 ? extraValue : 0;
    }
}

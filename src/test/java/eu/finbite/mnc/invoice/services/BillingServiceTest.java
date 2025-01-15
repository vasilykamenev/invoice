package eu.finbite.mnc.invoice.services;

import eu.finbite.mnc.invoice.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BillingServiceTest {

    BillingService billingService;

    @Mock
    BillingExtraPriceService billingExtraPrice;

    @BeforeEach
    void setUp() {
        billingService = new BillingService(billingExtraPrice);
        when(billingExtraPrice.getPriceList()).thenReturn(
            List.of(
                new ServicePrice(ServiceCode.MINUTE, "Name", 10.),
                new ServicePrice(ServiceCode.SMS, "Name", 10.)));
    }

    private static Stream<Arguments> provideDataForCalculation() {
        return Stream.of(
            Arguments.of(BillPackageType.S, 20, 100, 1005.),
            Arguments.of(BillPackageType.M, 20, 10, 105.),
            Arguments.of(BillPackageType.L, 10, 10, 5.),
            Arguments.of(BillPackageType.L, 0, 0, 5.)
            );
    }
    @ParameterizedTest(name = "{index} Calculate payment: type {0}, max minutes {1}, max sms {2}, total {3}")
    @DisplayName("Calculate total payment")
    @MethodSource("provideDataForCalculation")
    void t_0(BillPackageType type, Integer maxMinutes, Integer maxSms, Double totalPrice) {
        //given
        BillPackage billPackage = new BillPackage();
        billPackage.setBillPackageType(type);
        billPackage.setMaxMinutes(10);
        billPackage.setMaxSms(10);
        billPackage.setPrice(5.);
        //when
        var result = billingService.calculate(billPackage, maxMinutes, maxSms);
        //then
        assertThat(result).isNotNull().isInstanceOf(Bill.class);
        assertThat(result.getPaymentTotal()).isEqualTo(totalPrice);
    }
}
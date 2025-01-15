package eu.finbite.mnc.invoice.services;

import eu.finbite.mnc.invoice.model.ServicePrice;
import org.assertj.core.api.AssertionsForInterfaceTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class BillingExtraPriceTest {

    BillingExtraPriceService billingExtraPrice;

    @BeforeEach
    void setUp() {
        billingExtraPrice = new BillingExtraPriceService();
    }

    @DisplayName("Load price list")
    @Test
    void t_0() {
        //given
        //when
        billingExtraPrice.load();

        //then
        assertThat(billingExtraPrice).isNotNull();
        AssertionsForInterfaceTypes.assertThat(billingExtraPrice.getPriceList()).isNotNull().hasSize(2);
    }

    @DisplayName("Get price list")
    @Test
    void t_1() {
        //given
        ReflectionTestUtils.setField(billingExtraPrice, "priceList", List.of(new ServicePrice(), new ServicePrice(), new ServicePrice()));

        //when
        var result = billingExtraPrice.getPriceList();

        //then
        AssertionsForInterfaceTypes.assertThat(result).isNotNull().hasSize(3);
    }
}
package eu.finbite.mnc.invoice.services;

import eu.finbite.mnc.invoice.exception.PackageNotFound;
import eu.finbite.mnc.invoice.model.BillPackage;
import eu.finbite.mnc.invoice.model.BillPackageType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class BillingPackageTest {

    BillingPackageService billingPackage;

    @BeforeEach
    void setUp() {
        billingPackage = new BillingPackageService();
    }

    @DisplayName("Load packages list")
    @Test
    void t_0() {
        //given
        //when
        billingPackage.load();

        //then
        assertThat(billingPackage).isNotNull();
        assertThat(billingPackage.getPackages()).isNotNull().hasSize(3);
    }

    @DisplayName("Get packages list")
    @Test
    void t_1() {
        //given
        ReflectionTestUtils.setField(billingPackage, "packages", List.of(new BillPackage(), new BillPackage()));

        //when
        var result = billingPackage.getPackages();

        //then
        assertThat(result).isNotNull().hasSize(2);
    }

    @DisplayName("Get package")
    @Test
    void t_2() {
        //given
        ReflectionTestUtils.setField(billingPackage, "packages", List.of(
            new BillPackage(BillPackageType.L, 10, 1, 10.),
            new BillPackage(BillPackageType.M, 20, 11, 5.)));

        //when
        var result = billingPackage.getPackage(BillPackageType.M);

        //then
        assertThat(result).isNotNull().isInstanceOf(BillPackage.class);
        assertThat(result.getBillPackageType()).isEqualTo(BillPackageType.M);
    }

    @DisplayName("Get package when not exist")
    @Test
    void t_3() {
        //given
        ReflectionTestUtils.setField(billingPackage, "packages", List.of(
            new BillPackage(BillPackageType.L, 10, 1, 10.),
            new BillPackage(BillPackageType.M, 20, 11, 5.)));

        //when
        //then
        assertThatThrownBy(() -> billingPackage.getPackage(BillPackageType.S)).isInstanceOf(PackageNotFound.class);

    }

}
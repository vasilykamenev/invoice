package eu.finbite.mnc.invoice.commands;

import eu.finbite.mnc.invoice.model.*;
import eu.finbite.mnc.invoice.services.BillingExtraPriceService;
import eu.finbite.mnc.invoice.services.BillingPackageService;
import eu.finbite.mnc.invoice.services.BillingService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.shell.command.CommandHandlingResult;
import org.springframework.shell.command.annotation.ExceptionResolver;
import org.springframework.shell.standard.*;

import static java.util.Objects.isNull;

@AllArgsConstructor
@ShellComponent
@ShellCommandGroup("Bill")
public class ShellBillCommands {

    private final BillingPackageService billingPackage;

    private final BillingExtraPriceService billingExtraPrice;

    private final BillingService billingService;

    @ShellMethod(key = "packages", value = "List of supported packages")
    public String packages() {
        return StringUtils.join(billingPackage.getPackages(), System.lineSeparator());
    }

    @ShellMethod(key = "price", value = "Price list of extra values")
    public String price(@ShellOption(value = "minute,sms", defaultValue = ShellOption.NULL) String name) {
        if (isNull(name)) {
            return StringUtils.join(billingExtraPrice.getPriceList(), System.lineSeparator());
        } else {
            return billingExtraPrice.getPriceList().stream().filter(price -> price.getName().equals(name)).findFirst().orElse(new ServicePrice(ServiceCode.NON, "undefined", Double.NaN)).toString();
        }
    }

    @ShellMethod(key = "bill", value = "Create the bill")
    public String bill(@ShellOption(value = "type", help = "Select the package") BillPackageType type,
                       @ShellOption(value = "sms", defaultValue = "0", help = "Sms amount") Integer sms,
                       @ShellOption(value = "minutes", defaultValue = "0", help = "Minutes amount") Integer minutes) {

        BillPackage billPackage = billingPackage.getPackage(type);
        Bill bill = billingService.calculate(billPackage, minutes, sms);
        return bill.print();
    }

    @SuppressWarnings("unused")
    @ExceptionResolver({IllegalArgumentException.class})
    CommandHandlingResult errorHandler1(Exception e) {
        return CommandHandlingResult.of("Wrong value %s of Package name, select from list [%s] %s".formatted(((ConversionFailedException) e).getValue(), StringUtils.join(BillPackageType.values(), ","), System.lineSeparator()), 42);
    }

    @SuppressWarnings("unused")
    @ExceptionResolver({NumberFormatException.class})
    CommandHandlingResult errorHandler2(Exception e) {
        return CommandHandlingResult.of("Please use a number for value '%s' %s".formatted(((ConversionFailedException) e).getValue(), System.lineSeparator()), 42);
    }

}

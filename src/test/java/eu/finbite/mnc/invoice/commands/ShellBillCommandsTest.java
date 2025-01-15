package eu.finbite.mnc.invoice.commands;

import eu.finbite.mnc.invoice.services.BillingExtraPriceService;
import eu.finbite.mnc.invoice.services.BillingPackageService;
import eu.finbite.mnc.invoice.services.BillingService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.shell.test.ShellAssertions;
import org.springframework.shell.test.ShellTestClient;
import org.springframework.shell.test.autoconfigure.ShellTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

@ExtendWith(SpringExtension.class)
@ShellTest()
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ShellBillCommandsTest {

    @Autowired
    ShellTestClient client;

    @InjectMocks
    BillingService billingService;

    @InjectMocks
    BillingExtraPriceService billingExtraPrice;

    @InjectMocks
    BillingPackageService billingPackageService;

    @DisplayName("Test the shell commands")
    @Test
    void shell_test() {
        ShellTestClient.InteractiveShellSession session = client
            .interactive()
            .run();

        await().atMost(2, TimeUnit.SECONDS).untilAsserted(() -> {
            ShellAssertions.assertThat(session.screen())
                .containsText("shell");
        });

        session.write(session.writeSequence().text("help").carriageReturn().build());
        await().atMost(2, TimeUnit.SECONDS).untilAsserted(() -> {
            ShellAssertions.assertThat(session.screen())
                .containsText("AVAILABLE COMMANDS");
        });
    }

    @Configuration
    @Import({BillingService.class, BillingExtraPriceService.class, BillingPackageService.class})
    static class Config {

    }
}
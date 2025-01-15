package eu.finbite.mnc.invoice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Locale;

@SpringBootApplication
public class InvoiceApplication {

	public static void main(String[] args) {
		Locale.setDefault(new Locale("et","EE"));
		SpringApplication.run(InvoiceApplication.class, args);
	}
}

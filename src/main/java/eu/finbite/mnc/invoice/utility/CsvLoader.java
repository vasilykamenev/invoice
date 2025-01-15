package eu.finbite.mnc.invoice.utility;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import com.opencsv.bean.CsvToBeanBuilder;

public class CsvLoader {

    private static final char CSV_COLUMN_SEPARATOR = ',';

    private CsvLoader() {}

    public static <T> List<T> readFileToEntity(Class<T> clazz, String fileName) throws IOException {
        var file = new ClassPathResource(fileName).getInputStream();
        return new CsvToBeanBuilder<T>(new InputStreamReader(file))
            .withSeparator(CSV_COLUMN_SEPARATOR)
            .withIgnoreQuotations(true)
            .withType(clazz)
            .build()
            .parse();
    }

}

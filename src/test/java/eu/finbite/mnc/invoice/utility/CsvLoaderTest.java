package eu.finbite.mnc.invoice.utility;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


@ExtendWith(MockitoExtension.class)
class CsvLoaderTest {

    @BeforeEach
    void setUp() {
    }

    @DisplayName("Read source csv file")
    @Test
    void t_0() throws IOException {
        //given
        var filename = "data/correct-file.csv";
        //when
        var result = CsvLoader.readFileToEntity(Record.class, filename);
        //then
        assertThat(result).isNotNull()
            .isInstanceOf(List.class)
            .hasSize(2);
    }

    @DisplayName("Read corrupted source csv file")
    @Test
    void t_1() throws IOException {
        //given
        var filename = "data/corrupted-file.csv";
        //when
        var result = CsvLoader.readFileToEntity(Record.class, filename);
        //then
        assertThat(result).isNotNull()
            .isInstanceOf(List.class)
            .hasSize(2);
    }

}
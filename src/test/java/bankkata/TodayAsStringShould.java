package bankkata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

class TodayAsStringShould {

    private  TodayAsString todayAsString;

    @BeforeEach
    void setUp() {
        final var today = (Today) () -> LocalDate.of(2015, Month.MAY, 15);
        todayAsString = TodayAsString.create(today);
    }

    @Test
    void return_today_in_dd_MM_yyyy_format() {
        assertThat(todayAsString.get()).isEqualTo("15/05/2015");
    }
}
package bankkata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TodayShould {

    private Today today;

    @BeforeEach
    void setUp() {
        today = Today.create();
    }

    @Test
    void return_today_as_local_date() {
        assertThat(today.get()).isToday();
    }
}
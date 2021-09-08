import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MainTest {

    @Mock
    public MarketApi marketApi;

    @BeforeEach
    public void init() {
        Mockito.lenient().when(marketApi.connect()).thenReturn(3000);
    }

    @Test
    void KRW_계산기_테스트() {
        Calculator calculator = new Calculator(new KrwCalculator());
        int resultPlus = calculator.plus(10, 20);
        int resultMinus = calculator.minus(10, 20);

        assertEquals(30, resultPlus);
        assertEquals(-10, resultMinus);
    }

    @Test
    void Dollar_계산기_테스트() {
        Calculator calculator = new Calculator(new DollarCalculator(marketApi));

        int resultPlus = calculator.plus(20, 10);
        int resultMinus = calculator.minus(20, 10);

        assertEquals(90000, resultPlus);
        assertEquals(30000, resultMinus);
    }
}
package mn.edu.must.sqat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GradeCalculatorTest {

    private final GradeCalculator calc = new GradeCalculator();

    // ---- letterGrade: ердийн утгууд ----

    @Test
    @DisplayName("95 оноо A дүн байх ёстой")
    void ninetyFiveIsA() {
        double score = 95.0; // Arrange
        String grade = calc.letterGrade(score); // Act
        assertEquals("A", grade); // Assert
    }

    @Test
    @DisplayName("65 оноо D дүн байх ёстой")
    void sixtyFiveIsD() {
        double score = 65.0;
        String grade = calc.letterGrade(score);
        assertEquals("D", grade);
    }

    @Test
    @DisplayName("30 оноо F дүн байх ёстой")
    void thirtyIsF() {
        double score = 30.0;
        String grade = calc.letterGrade(score);
        assertEquals("F", grade);
    }

    // ---- letterGrade: хязгаарын утгууд ----

    @Test
    @DisplayName("90 оноо яг A дүн байх ёстой (хязгаарын тохиолдол)")
    void ninetyIsExactlyA() {
        GradeCalculator calc = new GradeCalculator();
        String grade = calc.letterGrade(90.0);
        assertEquals("A", grade);
    }

    @Test
    @DisplayName("89.99 оноо B дүн байх ёстой (90-ээс бага)")
    void eightyNinePointNineNineIsB() {
        String grade = calc.letterGrade(89.99);
        assertEquals("B", grade);
    }

    @Test
    @DisplayName("60 оноо яг D дүн байх ёстой (хязгаарын тохиолдол)")
    void sixtyIsExactlyD() {
        String grade = calc.letterGrade(60.0);
        assertEquals("D", grade);
    }

    @Test
    @DisplayName("59.99 оноо F дүн байх ёстой (60-аас бага)")
    void fiftyNinePointNineNineIsF() {
        String grade = calc.letterGrade(59.99);
        assertEquals("F", grade);
    }

    @Test
    @DisplayName("0 оноо F дүн байх ёстой (доод хязгаар)")
    void zeroIsF() {
        String grade = calc.letterGrade(0.0);
        assertEquals("F", grade);
    }

    @Test
    @DisplayName("100 оноо A дүн байх ёстой (дээд хязгаар)")
    void hundredIsA() {
        String grade = calc.letterGrade(100.0);
        assertEquals("A", grade);
    }

    // ---- letterGrade: буруу оролт ----

    @Test
    @DisplayName("-1 оноо exception шидэх ёстой (доод хязгаараас гарсан)")
    void negativeScoreThrows() {
        assertThrows(IllegalArgumentException.class, () -> calc.letterGrade(-1));
    }

    @Test
    @DisplayName("101 оноо exception шидэх ёстой (дээд хязгаараас гарсан)")
    void aboveHundredThrows() {
        assertThrows(IllegalArgumentException.class, () -> calc.letterGrade(101));
    }

    // ---- letterGrade: parameterized ----

    @ParameterizedTest
    @DisplayName("letterGrade хязгаарын утгууд (parameterized)")
    @CsvSource({"95,A", "90,A", "89.99,B", "80,B", "70,C", "60,D", "59.99,F", "0,F"})
    void letterGradeBoundaries(double score, String expected) {
        assertEquals(expected, calc.letterGrade(score));
    }

    // ---- totalScore: ердийн ба хязгаарын утга ----

    @Test
    @DisplayName("Хязгаар дотор бүх дээд оноогоор нийлбэр 100 гарах ёстой")
    void totalScoreAllMaxValues() {
        double total = calc.totalScore(10, 40, 10, 10, 30);
        assertEquals(100.0, total);
    }

    // ---- totalScore: буруу оролт ----

    @Test
    @DisplayName("att сөрөг утгатай бол exception шидэх ёстой")
    void negativeAttThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> calc.totalScore(-5, 40, 10, 10, 30));
    }

    @Test
    @DisplayName("lab дээд хязгаараас хэтэрсэн бол exception шидэх ёстой")
    void labOverMaxThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> calc.totalScore(10, 41, 10, 10, 30));
    }

    // ---- totalScore: parameterized ----

    @ParameterizedTest
    @DisplayName("totalScore хэвийн нөхцөлүүд (parameterized)")
    @CsvSource({
            "10,40,10,10,30,100",
            "0,0,0,0,0,0",
            "5,20,5,5,15,50"
    })
    void totalScoreValidCombinations(double att, double lab, double q1, double q2, double exam, double expected) {
        assertEquals(expected, calc.totalScore(att, lab, q1, q2, exam));
    }
}
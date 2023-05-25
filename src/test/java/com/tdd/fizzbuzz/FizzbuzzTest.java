package com.tdd.fizzbuzz;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FizzbuzzTest {

    @DisplayName("Divisible by three")
    @Test
    @Order(1)
    public void numberDivisibleByThree() {
        String expectedResult = "Fizz";
        assertEquals(expectedResult, Fizzbuzz.compute(3), "Should return Fizz");
    }
    @DisplayName("Divisible by five")
    @Test
    @Order(2)
    public void numberDivisibleByFive() {
        String expectedResult = "Buzz";
        assertEquals(expectedResult, Fizzbuzz.compute(5), "Should return Buzz");
    }
    @DisplayName("Divisible by three and five")
    @Test
    @Order(3)
    public void numberDivisibleByThreeAndFive() {
        String expectedResult = "FizzBuzz";
        assertEquals(expectedResult, Fizzbuzz.compute(15), "Should return FizzBuzz");
    }
    @DisplayName("Not Divisible by three and five")
    @Test
    @Order(4)
    public void numberNotDivisibleByThreeAndFive() {
        String expectedResult = "1";
        assertEquals(expectedResult, Fizzbuzz.compute(1), "Should return 1");
    }
    @DisplayName("Test with small data")
    @ParameterizedTest(name="value={0}, expected={1}")
    @CsvFileSource(resources = "/small-test-data.csv")
    @Order(5)
    public void testWithSmallData(int number, String expectedResult) {
        assertEquals(expectedResult, Fizzbuzz.compute(number));
    }
}

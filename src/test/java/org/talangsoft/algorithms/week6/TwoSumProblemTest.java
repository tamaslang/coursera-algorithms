package org.talangsoft.algorithms.week6;

import org.junit.Ignore;
import org.junit.Test;
import org.talangsoft.algorithms.helper.InputFileReader;

import java.util.Arrays;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.LongStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TwoSumProblemTest {
    private TwoSumProblem underTest = new TwoSumProblem();

    private String week6AssignmentPartA = "week6/twosum_numbers.txt";


    @Test
    public void solveWeek6AssignmentPartAWithDomainKnowledgeII() throws Exception {
        // Improvement II.: can we eliminate loop -10000 - 10000?
        // idea: find all numbers within the given range
        // - divide to 2 (positives and negatives)
        // - put negatives to treeset (sorted)
        //
        // - loop 1 to length(positives)
        // - get the index of the first number in negatives that abs is greater or equal n-10000
        // - get the index of the first number in negatives that abs is smaller or equal n+10000
        // - add the difference of the indexes, the value is the count of the numbers that meet the criteria
        //
        // performs: 1747ms
        long[] numbers = InputFileReader.readLongElements(week6AssignmentPartA);
        long[] positives = Arrays.stream(numbers).filter(nr -> nr >= 0).toArray(); // 500010 positive
        long[] negatives = Arrays.stream(numbers).filter(nr -> nr < 0).toArray();  // 499990 negative
        // TODO: solve!
        long before = System.currentTimeMillis();

        int count = underTest.findTwoSumNumberForSumsInInterval(numbers, 10000);

        long after = System.currentTimeMillis();
        System.out.println(String.format("Finding 2SUM for assignment took %dms", (after - before)));

        assertThat(count, is(427));
    }

    @Test
    @Ignore("it takes too much time (418194ms -> 418sec)")
    public void solveWeek6AssignmentPartAWithDomainKnowledgeI() throws Exception {
        // with knowing the input, we know that the smallest abs number is 808434
        // so to find the numbers which sums up to a number in range -10000..10000
        // Improvement I.: first number should be negative the other should be positive
        long[] numbers = InputFileReader.readLongElements(week6AssignmentPartA);
        long[] positives = Arrays.stream(numbers).filter(nr -> nr >= 0).toArray(); // 500010 positive
        long[] negatives = Arrays.stream(numbers).filter(nr -> nr < 0).toArray();  // 499990 negative

        Map<Long, Long> negativeNumbers = underTest.arrayToMap(negatives);

        long before = System.currentTimeMillis();

        int count = 0;
        for (long n = -10000; n <= 10000; n++) {
            if (n % 100 == 0) System.out.println("n: " + n);
            if (underTest.findTwoSumNaiveWithHashNegativePositive(positives, negativeNumbers, n)) count++;
        }

        long after = System.currentTimeMillis();
        System.out.println(String.format("Finding 2SUM for assignment took %dms", (after - before)));
        assertThat(count, is(427));
    }

    @Test
    @Ignore("it takes too much time (893099ms -> 893sec)")
    public void solveWeek6AssignmentPartA() throws Exception {
        long[] numbers = InputFileReader.readLongElements(week6AssignmentPartA);
        assertThat(numbers.length, is(1_000_000));

        Map<Long, Long> numberMap = underTest.arrayToMap(numbers);

        long before = System.currentTimeMillis();

        int count = 0;
        for (long n = -10000; n <= 10000; n++) {
            if (n % 100 == 0) System.out.println("n: " + n);
            if (underTest.findTwoSumNaiveWithHash(numbers, numberMap, n)) count++;
        }

        long after = System.currentTimeMillis();
        System.out.println(String.format("Finding 2SUM for assignment took %dms", (after - before)));
        assertThat(count, is(427));
    }

    @Test
    public void testTwoSumWithNaive() {
        long[] inputArr = new long[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        assertThat(underTest.findTwoSumNaive(inputArr, inArray(inputArr)), is(true));
        assertThat(underTest.findTwoSumNaive(inputArr, -1l), is(false));
    }

    @Test
    public void testTwoSumWithHash() {
        long[] inputArr = new long[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        assertThat(underTest.findTwoSumWithHashmap(inputArr, inArray(inputArr)), is(true));
        assertThat(underTest.findTwoSumWithHashmap(inputArr, -1l), is(false));
    }

    @Test
    public void testPerformanceWithHashArray() {
        measurePerformanceFor(incArray(100_000), -1, underTest::findTwoSumNaive, false);
        measurePerformanceFor(incArray(100_000), 100000 * 2 - 1, underTest::findTwoSumNaive, true);

        final long largeArrayLength = 10_000_000;
        final long last2NumberSum = largeArrayLength * 2 - 1;
        long[] largeArray = incArray(largeArrayLength);
        measurePerformanceFor(largeArray, -1, underTest::findTwoSumWithSort, false);
        measurePerformanceFor(largeArray, last2NumberSum, underTest::findTwoSumWithSort, true);

        measurePerformanceFor(largeArray, -1l, underTest::findTwoSumWithHashmap, false);
        measurePerformanceFor(largeArray, last2NumberSum, underTest::findTwoSumWithHashmap, true);
    }

    private long inArray(long[] arr) {
        return arr[arr.length - 1] + arr[arr.length - 2];
    }

    private long[] incArray(long length) {
        return LongStream.rangeClosed(1, length).toArray();
    }

    private boolean measurePerformanceFor(long[] arr, long sum, BiFunction<long[], Long, Boolean> twoSumFunction, Boolean twoSumFound) {
        long before = System.currentTimeMillis();

        boolean result = twoSumFunction.apply(arr, sum);

        long after = System.currentTimeMillis();
        System.out.println(String.format("Finding 2SUM expecting '%s' in array with elements %d took %dms", twoSumFound,
                arr.length, (after - before)));
        assertThat(String.format("Two some result should have been '%s' ", twoSumFound), result, is(twoSumFound));
        return result;
    }
}

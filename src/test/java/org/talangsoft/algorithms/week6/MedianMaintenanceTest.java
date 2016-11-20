package org.talangsoft.algorithms.week6;

import org.junit.Assert;
import org.junit.Test;
import org.talangsoft.algorithms.helper.InputFileReader;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class MedianMaintenanceTest {

    private String sourceFile = "week6/median.txt";

    @Test
    public void insert5ElementsTest() throws Exception {
        MedianMaintenance m = new MedianMaintenance();
        assertThat(m.median(), is(Optional.empty()));
        m.insert(1);
        assertThat(m.median(), is(Optional.of(1)));
        m.insert(5);
        assertThat(m.median(), is(Optional.of(1)));
        m.insert(4);
        assertThat(m.median(), is(Optional.of(4)));
        m.insert(2);
        assertThat(m.median(), is(Optional.of(2)));
        m.insert(3);
        assertThat(m.median(), is(Optional.of(3)));
    }

    @Test
    public void solveWeek6AssignmentTest() throws Exception {
        int[] numbers = InputFileReader.readIntElements(sourceFile);
        Assert.assertThat(numbers.length, is(10000));

        MedianMaintenance m = new MedianMaintenance();
        int sumMedian = 0;
        for (int nr : numbers) {
            m.insert(nr);
            sumMedian += m.median().get();
        }
        int solution = sumMedian % 10000;
        Assert.assertThat(solution, is(1213));
    }
}

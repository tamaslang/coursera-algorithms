package org.talangsoft.algorithms.week2;


import org.junit.Assert;
import org.junit.Test;
import org.talangsoft.algorithms.helper.InputFileReader;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class QuickSortAssignmentTest {

    private String assigmentSourceFile = "week2/quicksort.txt";
//
//    Answers are:
//       size   first  last     median
//        10    25     29       21
//        100   615    587      518
//        1000  10297  10184    8921
    private String sourceFile10 = "week2/10.txt";
    private String sourceFile100 = "week2/100.txt";
    private String sourceFile1000 = "week2/1000.txt";


    public void validateSorted(int[] array) {
        for (int i = 1; i < array.length; i++) {
            Assert.assertTrue("Upcoming element should be greated than the previous. problem in position " + i + "." +
                            "Element array[" + (i - 1) + "] = " + array[i - 1] + ", " +
                            "Element array[" + i + "] = " + array[i],
                    array[i] >= array[i - 1]);
        }
    }

    private void expectNrOfComputation(String sourcefile, PivotChooseStrategy strategy, long numberOfCalculations) throws Exception{
        int[] elements = InputFileReader.readIntElements(sourcefile);
        QuickSort quickSort = new QuickSort(strategy);
        int[] sortedArr = quickSort.quickSort(elements);
        // should Be sorted
        validateSorted(sortedArr);
        assertThat(quickSort.getTotalNrOfComputations(), is(numberOfCalculations));
    }

    @Test
    public void countQuickSortOperationsWithChoosingLeftmostElementForSourceFile10() throws Exception {
        expectNrOfComputation(sourceFile10, new QuickSort.ChooseFirstPivot(), 25L);
    }

    @Test
    public void countQuickSortOperationsWithChoosingRightmostElementForSourceFile10() throws Exception {
        expectNrOfComputation(sourceFile10, new QuickSort.ChooseLastPivot(), 29L);
    }

    @Test
    public void countQuickSortOperationsWithChoosingMedianElementForSourceFile10() throws Exception {
        expectNrOfComputation(sourceFile10, new QuickSort.ChooseMedianPivot(), 21L);
    }

    @Test
    public void countQuickSortOperationsWithChoosingLeftmostElementForSourceFile100() throws Exception {
        expectNrOfComputation(sourceFile100, new QuickSort.ChooseFirstPivot(), 615L);
    }

    @Test
    public void countQuickSortOperationsWithChoosingRightmostElementForSourceFile100() throws Exception {
        expectNrOfComputation(sourceFile100, new QuickSort.ChooseLastPivot(), 587L);
    }

    @Test
    public void countQuickSortOperationsWithChoosingMedianElementForSourceFile100() throws Exception {
        expectNrOfComputation(sourceFile100, new QuickSort.ChooseMedianPivot(), 518L);
    }

    @Test
    public void countQuickSortOperationsWithChoosingLeftmostElementForSourceFile1000() throws Exception {
        expectNrOfComputation(sourceFile1000, new QuickSort.ChooseFirstPivot(), 10297L);
    }

    @Test
    public void countQuickSortOperationsWithChoosingRightmostElementForSourceFile1000() throws Exception {
        expectNrOfComputation(sourceFile1000, new QuickSort.ChooseLastPivot(), 10184L);
    }

    @Test
    public void countQuickSortOperationsWithChoosingMedianElementForSourceFile1000() throws Exception {
        expectNrOfComputation(sourceFile1000, new QuickSort.ChooseMedianPivot(), 8921L);
    }

    @Test
    public void countQuickSortOperationsWithChoosingLeftmostElement() throws Exception {
        expectNrOfComputation(assigmentSourceFile, new QuickSort.ChooseFirstPivot(), 162085L);
    }

    @Test
    public void countQuickSortOperationsWithChoosingRightmostElement() throws Exception {
        expectNrOfComputation(assigmentSourceFile, new QuickSort.ChooseLastPivot(), 164123L);
    }

    @Test
    public void countQuickSortOperationsWithChoosingMedianElement() throws Exception {
        expectNrOfComputation(assigmentSourceFile, new QuickSort.ChooseMedianPivot(), 138382L);
    }


}
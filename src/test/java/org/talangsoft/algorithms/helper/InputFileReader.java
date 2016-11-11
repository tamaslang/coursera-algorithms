package org.talangsoft.algorithms.helper;

import java.util.*;
import java.util.stream.Collectors;

public class InputFileReader {
    public static int[] readIntElements(String sourceFile) throws Exception {
        // reading lines
        ClassLoader classLoader = InputFileReader.class.getClassLoader();
        java.net.URL url = classLoader.getResource(sourceFile);
        java.nio.file.Path resPath = java.nio.file.Paths.get(url.toURI());
        return java.nio.file.Files.readAllLines(resPath).stream().mapToInt(Integer::valueOf).toArray();
    }

    public static long[] readLongElements(String sourceFile) throws Exception {
        // reading lines
        ClassLoader classLoader = InputFileReader.class.getClassLoader();
        java.net.URL url = classLoader.getResource(sourceFile);
        java.nio.file.Path resPath = java.nio.file.Paths.get(url.toURI());
        return java.nio.file.Files.readAllLines(resPath).stream().mapToLong(Long::valueOf).toArray();
    }

    public static String[] readStringElements(String sourceFile) throws Exception {
        // reading lines
        ClassLoader classLoader = InputFileReader.class.getClassLoader();
        java.net.URL url = classLoader.getResource(sourceFile);
        java.nio.file.Path resPath = java.nio.file.Paths.get(url.toURI());
        return java.nio.file.Files.readAllLines(resPath).stream().toArray(String[]::new);
    }


    private static final String TAB = "\t";
    private static final String COMA = ",";
    private static final String SPACE = " ";

    private static int[] strArrToIntArrWithoutHead(String[] strArr) {
        return Arrays.asList(Arrays.copyOfRange(strArr, 1, strArr.length)).stream().mapToInt(Integer::parseInt).toArray();
    }

    /**
     * Reads graph data from source file with vertex and adjacency list.
     *
     *    1
     *  /  \
     * /    \
     * 2 -- 3
     * | \  |
     * |  \ |
     * |   \|
     * 4----5
     *
     * Defined as lines with format:
     * 1 2 3
     * 2 1 3 4 5
     * 3 1 2 5
     * 4 2 5
     * 5 2 3 4
     *
     * @param sourceFile
     * @return
     * @throws Exception
     */
    public static Map<Integer, int[]> readGraphOfNodesAndAdjacentsList(String sourceFile) throws Exception {
        // reading lines
        ClassLoader classLoader = InputFileReader.class.getClassLoader();
        java.net.URL url = classLoader.getResource(sourceFile);
        java.nio.file.Path resPath = java.nio.file.Paths.get(url.toURI());
        List<String> lines = java.nio.file.Files.readAllLines(resPath);
        return lines.stream().map(line -> line.split(TAB)).collect(Collectors.toMap(lineValues -> new Integer(lineValues[0]), lineValues -> strArrToIntArrWithoutHead(lineValues)));
    }

    /**
     * Reads directed graph data from source file with format A to B edges.
     * Graph: @ is the arrow direction
     *
     *    1
     *  @  \
     * /    @
     * 2 @- 3
     * @ \  |
     * |  \ |
     * |   @@
     * 4@---5
     *
     * Defined as lines with format:
     * 1 3
     * 2 1
     * 2 5
     * 3 2
     * 3 5
     * 4 2
     * 5 4
     *
     * @param sourceFile
     * @return
     * @throws Exception
     */
    public static Map<Integer, int[]> readDirectedGraphElementsFormatOfAtoBEdges(String sourceFile) throws Exception {
        // reading lines
        ClassLoader classLoader = InputFileReader.class.getClassLoader();
        java.net.URL url = classLoader.getResource(sourceFile);
        java.nio.file.Path resPath = java.nio.file.Paths.get(url.toURI());
        List<String> lines = java.nio.file.Files.readAllLines(resPath);
        Map<Integer, List<Integer>> nodeAndAdjacents = new HashMap<>();
        for (int i = 0; i < lines.size(); i++) {
            String[] edgeAtoBStr = lines.get(i).split(SPACE);
            Integer[] edgeAToB = new Integer[]{Integer.valueOf(edgeAtoBStr[0]), Integer.valueOf(edgeAtoBStr[1])};
            if (nodeAndAdjacents.containsKey(edgeAToB[0])) {
                nodeAndAdjacents.get(edgeAToB[0]).add(edgeAToB[1]);
            } else {
                List<Integer> adjacents = new ArrayList<>();
                adjacents.add(edgeAToB[1]);
                nodeAndAdjacents.put(edgeAToB[0], adjacents);
            }
        }
        // convert adjacents to array
        Map<Integer, int[]> nodesAndAdjacentsArr = new HashMap<>(nodeAndAdjacents.size());
        Iterator<Map.Entry<Integer,List<Integer>>> entryIterator = nodeAndAdjacents.entrySet().iterator();
        while(entryIterator.hasNext()){
            Map.Entry<Integer,List<Integer>> entry = entryIterator.next();
            nodesAndAdjacentsArr.put(entry.getKey(),entry.getValue().stream().mapToInt(Integer::intValue).toArray());
        }
        return nodesAndAdjacentsArr;
    }

    /**
     * Reads weighted directed graph from source file with format vertex and adjacency list with weights
     * Graph: @ is the arrow direction and the wieghts are in parenthesis
     *         1
     *       @  \
     *  (3) /    \ (9)
     *     / (4)  @
     *    2 @-----3
     *    @ \     |
     *    |   \(4)| (11)
     * (7)|     \ |
     *    |      @@
     *    4@------5
     *       (8)
     * Defined as lines with format:
     * 1 3,9
     * 2 1,3 5,4
     * 3 2,4 5,11
     * 4 2,7
     * 5 4,8
     *
     * @param sourceFile
     * @return
     * @throws Exception
     */
    public static Map<Integer, int[][]> readDirectedWeightedGraph(String sourceFile) throws Exception {
        // reading lines
        ClassLoader classLoader = InputFileReader.class.getClassLoader();
        java.net.URL url = classLoader.getResource(sourceFile);
        java.nio.file.Path resPath = java.nio.file.Paths.get(url.toURI());
        List<String> lines = java.nio.file.Files.readAllLines(resPath);
        return lines.stream().map(line -> line.split(TAB)).collect(
                Collectors.toMap(lineValues -> new Integer(lineValues[0]),
                        lineValues -> strArrToWeightedIntArrWithoutHead(lineValues)));
    }

    private static int[] strArrToIntArr(String[] strArr){
        return Arrays.stream(strArr).mapToInt(Integer::parseInt).toArray();
    }

    private static int[][] strArrToWeightedIntArrWithoutHead(String[] strArr) {
        return Arrays.asList(Arrays.copyOfRange(strArr, 1, strArr.length)).stream()
                .map(weightedTarget -> weightedTarget.split(COMA))
                .map(targetAndWeight -> strArrToIntArr(targetAndWeight)).toArray(int[][]::new);
    }


}

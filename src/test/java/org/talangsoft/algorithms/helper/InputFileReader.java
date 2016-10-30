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


    private static final String TAB = "\t";
    private static final String SPACE = " ";

    private static int[] strArrToIntArrWithoutHead(String[] strArr) {
        return Arrays.asList(Arrays.copyOfRange(strArr, 1, strArr.length)).stream().mapToInt(Integer::parseInt).toArray();
    }

    public static Map<Integer, int[]> readGraphElementsWithFormatOfNodesAndAdjacentsList(String sourceFile) throws Exception {
        // reading lines
        ClassLoader classLoader = InputFileReader.class.getClassLoader();
        java.net.URL url = classLoader.getResource(sourceFile);
        java.nio.file.Path resPath = java.nio.file.Paths.get(url.toURI());
        List<String> lines = java.nio.file.Files.readAllLines(resPath);
        return lines.stream().map(line -> line.split(TAB)).collect(Collectors.toMap(lineValues -> new Integer(lineValues[0]), lineValues -> strArrToIntArrWithoutHead(lineValues)));
    }

    public static Map<Integer, int[]> readGraphElementsFormatOfAtoBEdges(String sourceFile) throws Exception {
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

}

package pa;

import Lab7.Point;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PathTest {
    @Test
    void findPath()
    {
        int[][] mat = new int[][] {
                { 1, 0, 1, 1, 1, 1, 0, 1, 1, 1 },
                { 1, 0, 1, 0, 1, 1, 1, 0, 1, 1 },
                { 1, 1, 1, 0, 1, 1, 0, 1, 0, 1 },
                { 0, 0, 0, 0, 1, 0, 0, 0, 0, 1 },
                { 1, 1, 1, 0, 1, 1, 1, 0, 1, 0 },
                { 1, 0, 1, 1, 1, 1, 0, 1, 0, 0 },
                { 1, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
                { 1, 0, 1, 1, 1, 1, 0, 1, 1, 1 },
                { 1, 1, 0, 0, 0, 0, 1, 0, 0, 1 }
        };

        Node src = new Node(0,0);
        Node dest = new Node(3,4);
        Path path = new Path(src, dest, mat);
        for (Node node : Path.shortestPath)
        {System.out.print("("+node.x+","+node.y+ ")\t");}
    }
    @Test
    void testRead()
    {
        String file ="C:\\Users\\kenla\\IdeaProjects\\Comp3111LEx\\MazeMap_SPT.csv";
    }

    @Test
    void testPath() {
        int[][] mat = new int[][] {
                { 1, 0, 1, 1, 1, 1, 0, 1, 1, 1 },
                { 1, 0, 1, 0, 1, 1, 1, 0, 1, 1 },
                { 1, 1, 1, 0, 1, 1, 0, 1, 0, 1 },
                { 0, 0, 0, 0, 1, 0, 0, 0, 0, 1 },
                { 1, 1, 1, 0, 1, 1, 1, 0, 1, 0 },
                { 1, 0, 1, 1, 1, 1, 0, 1, 0, 0 },
                { 1, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
                { 1, 0, 1, 1, 1, 1, 0, 1, 1, 1 },
                { 1, 1, 0, 0, 0, 0, 1, 0, 0, 1 }
        };
        Node src = new Node(0,0);
        Node dest = new Node(3,4);
        Path path = new Path(src, dest, mat);
        for (Node node : Path.shortestPath) {
            assertEquals(1, mat[node.x][node.y]);
        }
    }
}
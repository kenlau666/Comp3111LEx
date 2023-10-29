package Lab7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PathTest {
    @Test
    void findPath() {
        boolean map[][] = {{true, true},
                {true, true}};
        Point source = new Point(0, 0);
        Point destination = new Point(0, 1);
        Path actual = new Path(map, source, destination);
        Point path[] = {source, destination};
        Path expected = new Path(path);
        for (int i = 0; i < actual.path.length; i++) {
            assertEquals(expected.path[i].x, actual.path[i].x);
            assertEquals(expected.path[i].y, actual.path[i].y);
        }
    }

    @Test
    void testPath() {
        boolean map[][] = {{true, true},
                {true, true}};
        Point source = new Point(0, 0);
        Point destination = new Point(0, 1);
        Path actual = new Path(map, source, destination);
        for (int i = 0; i < actual.path.length; i++) {
            assertEquals(true, map[actual.path[i].x][actual.path[i].y]);
        }
    }
}
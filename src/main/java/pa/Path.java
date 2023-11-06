package pa;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Path {
    static Node[] shortestPath;
    static int mazeRow;
    static int mazeCol;

    static int[] rowNum = {-1, 0, 0, 1};
    static int[] colNum = {0, -1, 1, 0};

    static boolean isValid(int row, int col) {
        return (row >= 0) && (row < mazeRow) &&
                (col >= 0) && (col < mazeCol);
    }

    static class QueueNode {
        Node node;
        List<Node> pastNode;

        public QueueNode(Node node, List<Node> pastNode) {
            this.node = node;
            this.pastNode = pastNode;
        }
    }

    Path(Node source, Node destination, int[][] maze) {
        if (maze[source.x][source.y] == 0 || maze[destination.x][destination.y] == 0 || (source.x == destination.x && source.y == destination.y)) {
            shortestPath = null;
            return;
        }

        mazeRow = maze.length;
        mazeCol = maze[0].length;
        boolean[][] visited = new boolean[mazeRow][mazeCol];
        visited[source.x][source.y] = true;

        Queue<QueueNode> q = new LinkedList<>();
        QueueNode s = new QueueNode(source, new ArrayList<>());
        q.add(s);

        while (!q.isEmpty()) {
            QueueNode c = q.peek();
            Node currNode = c.node;

            if (currNode.x == destination.x && currNode.y == destination.y) {
                shortestPath = new Node[c.pastNode.size()];
                for (int i = 0; i < c.pastNode.size(); i++)
                    shortestPath[i] = c.pastNode.get(i);
                return;
            }
            q.remove();

            for (int i = 0; i < 4; i++) {
                int row = currNode.x + rowNum[i];
                int col = currNode.y + colNum[i];

                if (isValid(row, col) &&
                        maze[row][col] == 1 &&
                        !visited[row][col]) {
                    visited[row][col] = true;
                    QueueNode neighboursNode = new QueueNode
                            (new Node(row, col), c.pastNode);
                    neighboursNode.pastNode.add(c.node);
                    q.add(neighboursNode);
                }
            }
        }
    }

    void displayPath()
    {

    }
}
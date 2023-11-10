package pa;

import com.opencsv.CSVReader;

import java.io.FileReader;
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

    static void readMazeFile(String file)
    {
        try {
            FileReader filereader = new FileReader(file);
            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;
            ArrayList<int[]> mazeList = new ArrayList<>();
            while ((nextRecord = csvReader.readNext()) != null) {
                int[] m = new int[nextRecord.length];
                for (int i=0; i<nextRecord.length; i++) {
                    m[i] =Integer.parseInt(nextRecord[i].replaceAll("\\s+",""));
                }
                mazeList.add(m);
            }
            int[][] maze= new int[mazeList.size()][mazeList.get(0).length];
            for (int i = 0; i < mazeList.size(); i++)
            {
                System.arraycopy(mazeList.get(i), 0, maze[i], 0, mazeList.get(i).length);
            }

//            for (int i = 0; i < maze.length; i++)
//            {
//                for (int j = 0; j < maze[i].length; j++)
//                {
//                    System.out.print(maze[i][j]+" ");
//                }
//                System.out.println("");
//            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class QueueNode {
        Node node;
        List<Node> pastNode;

        public QueueNode(Node node, List<Node> pastNode) {
            this.node = node;
            this.pastNode = new ArrayList<Node>(pastNode);

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

    public static void main(String[] args)
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
        for (Node node : shortestPath)
        {System.out.print(node+ "\t");}
    }
}


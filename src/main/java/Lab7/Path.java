package Lab7;


public class Path {
    public Point path[];

    public Path(boolean map[][], Point source, Point destination) {
        path = new Point[2];
        path[0] = source;
        path[1] = destination;
    }

    public Path(Point path[]) {
        this.path = new Point[path.length];
        for (int i = 0; i < path.length; i++) {
            this.path[i] = new Point(path[i]);
        }
    }
}

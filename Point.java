public class Point {
    private double[] coordinates;

    public Point(double...coordinates) {
        setCoordinatesCore(coordinates);
    }
    public double[] getCoordinates() {
        return this.coordinates;
    }
    public void setCoordinates(double...coordinates) {
        setCoordinatesCore(coordinates);
    }
    private void setCoordinatesCore(double...coordinates) {
        int countOfCoordinates = coordinates.length;
        this.coordinates = new double[countOfCoordinates];
        int i = 0;
        for(double coordinate: coordinates) {
            this.coordinates[i] = coordinate;
            i++;
        }
    }
}

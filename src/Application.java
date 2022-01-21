public class Application {
    public static void main(String [] args) {
        Point somePoint = new Point(2, 1, 4);
        Vector someVector = new Vector(2.0, 3.0, 5.0);

        Point point1 = new Point(1, 3, 5);
        Point point2 = new Point(2, 7, 3);
        Vector vector1 = new Vector(4, 2, 6);
        Vector vector2 = new Vector(point1, point2);
        Vector vector3 = new Vector(7, 8, 1);
        Vector vector4 = new Vector(3, 2, 9);
        Vector vector5 = new Vector(16, 24);

        for(int i = 0; i < vector1.getCountOfCoordinates(); i++) {
            System.out.println(vector1.getCoordinates()[i]);
        }

        double[] guidingCosines = vector2.getGuidingCosines();
        for(int i = 0; i < guidingCosines.length; i++) {
            System.out.println(guidingCosines[i]);
        }

        for(int i = 0; i < vector1.getCountOfCoordinates(); i++) {
            System.out.println(vector1.subtract(vector2, 1.5, 4)[i]);
        }

        System.out.println(vector1.getCosAngleBetweenVectors(vector3));

        System.out.println(vector1.getMixedProduct(vector3, vector4));

        System.out.println(vector1.checkCollinearity(vector4));

        System.out.println(vector1.getParallelogramArea(vector4));

        System.out.println(vector1.getPyramidVolume(vector3, vector4));




        double[] row1 = {1,3,6};
        double[] row2 = {7,1,7};
        double[] row3 = {1,2,9};
        double[] row4 = {4,1,10};
        Matrix someMatrix = new Matrix(row1, row2, row3, row4);

        double[] row5 = {4,5};
        double[] row6 = {3,5};
        Matrix someMatrix2 = new Matrix(row5, row6);
        Matrix someMatrix3 = new Matrix(row1, row2, row3);

        /*for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 3; j++) {
                System.out.print(someMatrix.getElements()[i][j] + " ");
            }
            System.out.println();
        }*/
        System.out.println(someMatrix3.getDeterminant());

    }
}
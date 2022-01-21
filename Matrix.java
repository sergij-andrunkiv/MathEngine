public class Matrix {
    private double[][] elements;

    public Matrix(double[]...rows) {  //   { (1,2,3), (3,5,1), (5,7,9), (4,1,8) }
        setElementsCore(rows);
    }
    public double[][] getElements() {
        return this.elements;
    }
    public void setElements(double[]...rows) {
        setElementsCore(rows);
    }
    private void setElementsCore(double[]...rows) {
        int countOfRows = rows.length;
        int countOfElementsInRow = rows[0].length;

        this.elements = new double[countOfRows][countOfElementsInRow]; // [4][3]
        int i = 0;
        for(double[] row: rows) {
            int j = 0;
            for(double element: row) {
                this.elements[i][j] = element;
                j++;
            }
            i++;
        }
    }

    public double getDeterminant() {
        double determinant = 0;
        if(this.elements[0].length == this.elements.length) {
            if(this.elements.length == 2) {
                determinant = (this.elements[0][0] * this.elements[1][1]) - (this.elements[0][1] * this.elements[1][0]);
                return determinant;
            }
            else if(this.elements.length == 3) {
                determinant = (this.elements[0][0] * this.elements[1][1] * this.elements[2][2]) +
                              (this.elements[0][2] * this.elements[1][0] * this.elements[2][1]) +
                              (this.elements[0][1] * this.elements[1][2] * this.elements[2][0]) -
                              (this.elements[0][2] * this.elements[1][1] * this.elements[2][0]) -
                              (this.elements[0][1] * this.elements[1][0] * this.elements[2][2]) -
                              (this.elements[0][0] * this.elements[1][2] * this.elements[2][1]);
                return determinant;
            }
        }
        return determinant;
    }
}

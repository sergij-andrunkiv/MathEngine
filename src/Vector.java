public class Vector {
    private double[] coordinates;
    private int countOfCoordinates;

    public Vector(double...coordinates) {
        setCoordinatesCore(coordinates);
    }
    public Vector(Point firstPoint, Point secondPoint) {
        setCoordinatesCore(firstPoint, secondPoint);
    }

    public double[] getCoordinates() {
        return this.coordinates;
    }
    public int getCountOfCoordinates() {
        return this.countOfCoordinates;
    }
    private void setCoordinatesCore(double...coordinates) {
        this.countOfCoordinates = coordinates.length;
        this.coordinates = new double[countOfCoordinates];
        int i = 0;
        for(double coordinate: coordinates) {
            this.coordinates[i] = coordinate;
            i++;
        }
    }
    private void setCoordinatesCore(Point firstPoint, Point secondPoint) {
        if(firstPoint.getCoordinates().length == secondPoint.getCoordinates().length) {
            this.coordinates = new double[firstPoint.getCoordinates().length];
            double[] firstPointCoordinates = firstPoint.getCoordinates();
            double[] secondPointCoordinates = secondPoint.getCoordinates();
            for(int i = 0; i < firstPoint.getCoordinates().length; i++) {
                this.coordinates[i] = secondPointCoordinates[i] - firstPointCoordinates[i];
            }
            this.countOfCoordinates = this.coordinates.length;
        }
    }

    public double getLength() {
        double length = 0;
        for(double coordinate: this.coordinates) {
            length += coordinate * coordinate;
        }
        return Math.sqrt(length);
    }

    public double[] getGuidingCosines() {
        double length = getLength();
        double[] guidingCosinesArray = new double[this.countOfCoordinates];
        int i = 0;
        for(double coordinate: this.coordinates) {
            guidingCosinesArray[i] = coordinate/length;
            i++;
        }
        return guidingCosinesArray;
    }

    public double[] add(Vector secondVector, double...multipliers) {
        return calculateCore(true, secondVector, multipliers);
    }
    public double[] subtract(Vector secondVector, double...multipliers) {
        return calculateCore(false, secondVector, multipliers);
    }
    private double[] calculateCore(boolean controller, Vector secondVector, double...multipliers) {
        double firstMultiplier = 1;
        double secondMultiplier = 1;
        if(multipliers.length > 1) {
            firstMultiplier = multipliers[0];
            secondMultiplier = multipliers[1];
        }
        else if(multipliers.length == 1) {
            firstMultiplier = multipliers[0];
        }
        int countOfCoordinatesSecondVector = secondVector.getCountOfCoordinates();
        double[] resultVectorCoordinatesArray = new double[this.countOfCoordinates];
        if(this.countOfCoordinates == countOfCoordinatesSecondVector) {
            for(int i = 0; i < this.countOfCoordinates; i++) {
                if(controller) {
                    resultVectorCoordinatesArray[i] = this.coordinates[i] * firstMultiplier + secondVector.getCoordinates()[i] * secondMultiplier;
                } else {
                    resultVectorCoordinatesArray[i] = this.coordinates[i] * firstMultiplier - secondVector.getCoordinates()[i] * secondMultiplier;
                }
            }
        }
        return resultVectorCoordinatesArray;
    }

    public double[] multiplyOnNumber(double number) {
        double[] coordinates = getCoordinates();
        int i = 0;
        for(double coordinate: coordinates) {
            coordinates[i] = coordinate * number;
            i++;
        }
        return coordinates;
    }

    public double getScalar(Vector secondVector) {
        double scalar = 0;
        if(getCountOfCoordinates() == secondVector.getCountOfCoordinates()) {
            for(int i = 0; i < getCountOfCoordinates(); i++) {
                scalar += getCoordinates()[i] * secondVector.getCoordinates()[i];
            }
        }
        return scalar;
    }

    public double getCosAngleBetweenVectors(Vector secondVector) {
        double scalar = getScalar(secondVector);
        return scalar / (getLength() * secondVector.getLength());
    }

    public double getProjectionOnSecondVector(Vector secondVector) {
        double scalar = getScalar(secondVector);
        double secondVectorLength = secondVector.getLength();
        return scalar / secondVectorLength;
    }

    public double[] getVectorProduct(Vector secondVector) {
        double[] vectorProduct = new double[3];
        if(getCountOfCoordinates() == 3 && secondVector.getCountOfCoordinates() == 3) {
            for(int i = 0; i < 3; i++) {
                vectorProduct[i] = switch (i) {
                    case 0 -> (getCoordinates()[1] * secondVector.getCoordinates()[2]) - (getCoordinates()[2] * secondVector.getCoordinates()[1]);
                    case 1 -> -((getCoordinates()[0] * secondVector.getCoordinates()[2]) - (getCoordinates()[2] * secondVector.getCoordinates()[0]));
                    case 2 -> (getCoordinates()[0] * secondVector.getCoordinates()[1]) - (getCoordinates()[1] * secondVector.getCoordinates()[0]);
                    default -> throw new IllegalStateException("Unexpected value");
                };
            }
            return vectorProduct;
        } else {
            throw new IllegalStateException("Unexpected value");
        }
    }

    public double getMixedProduct(Vector secondVector, Vector thirdVector) {
        double mixedProduct = 0;
        if(getCountOfCoordinates() == 3 && secondVector.getCountOfCoordinates() == 3 && thirdVector.getCountOfCoordinates() == 3) {
            for(int i = 0; i < 6; i++) {
                mixedProduct += switch (i) {
                    case 0 -> getCoordinates()[0] * secondVector.getCoordinates()[1] * thirdVector.getCoordinates()[2];
                    case 1 -> getCoordinates()[1] * secondVector.getCoordinates()[2] * thirdVector.getCoordinates()[0];
                    case 2 -> getCoordinates()[2] * secondVector.getCoordinates()[0] * thirdVector.getCoordinates()[1];
                    case 3 -> -(getCoordinates()[2] * secondVector.getCoordinates()[1] * thirdVector.getCoordinates()[0]);
                    case 4 -> -(getCoordinates()[1] * secondVector.getCoordinates()[0] * thirdVector.getCoordinates()[2]);
                    case 5 -> -(getCoordinates()[0] * secondVector.getCoordinates()[2] * thirdVector.getCoordinates()[1]);
                    default -> throw new IllegalStateException("Unexpected value");
                };
            }
            return mixedProduct;
        } else {
            throw new IllegalStateException("Unexpected value");
        }
    }

    public boolean checkCollinearity(Vector secondVector) {
        if(getCountOfCoordinates() == secondVector.getCountOfCoordinates()) {
            double runningRatioCoefficient = 0;
            for(int i = 0; i < getCountOfCoordinates(); i++) {
                if(i == 0) {
                    runningRatioCoefficient = getCoordinates()[i]/secondVector.getCoordinates()[i];
                } else {
                    if(runningRatioCoefficient == getCoordinates()[i]/secondVector.getCoordinates()[i]) {
                        runningRatioCoefficient = getCoordinates()[i]/secondVector.getCoordinates()[i];
                    } else {
                        return false;
                    }
                }
            }
            return true;
        } else {
            throw new IllegalStateException("Вектори різних розмірностей!");
        }
    }

    public boolean checkOrthogonality(Vector secondVector) {
        return getScalar(secondVector) == 0;
    }

    public boolean checkCoplanarity(Vector secondVector, Vector thirdVector) {
        return getMixedProduct(secondVector, thirdVector) == 0;
    }

    public double getTriangleArea(Vector secondVector) {
        return getAreaCore(secondVector, 1);
    }
    public double getParallelogramArea(Vector secondVector) {
        return getAreaCore(secondVector, 2);
    }
    private double getAreaCore(Vector secondVector, int controller) {
        Vector vectorProduct = new Vector(getVectorProduct(secondVector));
        double length = vectorProduct.getLength();
        if(controller == 1) return length / 2;
        else return length;
    }

    public double getPyramidVolume(Vector secondVector, Vector thirdVector) {
        return getMixedProduct(secondVector, thirdVector) / 6;
    }
}


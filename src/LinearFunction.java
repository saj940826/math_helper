/**
 * Created by 安捷 on 2016/7/6.
 */
public class LinearFunction {
    private float gradient;
    private float bias;

    public LinearFunction(float gradient, float bias) {
        this.gradient = gradient;
        this.bias = bias;
    }

    public static LinearFunction getLinearFunction(float[] pointA, float[] pointB) {
        float gradient = (pointA[1] - pointB[1]) / (pointA[0] - pointB[0]);
        float bias = pointA[1] - gradient * pointA[0];
        return new LinearFunction(gradient, bias);
    }

    public float[] calculateIntersection(LinearFunction linearFunction2) {
        try {
            float xSolution = (this.bias - linearFunction2.bias) / (linearFunction2.gradient - this.gradient);
            float ySolution = xSolution * this.gradient + bias;
            float[] solution = {xSolution, ySolution};
            return solution;
        } catch (Exception e) {
            float[] error = {-1, -1};
            return error;
        }
    }

    public float calculateY(float x) {
        return gradient * x - bias;
    }

    public LinearFunction getPerpendicularLine(float[] point) {
        float pGradient = -1 / gradient;
        float pBias = point[1] - pGradient * point[0];
        return new LinearFunction(pGradient, pBias);
    }

    public String toString(){
        String s = "y = "+gradient+" x + ( "+bias+" )";
        return s;
    }

    public float getDistanceFromPointToLine(float[] point) {
        LinearFunction perpendicular = this.getPerpendicularLine(point);
        float[] intersection = this.calculateIntersection(perpendicular);
        float distance = (float) Math.sqrt((double) (point[0]-intersection[0])*(point[0]-intersection[0])+(point[1]-intersection[1])*(point[1]-intersection[1]));
        return distance;
    }

    public static void main(String args[]) {
        System.out.println(getLinearFunction(new float[]{0, 0}, new float[]{1, 1}).getDistanceFromPointToLine(new float[]{0,2}));
    }
}

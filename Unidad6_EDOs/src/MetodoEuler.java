public class MetodoEuler {

    public static double evaluarFuncion(double x, double y) {
        // f(x, y) = x - y
        return x - y;
    }

    // Solución analítica exacta: y(x) = x - 1 + 2 * e^(-x)
    public static double solucionExacta(double x) {
        return x - 1.0 + 2.0 * Math.exp(-x);
    }

    public static void ejecutarEuler(double x0, double y0, double h, double xFinal) {
        // Criterio de paro (número total de pasos N)
        int nPasos = (int) Math.round((xFinal - x0) / h);
        double x = x0;
        double y = y0;

        System.out.printf("\n=== Método de Euler (h = %.4f, N = %d) ===\n", h, nPasos);
        System.out.println("n\t x_n\t\t y_n (Euler)\t y_exacta\t Error Abs");
        System.out.println("--------------------------------------------------------------------");

        double yExacta = solucionExacta(x);
        double errorAbs = Math.abs(yExacta - y);
        System.out.printf("%d\t %.4f\t %.6f\t %.6f\t %.6e\n", 0, x, y, yExacta, errorAbs);

        for (int i = 0; i < nPasos; i++) {
            double pendiente = evaluarFuncion(x, y);
            y = y + h * pendiente;
            x = x0 + (i + 1) * h; // Evita acumulación de error de redondeo en x

            yExacta = solucionExacta(x);
            errorAbs = Math.abs(yExacta - y);
            System.out.printf("%d\t %.4f\t %.6f\t %.6f\t %.6e\n", (i + 1), x, y, yExacta, errorAbs);
        }
    }

    public static void main(String[] args) {
        double x0 = 0.0;
        double y0 = 1.0;
        double xFinal = 0.3;

        // Experimentación variando h (0.1, 0.05, 0.01)
        double[] valoresH = {0.1, 0.05, 0.01};
        for (double h : valoresH) {
            ejecutarEuler(x0, y0, h, xFinal);
        }
    }
}
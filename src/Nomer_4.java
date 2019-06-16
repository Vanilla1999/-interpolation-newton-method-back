import javax.swing.*;
import java.io.File;
import java.io.PrintWriter;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Scanner;
public class Nomer_4 {
    public static final int K = 1001;
    private static Scanner scn;
    private static void Tabl( int n, double[][] y, double[] x) {
        for (int i = 1; i < n; i++) {
            for (int j = n-1; j >= i; j--) {
                y[j][i] = (y[j][i - 1] - y[j - 1]
                        [i - 1])/(x[j]-x[j-i]) ;
            }
        }
    }
    private static double X(double t,int j,int n,double[] x)
    {
        double sumX = 1;
        for (int i=0; i<j; i++) sumX = sumX*(t-x[n-i-1]);
        return sumX;
    }
    public static void main(String[] args)
    {
        try {
            scn = new Scanner(new File("your File address with a start values"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "\n" + "File not found");
        }
        scn.skip("Кол-во узлов ");
        int n = Integer.parseInt(scn.nextLine());
        double x[] = new double[n];
        double y[][] = new double[n ][n];
        scn.skip("Значение узлов");
        scn.nextLine();
        x[0] = Double.parseDouble(scn.next());
        for (int i = 1; i < n; i++) {
            x[i] = Double.parseDouble(scn.next());//read values X
        }
        scn.nextLine();
        scn.skip("y =");
        for (int i = 0; i < n; i++)
            y[i][0] = Double.parseDouble(scn.next());//read values Y

        System.out.println("");
        Tabl( n, y, x);

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j <= i; j++) {                                //Output rounded values
                DecimalFormat df = new DecimalFormat("#.####");
                df.setRoundingMode(RoundingMode.HALF_UP);
                String str1 = df.format(y[i][j]);
                System.out.print(str1 + "\t ");
            }
            System.out.println("");
        }
        try {
            File file;
            file = new File("The second File - output values");
            PrintWriter pw = new PrintWriter(file);
            double b[]=new double [K];
            b[0]=x[0];
            for ( int i = 1; i<K;i++)
                b[i]=b[i-1]+(x[n-1]-x[0])/1000;
            for ( int h=0;h<K;h++) {
                double value = b[h];
                double sum = y[n - 1][0];
                for (int i =1; i <n; i++) {

                    System.out.println("X="+X(value, i,n,x));
                    sum = sum + (y[n-1][i] * X(value, i,n,x));// Newton method
                    System.out.println("sum="+sum);
                }
                System.out.println("\n Value at " + value +
                        " is " + String.format("%.4g%n", sum));
                pw.println(String.format("%.4g",sum));
            }
            pw.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "File not found");}
        scn.close();

    }
}
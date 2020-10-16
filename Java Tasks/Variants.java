import java.util.Scanner;

public class Variants {
    public static void main(String [] args)
    {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        System.out.println(countVariants(n));
    }

    public static int countVariants(int n)
    {

        if (n < 1) return 0;

        int[] res = new int[n + 1];

        res[0] = 1;
        res[1] = 1;

        for (int i = 2; i <= n; i++) res[i] = res[i - 1] + res[i - 2];

        return res[n];
    }
}
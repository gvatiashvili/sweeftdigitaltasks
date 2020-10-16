import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class Split {
    public static void main(String [] args)
    {
        Scanner sc = new Scanner(System.in);
        int amount = sc.nextInt();
        System.out.println(Arrays.toString(minSplit(amount).toArray()));
    }

    private static ArrayList<Integer> minSplit(int amount)
    {
        int[] coins = new int[]{50, 20, 10, 5, 1};

        ArrayList<Integer> result = new ArrayList<>();

        for (int coin : coins)
        {

            int num = amount/coin;

            if (num > 0)
            {
                for (int i = 0; i < num; i++)
                {
                    result.add(coin);
                    amount -= coin;
                }
            }
        }
        return result;
    }
}

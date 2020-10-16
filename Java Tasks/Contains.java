import java.util.Scanner;
import java.util.ArrayList;

public class Contains {
    public static void main(String [] args)
    {

        Scanner sc = new Scanner(System.in);

        System.out.println("Number of Elements");

        int size = sc.nextInt();

        ArrayList<Integer> arr = new ArrayList<Integer>();

        for(int i = 0; i < size; i++)
        {

            System.out.println("Element" + (i+1));
            arr.add(sc.nextInt());

        }

        System.out.println(notContains(arr));

    }

    private static int notContains(ArrayList<Integer> arrlist)
    {

        int min = arrlist.get(0);

        for (int i = 1; i < arrlist.size(); i++)
        {

            if (min < 0 || min > arrlist.get(i))
            {
                min = arrlist.get(i);
            }
        }

        while (arrlist.indexOf(min + 1) != -1)  min++;

        if (arrlist.indexOf(1) == -1) min = 1;
        else  min += 1;

        return min;

    }
}

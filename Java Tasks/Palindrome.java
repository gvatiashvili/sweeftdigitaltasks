import java.util.Scanner;

public class Palindrome {
    public static void main(String [] args)
    {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        System.out.println(isPalindrome(str));
    }

    private static String isPalindrome(String str)
    {
        String revStr = "";

        for (int i = str.length() - 1; i >= 0; i--)
        {
                revStr += str.charAt(i);
        }

        str = str.toLowerCase();
        revStr = revStr.toLowerCase();

        if (str.equals(revStr)) return "Is Polindrome";
        else return "Is not Polindrome";
    }
}

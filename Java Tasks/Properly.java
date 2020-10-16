import java.util.Scanner;

public class Properly {
    public static void main(String [] args)
    {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        System.out.println(isProperly(str));
    }

    private static boolean isProperly(String str)
    {

        String sem = str;

        for (int i = 0; i < str.length(); i++)
        {
            String character = str.substring(i, i + 1);

            if (!character.equals("(") && !character.equals(")"))
            {
                sem = sem.replace(character, "");
            }
        }

        boolean noSemicolon = true;

        while (sem.indexOf("()") != -1)
        {
            noSemicolon = false;
            sem = sem.replace("()", "");
        }

        return sem.length() <= 0 && !noSemicolon;

    }
}

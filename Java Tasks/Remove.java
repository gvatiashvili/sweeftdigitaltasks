import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class Remove
{
    public static void main (String[] args)
    {
        DataStucture ds = new DataStucture();

        for (int i = 0; i < 10; i++) ds.add(i);

        int random = ds.getRandom();

        ds.remove(random);
    }
}

class DataStucture
{
    ArrayList<Integer> arr;

    HashMap<Integer, Integer> hash;

    public DataStucture()
    {
        arr = new ArrayList<Integer>();
        hash = new HashMap<Integer, Integer>();
    }

    void add(int x)
    {
        if (hash.get(x) != null)
            return;

        int s = arr.size();
        arr.add(x);

        hash.put(x, s);
    }

    void remove(int x)
    {
        Integer index = hash.get(x);
        if (index == null)
            return;

        hash.remove(x);

        int size = arr.size();
        Integer last = arr.get(size-1);
        Collections.swap(arr, index,  size-1);

        arr.remove(size-1);

        hash.put(last, index);
    }

    int getRandom()
    {
        Random rand = new Random();
        int index = rand.nextInt(arr.size());

        return arr.get(index);
    }
}
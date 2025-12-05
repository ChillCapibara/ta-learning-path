import java.util.Comparator;
import java.util.List;

public class SortUtils {

    public static <T> void sort(List<T> list, Comparator<? super T> comparator){
        list.sort(comparator);
    }

    public static <T> T max(List<T> list, Comparator<? super T> comparator){
        if (list.isEmpty())
            throw new IllegalArgumentException("List cannot be empty");
        T max = list.get(0);

        for (T item : list)
            if (comparator.compare(item, max) > 0)
                max  = item;
        return max;
    }
}

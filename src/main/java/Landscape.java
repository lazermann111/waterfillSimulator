import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Landscape {

    public static final int MAX_HEIGHT = 32000;
    public static final int MAX_WIDTH = 32000;
    public static final char GROUND = 'x';
    public static final char WATER = 'w';
    public static final char EMPTY = '-';

    private final List<Integer> landscape;
    private final char[][] array ;
    private final int width;
    private final int maxHeighth;

    public static void main(String ... args)
    {
        Landscape l = new Landscape(new int[]{3,1,4,2,0});
        l.validate();
        l.print();
    }

    public Landscape(int[] l) {
        this.landscape = Arrays.stream(l).boxed().collect(Collectors.toList());
        this.width = landscape.size();
        maxHeighth = landscape.stream().max(Comparator.naturalOrder()).get();
        array = new char[width][maxHeighth];
        fillArray();
    }

    private void fillArray()
    {
        for(int i = 0; i < array.length; i ++) {
            for (int j = 0; j < array[0].length; j++) {
                if (landscape.get(i) > j)
                    array[i][j] = GROUND;
                else
                    array[i][j] = EMPTY;
            }
        }
    }


    public void validate() {
        if (landscape.isEmpty() || landscape.size() > MAX_WIDTH)
            throw new IllegalArgumentException("Number of positions is out of limits: " + landscape.size());

        landscape.forEach(p -> {
            if (p < 0 || p > MAX_HEIGHT)
                throw new IllegalArgumentException("Max height is out of limits: " + p);
        });
    }
    public void calculateWater()
    {

    }


    public void print() {

        for(int i = 0; i < array.length ; i++)
        {
            for(int j = 0; j < array[0].length ; j++)
            {
                System.out.print(array[i][j]);
                System.out.print(' ');
            }
            System.out.println(' ');

        }
    }
}

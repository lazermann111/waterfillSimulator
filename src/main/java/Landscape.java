import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Landscape {

    public static final int MAX_HEIGHT = 32000;
    public static final int MAX_WIDTH = 32000;
    public static final char GROUND = 'G';
    public static final char WATER = 'W';
    public static final char EMPTY = '~';

    //user input
    private final List<Integer> landscape;
    // inner representation, used for water cell calculations
    private char[][] array;
    private final int maxHeight;
    private int waterCellsCount;

    public static void main(String... args) {
        Landscape l = new Landscape(new int[]{1, 1, 1, 10, 10, 1, 1, 1});

        l.print();
        System.out.println("***********");
        l.calculateWater();
        l.print();
        System.out.println("Water cells count: " + l.getWaterCellsCount());
    }

    public int getWaterCellsCount() {
        return waterCellsCount;
    }

    public Landscape(int[] l) {
        this.landscape = Arrays.stream(l).boxed().collect(Collectors.toList());
        int width = landscape.size();
        maxHeight = landscape.stream().max(Comparator.naturalOrder()).orElse(0);
        array = new char[width][maxHeight];
        validate();
        fillArray();
    }

    private void fillArray() {
        for (int width = 0; width < array.length; width++) {
            for (int height = 0; height < array[width].length; height++) {
                if (landscape.get(width) > height)
                    array[width][height] = GROUND;
                else
                    array[width][height] = EMPTY;
            }
        }
        array = rotateArray(array);
    }

    private static char [][] rotateArray(char [][] passedIn) {
        int h = passedIn[0].length;
        int w = passedIn.length;

        char[][] mirror = new char[h][w];
        for(int i = 0 ; i < h; i++){
            for(int j = 0 ; j < w; j++){
                mirror[i][j] = passedIn[j][h-i-1];
            }
        }
       return mirror;
    }


    public void validate() {

        if(maxHeight <= 0) throw new IllegalArgumentException("Max height is less than 1, makes no sense");

        if (landscape.isEmpty() || landscape.size() > MAX_WIDTH)
            throw new IllegalArgumentException("Number of positions is out of limits: " + landscape.size());

        landscape.forEach(p -> {
            if (p < 0 || p > MAX_HEIGHT)
                throw new IllegalArgumentException("Height is out of limits: " + p);
        });
    }


    public void calculateWater() {
        for (int i = 0; i < array.length; i++) {

            String rowToCheck = String.copyValueOf(array[i]);
            // here we are caching boundaries for given row, so in case
            // like this: G ~ ~ ~ G it will be calculated once instead of 3
            int tmpLeftBound = -1, tmpRightBound = -1;

            for (int j = 0; j < array[0].length; j++) {
                char toCheck = array[i][j];
                if (EMPTY != toCheck)
                {
                    continue;
                }

                //need to recalculate boundaries
                if(tmpRightBound == -1 || tmpLeftBound == -1  || j < tmpLeftBound || j > tmpRightBound)
                {
                    tmpLeftBound = rowToCheck.substring(0,j).lastIndexOf(GROUND);
                    tmpRightBound = rowToCheck.indexOf(GROUND, j);
                }

                if(tmpRightBound != -1 && tmpLeftBound != -1 && j > tmpLeftBound && j < tmpRightBound)
                {
                    array[i][j] = WATER;
                    waterCellsCount++;
                }
            }
        }
    }

    public void print()
    {
        Stream.of(array).forEach(e -> System.out.println(Arrays.toString(e)));
    }
}

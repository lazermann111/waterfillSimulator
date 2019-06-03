import org.junit.Assert;
import org.junit.Test;

public class LandscapeTests {

    @Test(expected = IllegalArgumentException.class)
    public void testIncorrectLandsapeHeighth() {
        Landscape landscape = new Landscape(new int[]{32001, 2, 3, 4, 5, 4, 0, 3, 1, 7});

    }

    @Test(expected = IllegalArgumentException.class)
    public void testIncorrectLandsapeHeighth2() {
        Landscape landscape = new Landscape(new int[]{-2, 2, 3, 4, 5, 4, 0, 3, 1, 7});

    }

    @Test(expected = IllegalArgumentException.class)
    public void testFlatLandscape() {
        Landscape landscape = new Landscape(new int[]{0, 0, 0});

    }

    @Test(expected = IllegalArgumentException.class)
    public void testIncorrectLandsapeWidth() {
        int[] array = new int[35000];
        for (int i = 0; i < 35000; i++) {
            array[i] = 1;
        }
        Landscape landscape = new Landscape(array);
    }

    @Test
    public void testPeakWithNoBoundaries() {

        //[~, ~, ~, G, G, ~, ~, ~]
        //[~, ~, ~, G, G, ~, ~, ~]
        //[~, ~, ~, G, G, ~, ~, ~]
        //[~, ~, ~, G, G, ~, ~, ~]
        //[~, ~, ~, G, G, ~, ~, ~]
        //[~, ~, ~, G, G, ~, ~, ~]
        //[~, ~, ~, G, G, ~, ~, ~]
        //[~, ~, ~, G, G, ~, ~, ~]
        //[~, ~, ~, G, G, ~, ~, ~]
        //[G, G, G, G, G, G, G, G]
        Landscape landscape = new Landscape(new int[]{1, 1, 1, 10, 10, 1, 1, 1});

        //same
        landscape.calculateWater();

        Assert.assertEquals(0, landscape.getWaterCellsCount());

    }

    @Test
    public void testExampleConfig() {

        //[G, ~, ~, ~, G, ~, ~, ~, ~]
        //[G, ~, ~, G, G, G, ~, ~, ~]
        //[G, ~, G, G, G, G, ~, G, ~]
        //[G, G, G, G, G, G, ~, G, ~]
        //[G, G, G, G, G, G, ~, G, G]
        Landscape landscape = new Landscape(new int[]{5, 2, 3, 4, 5, 4, 0, 3, 1});

        //[G, W, W, W, G, ~, ~, ~, ~]
        //[G, W, W, G, G, G, ~, ~, ~]
        //[G, W, G, G, G, G, W, G, ~]
        //[G, G, G, G, G, G, W, G, ~]
        //[G, G, G, G, G, G, W, G, G]
        landscape.calculateWater();

        Assert.assertEquals(9, landscape.getWaterCellsCount());


        //[~, ~, ~, ~, ~, ~, ~, ~, ~, G]
        //[~, ~, ~, ~, ~, ~, ~, ~, ~, G]
        //[G, ~, ~, ~, G, ~, ~, ~, ~, G]
        //[G, ~, ~, G, G, G, ~, ~, ~, G]
        //[G, ~, G, G, G, G, ~, G, ~, G]
        //[G, G, G, G, G, G, ~, G, ~, G]
        //[G, G, G, G, G, G, ~, G, G, G]
        Landscape landscape2 = new Landscape(new int[]{5, 2, 3, 4, 5, 4, 0, 3, 1, 7});

        //[~, ~, ~, ~, ~, ~, ~, ~, ~, G]
        //[~, ~, ~, ~, ~, ~, ~, ~, ~, G]
        //[G, W, W, W, G, W, W, W, W, G]
        //[G, W, W, G, G, G, W, W, W, G]
        //[G, W, G, G, G, G, W, G, W, G]
        //[G, G, G, G, G, G, W, G, W, G]
        //[G, G, G, G, G, G, W, G, G, G]
        landscape2.calculateWater();
        Assert.assertEquals(18, landscape2.getWaterCellsCount());


        //[~, ~, ~, G, ~, ~, ~, ~, ~, ~]
        //[~, ~, ~, G, ~, ~, ~, ~, ~, ~]
        //[G, ~, ~, G, ~, ~, ~, ~, ~, ~]
        //[G, ~, ~, G, ~, ~, ~, ~, ~, ~]
        //[G, ~, ~, G, ~, ~, ~, ~, ~, ~]
        //[G, ~, ~, G, ~, ~, ~, ~, ~, G]
        //[G, ~, ~, G, ~, ~, ~, ~, ~, G]
        //[G, ~, ~, G, G, ~, ~, ~, ~, G]
        //[G, ~, ~, G, G, G, ~, ~, ~, G]
        //[G, ~, G, G, G, G, ~, G, ~, G]
        //[G, G, G, G, G, G, ~, G, ~, G]
        //[G, G, G, G, G, G, ~, G, G, G]
        Landscape landscape3 = new Landscape(new int[]{10, 2, 3, 12, 5, 4, 0, 3, 1, 7});

        //[~, ~, ~, G, ~, ~, ~, ~, ~, ~]
        //[~, ~, ~, G, ~, ~, ~, ~, ~, ~]
        //[G, W, W, G, ~, ~, ~, ~, ~, ~]
        //[G, W, W, G, ~, ~, ~, ~, ~, ~]
        //[G, W, W, G, ~, ~, ~, ~, ~, ~]
        //[G, W, W, G, W, W, W, W, W, G]
        //[G, W, W, G, W, W, W, W, W, G]
        //[G, W, W, G, G, W, W, W, W, G]
        //[G, W, W, G, G, G, W, W, W, G]
        //[G, W, G, G, G, G, W, G, W, G]
        //[G, G, G, G, G, G, W, G, W, G]
        //[G, G, G, G, G, G, W, G, G, G]
        landscape3.calculateWater();
        Assert.assertEquals(37, landscape3.getWaterCellsCount());
    }
}

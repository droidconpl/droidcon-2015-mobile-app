package pl.droidcon.app.model.ui;


public class SwipeRefreshColorSchema {

    private int[] colors;
    private int currentIndex = 0;

    public SwipeRefreshColorSchema(int colorsCount) {
        colors = new int[colorsCount];
    }

    public SwipeRefreshColorSchema withColor(int color) {
        colors[currentIndex] = color;
        currentIndex++;
        return this;
    }

    public int[] getColors() {
        return colors;
    }
}

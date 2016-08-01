package black.target.deerlight.com.targetmoney.Constructs_class;

/**
 * Created by samuel_hsieh on 15/9/22.
 */
public class SlidingItems {

    private String title;
    private int resIcon;
    private int color;

    public SlidingItems(String title, int resIcon, int color) {
        this.title = title;
        this.resIcon = resIcon;
        this.color = color;
    }
    public SlidingItems(String title, int color) {
        this.title = title;
        this.color = color;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setResIcon(int resIcon) {
        this.resIcon = resIcon;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getTitle() {
        return title;
    }

    public int getResIcon() {
        return resIcon;
    }

    public int getColor() {
        return color;
    }

}

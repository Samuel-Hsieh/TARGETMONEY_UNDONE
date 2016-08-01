package black.target.deerlight.com.targetmoney.Constructs_class;

/**
 * Created by samuel_hsieh on 2015/10/28.
 */
public class SettingListItems {

    private String item;
    private String subtitle;
    private int icon;
    private int isInvisible;
    private boolean isListTitle = false;

    public SettingListItems(String item) {
        this.item = item;
        isListTitle = true;
    }

    public SettingListItems(String item, int icon, int isinvisible ,String subtitle) {
        this.item = item;
        this.icon = icon;
        this.isInvisible = isinvisible;
        this.subtitle = subtitle;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getisInvisible() {
        return isInvisible;
    }

    public void setisInvisible(int isInvisible) {
        this.isInvisible = isInvisible;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public boolean isListTitle() {
        return isListTitle;
    }

    public void setisListTitle(boolean isListTitle) {
        this.isListTitle = isListTitle;
    }
}

package black.target.deerlight.com.targetmoney.Constructs_class;

/**
 * Created by samuel_hsieh on 15/10/5.
 */
public class TargetListItems {
    int TargetCount;
    String TargetTitle;
    String TargetDetail;
    int CurrentMoney;
    long TargetMoney;
    String TargetColor;

    public TargetListItems(int targetCount, String targetTitle, String targetDetail, int currentMoney, long targetMoney, String targetColor) {
        TargetCount = targetCount;
        TargetTitle = targetTitle;
        TargetDetail = targetDetail;
        CurrentMoney = currentMoney;
        TargetMoney = targetMoney;
        TargetColor = targetColor;
    }

    public int getTargetCount() {
        return TargetCount;
    }

    public String getTargetTitle() {
        return TargetTitle;
    }

    public String getTargetDetail() {
        return TargetDetail;
    }

    public int getCurrentMoney() {
        return CurrentMoney;
    }

    public long getTargetMoney() {
        return TargetMoney;
    }

    public String getTargetColor() {
        return TargetColor;
    }

    public void setTargetCount(int targetCount) {
        TargetCount = targetCount;
    }

    public void setTargetTitle(String targetTitle) {
        TargetTitle = targetTitle;
    }

    public void setTargetDetail(String targetDetail) {
        TargetDetail = targetDetail;
    }

    public void setCurrentMoney(int currentMoney) {
        CurrentMoney = currentMoney;
    }

    public void setTargetMoney(int targetMoney) {
        TargetMoney = targetMoney;
    }

    public void setTargetColor(String targetColor) {
        TargetColor = targetColor;
    }
}

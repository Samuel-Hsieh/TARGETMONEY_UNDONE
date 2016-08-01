package black.target.deerlight.com.targetmoney.Constructs_class;

/**
 * Created by samuel_hsieh on 15/10/5.
 */
public class AuditListItems {

    int AuditCount;
    String AuditItem;
    double AuditPercent;
    long AuditMoney;

    public AuditListItems(int auditCount, String auditItem, double auditPercent, long auditMoney) {

        AuditCount = auditCount;
        AuditItem = auditItem;
        AuditPercent = auditPercent;
        AuditMoney = auditMoney;
    }

    public int getAuditCount() {
        return AuditCount;
    }

    public void setAuditCount(int auditCount) {
        AuditCount = auditCount;
    }

    public String getAuditItem() {
        return AuditItem;
    }

    public void setAuditItem(String auditItem) {
        AuditItem = auditItem;
    }

    public double getAuditPercent() {
        return AuditPercent;
    }

    public void setAuditPercent(double auditPercent) {
        AuditPercent = auditPercent;
    }

    public long getAuditMoney() {
        return AuditMoney;
    }

    public void setAuditMoney(long auditMoney) {
        AuditMoney = auditMoney;
    }

}

package foo.zongzhe.map_reduce.phone_data;

public class PhoneData {
    private String num;
    private int flowUp;
    private int flowDown;
    private int flowTotal;
    private String ipAddr;
    private String webSite;

    public PhoneData(String num, int flowDown, int flowTotal, String ipAddr, String webSite) {
        this.num = num;
        this.flowDown = flowDown;
        this.flowTotal = flowTotal;
        this.ipAddr = ipAddr;
        this.webSite = webSite;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public int getFlowUp() {
        return flowUp;
    }

    public void setFlowUp(int flowUp) {
        this.flowUp = flowUp;
    }

    public int getFlowDown() {
        return flowDown;
    }

    public void setFlowDown(int flowDown) {
        this.flowDown = flowDown;
    }

    public int getFlowTotal() {
        return flowTotal;
    }

    public void setFlowTotal(int flowTotal) {
        this.flowTotal = flowTotal;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }
}

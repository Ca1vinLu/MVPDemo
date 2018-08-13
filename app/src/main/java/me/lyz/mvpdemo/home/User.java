package me.lyz.mvpdemo.home;

/**
 * @author LYZ 2018.08.14
 */
public class User {
    private String name;
    private String psd;

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPsd() {
        return psd == null ? "" : psd;
    }

    public void setPsd(String psd) {
        this.psd = psd;
    }
}

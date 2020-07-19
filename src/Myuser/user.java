package Myuser;

public class user {
    String name;
    String passward;
    String account;
    String signature;

    public user(String name, String passward, String account, String signature) {
        this.name = name;
        this.passward = passward;
        this.account = account;
        this.signature = signature;
    }

    public user(String name, String passward, String account) {
        this.name = name;
        this.passward = passward;
        this.account = account;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassward(String passward) {
        this.passward = passward;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getName() {
        return name;
    }

    public String getPassward() {
        return passward;
    }

    public String getAccount() {
        return account;
    }

    public String getSignature() {
        return signature;
    }
}

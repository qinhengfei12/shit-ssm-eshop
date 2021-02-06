package xyz.kmahyyg.eshopdemo.enums;

public enum UserStatusEnum {
    NORMAL(0),
    ACCOUNT_EXPIRED(1),
    LOCKED(2),
    CRED_EXPIRED(4);

    private final int status_value;
    private UserStatusEnum(int i) {
        this.status_value = i;
    }

    public int getStatus() {
        return this.status_value;
    }
}

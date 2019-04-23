package kr.hs.dgsw.web01blog.Protocol;

public enum ResponseType {
    FAIL    (0, "Fail to run"),

    USER_GET (101, "user id [%d] GET"),
    USER_GETALL (102, "user get all"),
    USER_DELETE (103, "user id [%d] deleted"),
    USER_ADD    (104, "user id [%d] added"),
    USER_UPDATE (105, "user id [%d] updated"),

    POST_GET    (201, ""),
    POST_ADD    (202, ""),
    POST_UPDATE (203, ""),
    POST_DELETE (204, ""),

    ATTACHMENT_STORED   (301, ""),
    ATTACHMENT_DOWNLOAD (302, "")
    ;

    final private int code;
    final private String desc;

    ResponseType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int code() {
        return this.code;
    }

    public String desc() {
        return this.desc;
    }
}

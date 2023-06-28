package com.ymc.util;

public class R {
    private int status;
    private String msg;
    private Object data;

    public R(int status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    // Getter and Setter methods...

    // 添加静态网络状态返回码
    public static R success(Object data) {
        return new R(200, "Success", data);
    }

    public static R badRequest(String msg) {
        return new R(400, "Bad Request", null);
    }

    public static R unauthorized() {
        return new R(401, "Unauthorized", null);
    }

    public static R notFound() {
        return new R(404, "Not Found", null);
    }

    public static R internalServerError() {
        return new R(500, "Internal Server Error", null);
    }
}

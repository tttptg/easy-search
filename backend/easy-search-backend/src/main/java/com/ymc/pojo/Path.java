package com.ymc.pojo;

public class Path {
    private String path;
    private int type;

    public Path(String path, int type) {
        this.path = path;
        this.type = type;
    }

    public Path() {
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

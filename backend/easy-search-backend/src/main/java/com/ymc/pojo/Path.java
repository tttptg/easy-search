package com.ymc.pojo;

public class Path {
    private String path;
    private boolean type;

    public Path(String path, boolean type) {
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

    public boolean getType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }
}

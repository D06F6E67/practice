package com.lee.mybatis.enums;

/**
 * 启用禁用(角色表)
 */
public enum Enabled {
    /**
     * 禁用
     */
    DISABLED(0),
    /**
     * 启用
     */
    ENABLED(1);

    private final int value;

    Enabled(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

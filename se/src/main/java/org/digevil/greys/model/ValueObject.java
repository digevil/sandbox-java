package org.digevil.greys.model;

import com.google.common.base.MoreObjects;

/**
 * Created by huangtao729 on 2017/12/12.
 */
public class ValueObject {
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    private String text;

    public ValueObject(int count, String text) {
        super();
        setCount(count);
        setText(text);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("count", getCount())
                .add("text", getText())
                .toString();
    }
}

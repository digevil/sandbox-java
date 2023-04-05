package org.digevil.sandbox.s.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public class User {

    private Integer id;
    private String uuid;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equal(id, user.id) && Objects.equal(uuid, user.uuid) && Objects.equal(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, uuid, name);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("idHash", uuid)
                .add("name", name)
                .toString();
    }

}

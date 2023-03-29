package org.digevil.sandbox.s.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public class User {

    private Integer id;
    private String idHash;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdHash() {
        return idHash;
    }

    public void setIdHash(String idHash) {
        this.idHash = idHash;
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
        return Objects.equal(id, user.id) && Objects.equal(idHash, user.idHash) && Objects.equal(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, idHash, name);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("idHash", idHash)
                .add("name", name)
                .toString();
    }

}

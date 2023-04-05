package org.digevil.sandbox.s.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public class User {

    private Integer id;
    private String uuid;
    private String name;

    private UserGender gender;

    public Integer getId() {
        return id;
    }

    public User setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getUuid() {
        return uuid;
    }

    public User setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public UserGender getGender() {
        return gender;
    }

    public User setGender(UserGender gender) {
        this.gender = gender;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equal(id, user.id) && Objects.equal(uuid, user.uuid) && Objects.equal(name, user.name) && Objects.equal(gender, user.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, uuid, name, gender);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("idHash", uuid)
                .add("name", name)
                .add("gender", gender)
                .toString();
    }
}

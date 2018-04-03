package org.digevil.web.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by huangtao729 on 2017/12/15.
 */
@Table
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "the database generated user id")
    private Integer id;

    @ApiModelProperty(notes = "name of the user")
    private String name;

    @ApiModelProperty(notes = "age of the user")
    private Integer age;

    @ApiModelProperty(notes = "birthday of the user")
    private Date birthday;

    @Version
    @ApiModelProperty(notes = "the auto generated version of the user")
    private Integer version;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

}

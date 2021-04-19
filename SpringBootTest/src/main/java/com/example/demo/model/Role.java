package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "t_role",schema = "parkinglot")
public class Role {
    public Role(){}
    @Id
    @Basic
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "rolename")
    private String rolename;

    @Column(name = "rolecode")
    private String rolecode;

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", rolename='" + rolename + '\'' +
                ", rolecode='" + rolecode + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getRolecode() {
        return rolecode;
    }

    public void setRolecode(String rolecode) {
        this.rolecode = rolecode;
    }

    public Role(String rolename, String rolecode) {
        this.rolename = rolename;
        this.rolecode = rolecode;
    }
}

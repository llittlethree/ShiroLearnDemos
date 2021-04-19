package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "t_permission",schema = "parkinglot")
public class Permission {
    public Permission(){}
    @Id
    @Basic
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "permissionname")
    private String permissionname;

    @Column(name = "role_id")
    private Integer roleId;

    public Permission(String permissionname, Integer roleId) {
        this.permissionname = permissionname;
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", permissionname='" + permissionname + '\'' +
                ", roleId=" + roleId +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPermissionname() {
        return permissionname;
    }

    public void setPermissionname(String permissionname) {
        this.permissionname = permissionname;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}

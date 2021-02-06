package xyz.kmahyyg.eshopdemo.model;

import lombok.Data;

import java.io.Serializable;

/**
 * null
 * @TableName sys_users
 */
@Data
public class SysUsers implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String username;

    private String uid;

    private Long phone;

    private String password;

    private String role;

    private String avatar;

    private Integer preferPayment;

    private Integer preferDelivery;

    private String addr;

    private Integer status;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SysUsers other = (SysUsers) that;
        return (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
            && (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getRole() == null) ? 0 : getRole().hashCode());
        result = prime * result + ((getAvatar() == null) ? 0 : getAvatar().hashCode());
        result = prime * result + ((getPreferPayment() == null) ? 0 : getPreferPayment().hashCode());
        result = prime * result + ((getPreferDelivery() == null) ? 0 : getPreferDelivery().hashCode());
        result = prime * result + ((getAddr() == null) ? 0 : getAddr().hashCode());
        return result;
    }

}
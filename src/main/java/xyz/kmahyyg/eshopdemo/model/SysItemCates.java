package xyz.kmahyyg.eshopdemo.model;

import lombok.Data;

import java.io.Serializable;

/**
 * null
 * @TableName sys_item_cates
 */
@Data
public class SysItemCates implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

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
        SysItemCates other = (SysItemCates) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        return result;
    }

}
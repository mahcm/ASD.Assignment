/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author mah
 */
@Embeddable
public class ItemArrangementPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "ITEMID")
    private String itemid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "ARR_ID")
    private String arrId;

    public ItemArrangementPK() {
    }

    public ItemArrangementPK(String itemid, String arrId) {
        this.itemid = itemid;
        this.arrId = arrId;
    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public String getArrId() {
        return arrId;
    }

    public void setArrId(String arrId) {
        this.arrId = arrId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemid != null ? itemid.hashCode() : 0);
        hash += (arrId != null ? arrId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemArrangementPK)) {
            return false;
        }
        ItemArrangementPK other = (ItemArrangementPK) object;
        if ((this.itemid == null && other.itemid != null) || (this.itemid != null && !this.itemid.equals(other.itemid))) {
            return false;
        }
        if ((this.arrId == null && other.arrId != null) || (this.arrId != null && !this.arrId.equals(other.arrId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ItemArrangementPK[ itemid=" + itemid + ", arrId=" + arrId + " ]";
    }
    
}

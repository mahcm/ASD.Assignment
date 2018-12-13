/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mah
 */
@Entity
@Table(name = "ITEM_ARRANGEMENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemArrangement.findAll", query = "SELECT i FROM ItemArrangement i")
    , @NamedQuery(name = "ItemArrangement.findByItemid", query = "SELECT i FROM ItemArrangement i WHERE i.itemArrangementPK.itemid = :itemid")
    , @NamedQuery(name = "ItemArrangement.findByArrId", query = "SELECT i FROM ItemArrangement i WHERE i.itemArrangementPK.arrId = :arrId")
    , @NamedQuery(name = "ItemArrangement.findByQuantity", query = "SELECT i FROM ItemArrangement i WHERE i.quantity = :quantity")})
public class ItemArrangement implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ItemArrangementPK itemArrangementPK;
    @Column(name = "QUANTITY")
    private Integer quantity;

    public ItemArrangement() {
    }

    public ItemArrangement(ItemArrangementPK itemArrangementPK) {
        this.itemArrangementPK = itemArrangementPK;
    }

    public ItemArrangement(String itemid, String arrId) {
        this.itemArrangementPK = new ItemArrangementPK(itemid, arrId);
    }

    public ItemArrangementPK getItemArrangementPK() {
        return itemArrangementPK;
    }

    public void setItemArrangementPK(ItemArrangementPK itemArrangementPK) {
        this.itemArrangementPK = itemArrangementPK;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemArrangementPK != null ? itemArrangementPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemArrangement)) {
            return false;
        }
        ItemArrangement other = (ItemArrangement) object;
        if ((this.itemArrangementPK == null && other.itemArrangementPK != null) || (this.itemArrangementPK != null && !this.itemArrangementPK.equals(other.itemArrangementPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ItemArrangement[ itemArrangementPK=" + itemArrangementPK + " ]";
    }
    
}

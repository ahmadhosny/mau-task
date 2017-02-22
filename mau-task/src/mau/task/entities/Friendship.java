/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mau.task.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mdev
 */
@Entity
@Table(name = "friendship")
@XmlRootElement
public class Friendship implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "player_id")
    private Integer playerId;
    @Basic(optional = false)
    @Column(name = "friend_id")
    private int friendId;
    @Basic(optional = false)
    @Column(name = "status")
    private FriendshipStatus status;

    public Friendship() {
    }

    public Friendship(Integer playerId) {
        this.playerId = playerId;
    }

    public Friendship(Integer playerId, int friendId, FriendshipStatus status) {
        this.playerId = playerId;
        this.friendId = friendId;
        this.status = status;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public int getFriendId() {
        return friendId;
    }

    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }

    public FriendshipStatus getStatus() {
        return status;
    }

    public void setStatus(FriendshipStatus status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (playerId != null ? playerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Friendship)) {
            return false;
        }
        Friendship other = (Friendship) object;
        return !((this.playerId == null && other.playerId != null) || (this.playerId != null && !this.playerId.equals(other.playerId)));
    }

    @Override
    public String toString() {
        return "mau.task.entities.Friends[ playerId=" + playerId + " ]";
    }
    
}

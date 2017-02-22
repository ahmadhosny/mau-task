package mau.task.model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import mau.task.entities.Friendship;
import mau.task.entities.FriendshipStatus;
import mau.task.entities.Player;

/**
 * Contains the database operations for Friends
 *
 * @author Ahmad Hosny
 */
public class FriendshipModel {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("mau-taskPU");
    private final EntityManager em = emf.createEntityManager();

    public List<Player> getFriendsByPlayerID(Integer playerId) {

        Query query = em.createQuery("SELECT fr FROM Friendship AS fr WHERE FR.status = :status AND (FR.friendId = :plId OR FR.playerId = :plId)");
        query.setParameter("plId", playerId);
        query.setParameter("status", FriendshipStatus.CONFIRMED);
        List<Player> friendList = query.getResultList();

        return friendList;
    }

    public void requestFriendship(Integer playerId, Integer friendId) {
        Friendship friendshipRequest = new Friendship();
        friendshipRequest.setPlayerId(playerId);
        friendshipRequest.setFriendId(friendId);
        friendshipRequest.setStatus(FriendshipStatus.REQUESTED);
        em.getTransaction().begin();
        em.persist(friendshipRequest);
        em.getTransaction().commit();
    }

    public boolean respond(Integer responderId, Integer requesterId, String response) {

        Query query = em.createQuery("SELECT fr FROM Friendship AS fr WHERE FR.playerId = :pId AND FR.friendId = :fId AND FR.status = :status");
        query.setParameter("pId", responderId);
        query.setParameter("fId", requesterId);
        query.setParameter("status", FriendshipStatus.REQUESTED);

        Friendship friendship = (Friendship) query.getSingleResult();
        friendship.setPlayerId(responderId);
        friendship.setFriendId(requesterId);

        if (response.equals(FriendshipStatus.CONFIRMED.toString())) {
            friendship.setStatus(FriendshipStatus.CONFIRMED);
        } else if (response.equals(FriendshipStatus.REJECTED.toString())) {
            friendship.setStatus(FriendshipStatus.REJECTED);
        }
        em.getTransaction().begin();
        em.merge(friendship);
        em.getTransaction().commit();
        return true;
    }

}

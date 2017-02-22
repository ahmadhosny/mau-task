package mau.task.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import mau.task.entities.Player;

/**
 * Holds the database operations on the players.
 *
 * @author Ahmad Hosny
 */
public class PlayerModel {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("mau-taskPU");
    private final EntityManager em = emf.createEntityManager();

    public Player findPlayerByName(String playerName) {

        Query loginQuery = em.createQuery("SELECT pl FROM Player AS pl WHERE PL.name = :name");
        loginQuery.setParameter("name", playerName);

        return (Player) loginQuery.getSingleResult();
    }

}

package mau.task;

import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;
import java.util.List;
import mau.task.entities.Player;
import mau.task.model.FriendshipModel;
import mau.task.model.PlayerModel;

/**
 * Handles friendship requests between players.
 *
 * @author Ahmad Hosny
 */
public class FriendshipRequestHandler extends BaseClientRequestHandler {

    @Override
    public void handleClientRequest(User player, ISFSObject parameters) {
        String playerName = player.getName();
        String requestedFriendName = parameters.getText("requestedFriendName");

        PlayerModel playerModel = new PlayerModel();
        FriendshipModel friendshipModel = new FriendshipModel();

        Player requester = playerModel.findPlayerByName(playerName);
        Player requestedFriend = playerModel.findPlayerByName(requestedFriendName);

        ISFSObject returnedObj = new SFSObject();

        List<Player> friendList = friendshipModel.getFriendsByPlayerID(requester.getId());
        if (friendList != null && friendList.contains(requestedFriend)) {
            returnedObj.putText("result", "Friend request failed!");
        } else {
            friendshipModel.requestFriendship(requester.getId(), requestedFriend.getId());
            returnedObj.putText("result", "Friend request succeeded!");
        }

        send("friendshipRequest", returnedObj, player);
    }

}

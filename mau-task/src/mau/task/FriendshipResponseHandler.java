package mau.task;

import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;
import mau.task.entities.Player;
import mau.task.model.FriendshipModel;
import mau.task.model.PlayerModel;

/**
 * Handles friendship responses between players
 *
 * @author Ahmad Hosny
 */
public class FriendshipResponseHandler extends BaseClientRequestHandler {

    @Override
    public void handleClientRequest(User player, ISFSObject parameters) {
        String playerName = player.getName();
        String requestedFriendName = parameters.getText("requestedFriendName");
        String response = parameters.getText("response");

        PlayerModel playerModel = new PlayerModel();
        FriendshipModel friendshipModel = new FriendshipModel();

        Player responder = playerModel.findPlayerByName(playerName);
        Player requester = playerModel.findPlayerByName(requestedFriendName);

        ISFSObject returnedObj = SFSObject.newInstance();

        if (friendshipModel.respond(responder.getId(), requester.getId(), response)) {
            returnedObj.putText("result", "Friendship response succeeded");
        } else {
            returnedObj.putText("result", "Friendship response failed!");
        }
    }

}

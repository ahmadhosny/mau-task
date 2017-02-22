package mau.task;

import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;
import mau.task.entities.Player;
import mau.task.model.PlayerModel;

/**
 *
 * @author Ahmad Hosny
 */
class LoginHandler extends BaseClientRequestHandler {

    @Override
    public void handleClientRequest(User player, ISFSObject parameters) {
        PlayerModel playerModel = new PlayerModel();
        Player fetchedPlayer = playerModel.findPlayerByName(player.getName());

        ISFSObject resultObj = SFSObject.newInstance();
        if (fetchedPlayer != null) {
            resultObj.putText("result", "Log in succeeded");
        } else {
            resultObj.putText("result", "Log in failed!");
        }

        send("login", resultObj, player);
    }

}

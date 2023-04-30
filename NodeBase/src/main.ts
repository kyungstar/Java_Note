
import Config from "../Config";
import UserLoader from "./ServerLoader/Target/User";
import Logger from "./modules/Logger"

(async function () {

    // 고객
    if (["USER"].indexOf(Config.SERVER_TYPE) >= 0) {
        Logger.info(Config.SERVER_TYPE + ' Is Loading')
        await UserLoader();
    }

})();

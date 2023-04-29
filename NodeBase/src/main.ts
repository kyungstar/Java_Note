
import Config from "../Config";
import UserLoader from "./ServerLoader/Target/User";
import AdminLoader from "./ServerLoader/Target/Admin";
import DFSLoader from "./ServerLoader/Target/DFS";
import DBLoader from "./ServerLoader/Target/MariaDB";
import Logger from "./Module/Logger";

(async function () {

    // 고객
    if (["USER"].indexOf(Config.SERVER_TYPE) >= 0) {
        Logger.info(Config.SERVER_TYPE + ' Is Loading')
        Logger.info('DataBase is Loading')
        await DBLoader();
        await UserLoader();
    }

})();

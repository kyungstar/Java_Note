
import dotenv from "dotenv";
import path from "path";
import os from "os";

process.env.ROOT_PATH = path.join(__dirname, "..");
let envFound = dotenv.config({path: __dirname + "/.env." + process.argv[2].toLowerCase()});

if (envFound.error) {
    // 설정 로드 못함. 실행 실패
    throw new Error("Couldn't find .env file");
}



class Config {

    // Global
    PORT: number;
    SERVER_TYPE: string;
    DEFAULT_FILE_PATH: string;
    FILE_SIZE: number;

    DB: {
        DATABASE: string;
        PASSWORD: string;
        PORT: number;
        HOST: string;
        USER: string;
        CONNECTION_LIMIT: string;
        ENTITY_PATH: string;
    };

    //JWT
    JWT: {
        SECRET: string,
        EXPIRES_IN: string
    };

    // LOG
    LOG: {
        LOG_PATH: string;
        LEVEL: string;
        FILE_SIZE: string;
        FILE_CNT: string;
    }

    constructor() {

        // Global
        this.PORT = parseInt(process.env.PORT, 10);
        this.SERVER_TYPE = process.env.SERVER_TYPE
        this.DEFAULT_FILE_PATH = process.env.DEFAULT_FILE_PATH
        this.FILE_SIZE = parseInt(process.env.FILE_SIZE);

        this.JWT = {
            SECRET: process.env.JWT_SECRET,
            EXPIRES_IN: process.env.JWT_EXPIRES_IN
        };

        this.LOG = {
            LOG_PATH: process.env.LOG_PATH,
            LEVEL: process.env.LEVEL,
            FILE_SIZE: process.env.FILE_SIZE,
            FILE_CNT: process.env.FILE_CNT
        }

        this.DB = {
            DATABASE: process.env.DATABASE,
            USER: process.env.DB_USER,
            PASSWORD: process.env.DB_PASSWORD,
            PORT: parseInt(process.env.DB_PORT),
            HOST: process.env.DB_HOST,
            CONNECTION_LIMIT: process.env.CONNECTION_LIMIT,
            ENTITY_PATH: process.env.ENTITY_PATH
        }

    }
}

export default new Config();
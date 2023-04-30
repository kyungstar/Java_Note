
import {Request, Response} from "express";

const fs = require('fs');
const path = require('path');
const moment = require('moment')
import * as CryptoJS from 'crypto-js';
const { AES } = CryptoJS;

import { createConnection } from 'typeorm';
import {getConnection} from "typeorm";


import BaseController from '../Base/BaseController';

import Logger from "../../../modules/Logger";

import Config from "../../../../Config";

import DataChecker from "../../Util/DataChecker";
import {YgUserInfo} from "../../Data/Entity/User/User";



/*
import {ygUserInfo} from "../../Data/entity/User";
import {ygUserLogin} from "../../Data/entity/User";
import {createToken, JwtModel} from "../../../MiddleWare/JwtAuth";
import {SnakeNamingStrategy} from "typeorm-naming-strategies";
import {ygFile} from "../../Data/entity/File";
*/


const Date = moment().format('YYYYMMDD');

class UserController extends BaseController {


    public userEmail = async (req: Request, res: Response) => {

        try {

            let data = DataChecker.mergeObject(
                DataChecker.stringArrCheck(res, req.body, ['email'], true)
            ) as {
                email: string
            }

        } catch (err) {

        }


    }

    public userPhone = async (req: Request, res: Response) => {


    }

    public userJoin = async (req: Request, res: Response) => {
        Logger.info("Call API - " + req.originalUrl);

        // 트랜잭션

        try {

            let data = DataChecker.mergeObject(
                DataChecker.stringArrCheck(res, req.body, ['userName', 'phone', 'email', 'gender', 'loginId', 'password'], true)
            ) as {
                userName: string,
                phone: string,
                email: string,
                gender: string,
                loginId: string,
                password: string
            }


            console.log(Config.DB.ENTITY_PATH);


            // 데이터베이스 연결
            const connection = await createConnection();

            // 고객 이메일 중복검사
            const userRepository = await connection.getRepository(YgUserInfo);

            // 고객 userId 생성
            const text = data.userName + moment().format('YYYYMMDDHHMMSS') + data.email; // 암호화할 텍스트
            const key = 'kygNodeProject'; // 암호화에 사용할 키

            // 암호화
            const encrypted = CryptoJS.AES.encrypt(text, key).toString();
            let userId = Buffer.from(encrypted).toString('base64').slice(0, 45);

            // 고객이름  암호화
            let userName = CryptoJS.AES.encrypt(data.userName, key).toString();

            // 전화번호  암호화
            let userPhone = CryptoJS.AES.encrypt(data.phone, key).toString();

            // 비밀번호 암호화
            let password = CryptoJS.SHA256(data.password).toString();



            const user = new YgUserInfo();
            user.userId = userId;
            user.userName = userName;
            user.gender = data.gender;
            user.email = data.email;
            user.phone = userPhone;

            await userRepository.save(user);

           // const token = createToken(new JwtModel(({u: userId, t: userPhone} as JwtModel)));
           //  return token;

            return this.true(res, '01');

        } catch (err) {
            Logger.debug(err);
            return this.err(res, 'UF2', err)
        }
    }

}


export default new UserController();
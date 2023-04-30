import { Entity, Column, Index, PrimaryColumn, CreateDateColumn } from "typeorm";

@Entity()
export class YgUserInfo {
    @PrimaryColumn({ length: 45 })
    userId: string;

    @Index()
    @Column({ length: 100 })
    userName: string;

    @Index()
    @Column()
    gender: string;

    @Column({ length: 100 })
    phone: string;

    @Column()
    email: string;

    @CreateDateColumn({ type: "timestamp" })
    regDate: Date;
}

@Entity()
export class YgUserLogin {
    @PrimaryColumn({ length: 45 })
    userId: string;

    @Index()
    @Column({ length: 20 })
    loginId: string;

    @Column({ length: 50 })
    password: string;

    @Column({ default: 0 })
    tryCnt: number;

    @Column({ default: 0 })
    block: number;
}

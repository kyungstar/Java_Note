import { EntityRepository, Repository } from 'typeorm';
import { User } from '../../Data/Entity/User';

@EntityRepository(User)
export class UserRepository extends Repository<User> {
    // 사용자 정의 메서드 추가 가능
    findByAge(age: number): Promise<User[]> {
        return this.find({ where: { age } });
    }
}
package edu.hitsz.strategy;

import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;
import edu.hitsz.strategy.ShootStrategy;

import java.util.LinkedList;
import java.util.List;

public class RoundShootStrategy implements ShootStrategy {
    @Override
    public List<BaseBullet> shootAction(int locationX, int locationY, double speedX, double speedY, int direction) {
        int shootNum = 12;                    // 环形子弹数量
        int power = (direction > 0) ? 50 : 30;  // 敌人威力更高，和 Scatter 保持一致
        List<BaseBullet> res = new LinkedList<>();

        int x = locationX;
        int y = locationY + direction * 2;    // 向前偏移一点，避免和飞机重叠

        double bulletSpeed = 10.0;            // 环形子弹速度（可自行调整 8~15）
        double angleStep = 2 * Math.PI / shootNum;

        for (int i = 0; i < shootNum; i++) {
            // 从正上方开始，360° 均匀环形
            double angle = i * angleStep - Math.PI / 2.0;
            int speedx = (int) (bulletSpeed * Math.cos(angle));
            int speedy = (int) (bulletSpeed * Math.sin(angle));

            BaseBullet bullet;
            if (direction > 0) {
                bullet = new EnemyBullet(x, y, speedx, speedy, power);
            } else {
                bullet = new HeroBullet(x, y, speedx, speedy, power);
            }
            res.add(bullet);
        }
        return res;
    }
}

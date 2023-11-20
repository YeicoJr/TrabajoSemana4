package Cache;

import redis.clients.jedis.Jedis;

public class CacheBranch {
    private Jedis jedis;

    public CacheBranch() {
        this.jedis = new Jedis("localhost"); // Configura la conexión a la instancia de Redis
    }

    public void addToCache(int branchId, String branchInfo) {
        jedis.set("Branch:" + branchId, branchInfo);
    }

    public String getFromCache(int branchId) {
        return jedis.get("Branch:" + branchId);
    }
}

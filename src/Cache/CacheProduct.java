package Cache;

import redis.clients.jedis.Jedis;

public class CacheProduct {
    private Jedis jedis;

    public CacheProduct() {
        this.jedis = new Jedis("localhost"); // Configura la conexión a la instancia de Redis
    }

    public void addToCache(int productId, String productInfo) {
        jedis.set("Product:" + productId, productInfo);
    }

    public String getFromCache(int productId) {
        return jedis.get("Product:" + productId);
    }
}

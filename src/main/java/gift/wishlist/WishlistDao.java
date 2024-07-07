package gift.wishlist;

import gift.member.Member;
import gift.product.Product;
import gift.product.ProductDao;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
public class WishlistDao {

    private JdbcClient jdbcClient;

    public WishlistDao(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public void insertWish(Member member, Long productId) {
        var sql = """
            insert into wishlist (member_id, product_id) 
            values (?,?)
            """;
        jdbcClient.sql(sql)
            .param(member.getId())
            .param(productId)
            .update();
    }

    public List<Long> findAllWish() {
        var sql = """
            select product_id
            from wishlist
            """;
        return  jdbcClient.sql(sql).query(Long.class).list();

    }

  public Optional<Long> findProductById(Long product_id) {
        var sql = """
            select product_id 
            from wishlist 
            where product_id = ?
            """;
        return jdbcClient.sql(sql).param(product_id).query(Long.class).optional();
  }

    public void deleteWish(Long productId) {
        var sql = """
            delete from wishlist 
            where product_id = ?
            """;
        jdbcClient.sql(sql)
            .param(productId)
            .update();
    }
}

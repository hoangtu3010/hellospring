package aptech.t2008m.hellospring.article;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {
    @Query(value = "SELECT * FROM Article a WHERE a.title LIKE %:keyword% OR a.category LIKE %:keyword%", nativeQuery = true)
    List<Article> search(@Param("keyword") String keyword);
}

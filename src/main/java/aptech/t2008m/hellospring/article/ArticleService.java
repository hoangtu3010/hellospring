package aptech.t2008m.hellospring.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> findAll(String keyword) {
        return articleRepository.search(keyword);
    }

    public Optional<Article> findById(int id) {
        return articleRepository.findById(id);
    }

    public Article save(Article account) {
        return articleRepository.save(account);
    }

    public void deleteById(int id) {
        articleRepository.deleteById(id);
    }
}

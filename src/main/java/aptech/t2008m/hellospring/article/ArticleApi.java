package aptech.t2008m.hellospring.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/articles")
public class ArticleApi {
    @Autowired
    ArticleService articleService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Article>> findAll(@RequestParam(defaultValue = "") String keyword) {
        return ResponseEntity.ok(articleService.findAll(keyword));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Article> save(@RequestBody Article article) {
        return ResponseEntity.status(HttpStatus.CREATED).body(articleService.save(article));
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public ResponseEntity<Article> update(@PathVariable int id, @RequestBody Article Article) {
        Optional<Article> optionalArticle = articleService.findById(id);

        if (!optionalArticle.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        Article existArticle = optionalArticle.get();
        existArticle.setTitle(Article.getTitle());
        existArticle.setDescription(Article.getDescription());
        existArticle.setImage(Article.getImage());
        existArticle.setContent(Article.getContent());
        existArticle.setCategory(Article.getCategory());
        existArticle.setStatus(Article.getStatus());

        return ResponseEntity.ok(articleService.save(existArticle));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<Article> findById(@PathVariable int id) {
        Optional<Article> optionalArticle = articleService.findById(id);

        if (!optionalArticle.isPresent()) {
            ResponseEntity.notFound();
        }

        return ResponseEntity.ok(optionalArticle.get());
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable int id) {
        Optional<Article> optionalArticle = articleService.findById(id);

        if (!optionalArticle.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        }

        articleService.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body(true);
    }
}

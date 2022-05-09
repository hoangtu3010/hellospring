package aptech.t2008m.hellospring.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/orders")
public class OrderApi {
    @Autowired
    private OrderService orderService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Order>> findAll(){
        return ResponseEntity.ok(orderService.findAll());
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<Order> findById(int id){
        Optional<Order> optionalGrade = orderService.findById(id);

        if (!optionalGrade.isPresent()) {
            ResponseEntity.notFound();
        }

        return ResponseEntity.ok(optionalGrade.get());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Order> save(@RequestBody Order order){
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.save(order));
    }
}

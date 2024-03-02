import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClienteController {

    @GetMapping("/app")
    public String getHello() {
        return "Ola Spring Boot com Docker!";
    }
}

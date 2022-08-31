package flights.backend.amadeus;

import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.Location;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/amadeus")
public class AmadeusController {

    @GetMapping("/")
    public String hello() {
        return "Hello World Travel!";
    }

    @GetMapping("/locations")
    public Location[] locations(@RequestParam(required = true) String keyword) throws ResponseException {
        return AmadeusConnect.INSTANCE.location(keyword);
    }

}

package mate.academy.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.model.CinemaHall;
import mate.academy.model.dto.CinemaHallDto;
import mate.academy.service.CinemaHallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cinema-halls")
public class CinemaHallController {
    @Autowired
    private CinemaHallService cinemaHallService;

    @PostMapping
    public String addCinemaHall(@RequestBody CinemaHallDto cinemaHallDto) {
        cinemaHallService.add(convertFromDto(cinemaHallDto));
        return "Success";
    }

    @GetMapping
    public List<CinemaHallDto> getAllCinemaHalls() {
        return cinemaHallService.getAll().stream()
        .map(this::convertToDto)
        .collect(Collectors.toList());
    }

    private CinemaHall convertFromDto(CinemaHallDto cinemaHallDto) {
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(cinemaHallDto.getCapacity());
        cinemaHall.setDescription(cinemaHallDto.getDescription());
        return cinemaHall;
    }

    private CinemaHallDto convertToDto(CinemaHall cinemaHall) {
        CinemaHallDto cinemaHallDto = new CinemaHallDto();
        cinemaHallDto.setCapacity(cinemaHall.getCapacity());
        cinemaHallDto.setDescription(cinemaHall.getDescription());
        return cinemaHallDto;
    }
}

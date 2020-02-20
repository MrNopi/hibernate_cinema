package mate.academy.model.dto.request;

import java.util.List;
import mate.academy.model.dto.TicketDto;

public class OrderRequestDto {
    List<TicketDto> tickets;
    Long userId;

    public List<TicketDto> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketDto> tickets) {
        this.tickets = tickets;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}

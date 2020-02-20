package mate.academy.model.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import mate.academy.model.dto.TicketDto;

public class OrderResponseDto {
    List<TicketDto> tickets;
    LocalDateTime orderTime;
    Long userId;

    public List<TicketDto> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketDto> tickets) {
        this.tickets = tickets;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}

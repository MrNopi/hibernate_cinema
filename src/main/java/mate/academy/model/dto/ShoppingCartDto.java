package mate.academy.model.dto;

import java.util.List;

public class ShoppingCartDto {
    private List<TicketDto> tickets;

    public List<TicketDto> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketDto> tickets) {
        this.tickets = tickets;
    }

    public void addTicket(TicketDto ticketDto) {
        tickets.add(ticketDto);
    }
}

package ru.practicum.shareit.item.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import ru.practicum.shareit.request.model.Request;
import ru.practicum.shareit.user.model.User;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
@Table(name = "items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    @Positive
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Item name can't be null.")
    @Length(max = 255)
    private String name;

    @NotNull(message = "Item description can't be null.")
    @Length(max = 512)
    private String description;

    @Column(name = "is_available")
    @NotNull(message = "Item status can't be null.")
    private Boolean available;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @ManyToOne
    @JoinColumn(name = "request_id")
    private Request request;

    @NotNull (message = " price can't be null.")
    @Column(name ="price")
    @Digits(integer=10, fraction=2)
    private BigDecimal price;

    public Item(Integer id, String name, String description, Boolean available, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.available = available;
        this.price = price;
    }
}

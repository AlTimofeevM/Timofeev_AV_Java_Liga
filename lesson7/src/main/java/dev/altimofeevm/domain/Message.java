package dev.altimofeevm.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;

/**
 * Класс-сущность для таблицы Message
 */
@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="message")
public class Message {

    /**
     * Первичный ключ таблицы
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    private Long id;

    /**
     * Тело сообщения
     */
    @Column(name = "body")
    private String body;

    /**
     * Отправитель сообщения
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author")
    private User author;

    /**
     * Получатель сообщения
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "recipient")
    private User recipient;

    /**
     * Дата отправки
     */
    @Column(name = "date_of_sent")
    private Date dateOfSent;
}
package dev.altimofeevm.social.services.filter;

import dev.altimofeevm.social.domain.User;
import dev.altimofeevm.social.services.specification.BaseSpecification;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;



import static org.springframework.data.jpa.domain.Specification.where;

/**
 * Фильтр для реестра пользователей
 */
@Getter
@Setter
public class UserFilter implements Filter<User> {

    /**
     * Имя пользователя
     */
    private String firstName;

    /**
     * Фамилия пользователя
     */
    private String lastName;

    /**
     * Интересы
     */
    private String interests;

    /**
     * Город
     */
    private String city;

    @Override
    public Specification<User> toSpecification() {
        return where(BaseSpecification.<User>like("firstName", firstName))
                .and(BaseSpecification.like("lastName", lastName))
                .and(BaseSpecification.like("interests", interests))
                .and(BaseSpecification.like("city", city));
    }
}


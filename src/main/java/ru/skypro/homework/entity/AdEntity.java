package ru.skypro.homework.entity;

import lombok.*;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@EqualsAndHashCode(exclude = "pk")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AdEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;
    private Integer price;
    private String title;
    private String description;
    @ManyToOne
    @JoinColumn(name = "user_entity_id")
    private UserEntity userEntity;
    @OneToMany(mappedBy = "adEntity")
    private Collection<CommentEntity> commentEntities;
}

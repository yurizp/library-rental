package com.agriness.libraryrental.entity;

import com.agriness.libraryrental.config.ObjectUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "book_properties")
public class BookPropertyEntity {

    @Id()
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "book_id")
    private Long bookId;
    @Column(name = "key")
    private String key;
    @Column(name = "value")
    private String value;

    @SneakyThrows
    @Override
    public String toString() {
        return ObjectUtils.toString(this);
    }
}

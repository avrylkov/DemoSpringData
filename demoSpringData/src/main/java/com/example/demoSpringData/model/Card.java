package com.example.demoSpringData.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Card {

    @org.springframework.data.annotation.Id
    private long cardId;
    private String code;
    private String name;

    @Id
    @Column(name = "CARD_ID")
    public long getCardId() {
        return cardId;
    }

    public void setCardId(long cardId) {
        this.cardId = cardId;
    }

    @Basic
    @Column(name = "CODE")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return cardId == card.cardId &&
                Objects.equals(code, card.code) &&
                Objects.equals(name, card.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(cardId, code, name);
    }
}

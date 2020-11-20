/*
 * Copyright (C) 2020 Andre Kessler (https://github.com/goblingift)
 * All rights reserved
 */
package gift.goblin.quizgenerator.database.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 *
 * @author andre
 */
@Entity
@Table(name = "quizcard")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Quizcard.findAll", query = "SELECT q FROM Quizcard q"),
    @NamedQuery(name = "Quizcard.findById", query = "SELECT q FROM Quizcard q WHERE q.id = :id"),
    @NamedQuery(name = "Quizcard.findByQuestion", query = "SELECT q FROM Quizcard q WHERE q.question = :question"),
    @NamedQuery(name = "Quizcard.findByAnswer1", query = "SELECT q FROM Quizcard q WHERE q.answer1 = :answer1"),
    @NamedQuery(name = "Quizcard.findByAnswer2", query = "SELECT q FROM Quizcard q WHERE q.answer2 = :answer2"),
    @NamedQuery(name = "Quizcard.findByAnswer3", query = "SELECT q FROM Quizcard q WHERE q.answer3 = :answer3"),
    @NamedQuery(name = "Quizcard.findByCorrectAnswer", query = "SELECT q FROM Quizcard q WHERE q.correctAnswer = :correctAnswer"),
    @NamedQuery(name = "Quizcard.findByCreationDate", query = "SELECT q FROM Quizcard q WHERE q.creationDate = :creationDate")})
public class Quizcard implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Basic(optional = false)
    @Column(name = "question")
    private String question;
    
    @Basic(optional = false)
    @Column(name = "answer1")
    private String answer1;
    
    @Basic(optional = false)
    @Column(name = "answer2")
    private String answer2;
    
    @Basic(optional = false)
    @Column(name = "answer3")
    private String answer3;
    
    @Basic(optional = false)
    @Column(name = "correct_answer")
    private short correctAnswer;
    
    @Basic(optional = false)
    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Category categoryId;

    public Quizcard() {
    }

    public Quizcard(Integer id) {
        this.id = id;
    }

    public Quizcard(Integer id, String question, String answer1, String answer2, String answer3, short correctAnswer, Date creationDate) {
        this.id = id;
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.correctAnswer = correctAnswer;
        this.creationDate = creationDate;
    }
    
    @PrePersist
    void createDefaultValues() {
        this.creationDate = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public short getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(short correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Category getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Category categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Quizcard)) {
            return false;
        }
        Quizcard other = (Quizcard) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Quizcard{" + "id=" + id + ", question=" + question + ", answer1=" + answer1 + ", answer2=" + answer2 + ", answer3=" + answer3 + ", correctAnswer=" + correctAnswer + ", creationDate=" + creationDate + ", categoryId=" + categoryId + '}';
    }
    
}

package by.vstu.english_education.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "lesson")
public class Lesson extends AbstractEntity {
  @OneToMany(mappedBy = "lesson",cascade = CascadeType.ALL,orphanRemoval = true)
  private List<Test> tests;
  @ManyToOne()
  private User author;
  private String title;
  @Lob
  private String theory;
}
package io.lincu.domains.ghost;

import io.lincu.domains.Category;
import io.lincu.domains.Content;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Read only domain for ghost.posts table
 *
 * @author Keeun Baik
 */
@Data
@Entity
@Table(name = "posts")
public class Post {

    @Id @GeneratedValue
    private Long id;

    private String title;

    private String slug;

    private String image;

    private boolean featured;

    private String status;

    @Lob
    private String markdown;

    @Lob
    private String html;

    private Date createdAt;

    @ManyToOne
    @org.hibernate.annotations.ForeignKey(name = "none")
    @JoinColumn(name = "created_by",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private User createdBy;

    private Date updatedAt;

    @ManyToOne
    @org.hibernate.annotations.ForeignKey(name = "none")
    @JoinColumn(name = "updated_by",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private User updatedBy;

    private Date publishedAt;

    @ManyToOne
    @org.hibernate.annotations.ForeignKey(name = "none")
    @JoinColumn(name = "published_by",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private User publishedBy;

    @ManyToMany
    @JoinTable(name = "posts_tags",
            joinColumns = @JoinColumn(name = "post_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
    private List<Tag> tags;

    @OneToOne(mappedBy = "post")
    private Content content;

    public String getUrl() {
        return "http://localhost:2368/" + this.slug;
    }

    public String getShortDescr() {
        if (this.markdown.length() > 250) {
            return this.markdown.substring(0, 250).concat("...");
        }
        return this.markdown;
    }

    public boolean isSelected(Category category) {
        if (this.content == null) {
            return category.getName().equals(Category.UNCATEGORIZED);
        } else {
            return this.content.getCategory().equals(category);
        }
    }

    public String getCategoryName() {
        if (this.content == null) {
            return Category.UNCATEGORIZED;
        } else {
            return this.content.getCategory().getName();
        }
    }

}

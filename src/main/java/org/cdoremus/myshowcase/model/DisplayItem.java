package org.cdoremus.myshowcase.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.cdoremus.myshowcase.util.LocalDateTimeAttributeConverter;
import org.cdoremus.myshowcase.util.LocalDateTimeParseDeserializer;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 *
 *
 */
@Entity
public class DisplayItem {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long itemId;

    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;

    @Column(name="filename")
    private String filename;

    @Column(name="height")
    private int height;

    @Column(name="width")
    private int width;

    @Column(name="create_date")
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = LocalDateTimeParseDeserializer.class)
    private LocalDateTime createDate;

    @Column(name="is_visible")
    private boolean isVisible = true;

    @ManyToOne(optional=false)
    @JoinColumn(name="user_id",referencedColumnName="user_id", insertable = true, updatable = true)
    private User user;

    public DisplayItem() {
    }

    public DisplayItem(String title, String description, int height, int width, String filename, boolean isVisible, User user) {
        this.title = title;
        this.description = description;
        this.height = height;
        this.width = width;
        this.filename = filename;
        this.isVisible = isVisible;
        this.user = user;
    }



    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        return checkObjectEquals((DisplayItem) o);
    }

    private boolean checkObjectEquals(DisplayItem o) {
        DisplayItem that = o;

        if (height != that.height) return false;
        if (width != that.width) return false;
        if (isVisible != that.isVisible) return false;
        if (!itemId.equals(that.itemId)) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (filename != null ? !filename.equals(that.filename) : that.filename != null) return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        int result = itemId.hashCode();
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (filename != null ? filename.hashCode() : 0);
        result = 31 * result + height;
        result = 31 * result + width;
        result = 31 * result + (isVisible ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DisplayItem{" +
                "itemId=" + itemId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", height=" + height +
                ", width=" + width +
                ", filename=" + filename +
                ", createDate=" + createDate +
                ", isVisible=" + isVisible +
                ", user=" + user +
                '}';
    }
}

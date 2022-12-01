package com.memeit.image;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.memeit.post.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity

@Table(name = "Images")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class FileImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    private String filePath;
    @OneToOne(mappedBy = "fileImage")
    @JsonIgnore
    private Post post;

    @Override
    public String toString() {
        return "FileImage{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';


    }
}
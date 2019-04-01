package org.nclc.content;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.apache.log4j.Logger;


@Entity
@Table(name="tfile", uniqueConstraints = {
@UniqueConstraint(columnNames = "ID")})
public class TFile implements Serializable {

    /** log4j category */
    private static final Logger log = Logger.getLogger(Category.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Integer id;

    @Column(name="size_bytes")
    private Long size;

    @Column(name="checksum", length = 64)
    private String checksum;

    @Column(name="checksum_algorithm", length = 32)
    private String checksum_algorithm;

    @Column(name="description", length = 256)
    private String description;

    @Column(name="word_count")
    private Integer wordCount;

    @Column(name="single_character_count")
    private Integer singleCharacterCount;

    public TFile() {
    }

    public TFile(Long size, String checksum, String checksum_algorithm, String description, Integer wordCount, Integer singleCharacterCount) {
        this.size = size;
        this.checksum = checksum;
        this.checksum_algorithm = checksum_algorithm;
        this.description = description;
        this.wordCount = wordCount;
        this.singleCharacterCount = singleCharacterCount;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public String getChecksum_algorithm() {
        return checksum_algorithm;
    }

    public void setChecksum_algorithm(String checksum_algorithm) {
        this.checksum_algorithm = checksum_algorithm;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getWordCount() {
        return wordCount;
    }

    public void setWordCount(Integer wordCount) {
        this.wordCount = wordCount;
    }

    public Integer getSingleCharacterCount() {
        return singleCharacterCount;
    }

    public void setSingleCharacterCount(Integer singleCharacterCount) {
        this.singleCharacterCount = singleCharacterCount;
    }

    
}

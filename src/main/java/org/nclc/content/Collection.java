package org.nclc.content;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.log4j.Logger;
import org.hibernate.annotations.BatchSize;
import org.hibernate.proxy.HibernateProxyHelper;

@Entity
@Table(name="collection", uniqueConstraints = {
@UniqueConstraint(columnNames = "ID")})
public class Collection implements Serializable {

    /** log4j category */
    private static final Logger log = Logger.getLogger(Category.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="title", length = 50)
    private String title;

    @Column(name="description", length = 250)
    private String description;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    @BatchSize(size=100)
    @JoinTable(
        name = "category2collection",
        joinColumns = {@JoinColumn(name = "collection_id") },
        inverseJoinColumns = {@JoinColumn(name = "category_id") }
    )
    private Set<Category> categories = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "collections", cascade = {CascadeType.PERSIST})
    @BatchSize(size=100)
    private Set<ECase> ECases = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<Category> getCategories(){
        Category[] output = categories.toArray(new Category[]{});
        return Arrays.asList(output);        
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public void addCategory(Category category)
    {
        categories.add(category);
    }

    public Set<ECase> getECases() {
        return ECases;
    }

    public void setECases(Set<ECase> ECases) {
        this.ECases = ECases;
    }

    public void addECase(ECase eCase) {
        ECases.add(eCase);
    }

    @Override
    public boolean equals(Object other)
    {
        if (other == null)
        {
            return false;
        }
        Class<?> objClass = HibernateProxyHelper.getClassWithoutInitializingProxy(other);
        if (this.getClass() != objClass)
        {
            return false;
        }
        final Collection otherCollection = (Collection) other;
        if (!this.getId().equals(otherCollection.getId() ))
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(getId()).toHashCode();
    }
}

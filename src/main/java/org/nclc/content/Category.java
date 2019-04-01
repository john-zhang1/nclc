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
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.log4j.Logger;
import org.hibernate.annotations.BatchSize;
import org.hibernate.proxy.HibernateProxyHelper;

@Entity
@Table(name="category", uniqueConstraints = {
@UniqueConstraint(columnNames = "ID")})
public class Category implements Serializable {

    /** log4j category */
    private static final Logger log = Logger.getLogger(Category.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Integer id;
    
    @Column(name="title", nullable = false, length = 50)
    private String title;

    @Column(name="description", length = 250)
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @BatchSize(size=100)
    @JoinTable(
            name = "category2category",
            joinColumns = { @JoinColumn(name = "parent_cat_id") },
            inverseJoinColumns = { @JoinColumn(name = "child_cat_id") })
    private Set<Category> subCategories = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "subCategories")
    @BatchSize(size=100)
    private Set<Category> parentCategories = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "categories", cascade = {CascadeType.PERSIST})
    @BatchSize(size=100)
    private Set<Collection> collections = new HashSet<>();

    @Transient
    private boolean modified = false;

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

    public Set<Category> getSubCategories(){
        return subCategories;
    }

    public List<Category> getSubCategoryList()
    {
        Category[] output = subCategories.toArray(new Category[]{});
        return Arrays.asList(output);
    }

    public void addSubCategory(Category subCategory)
    {
        subCategories.add(subCategory);
    }

    public void removeSubCategory(Category subCategory)
    {
        subCategories.remove(subCategory);
        setModified();
    }

    public Set<Category> getParentCategories(){
        return parentCategories;
    }
    
    public List<Category> getParentCategoryList()
    {
        Category[] output = parentCategories.toArray(new Category[]{});
        return Arrays.asList(output);
    }

    public void addParentCategory(Category parentCategory) {
        parentCategories.add(parentCategory);
    }

    public void clearParentCategories(){
        parentCategories.clear();
    }

    public void removeParentCategory(Category parentCategory)
    {
        parentCategories.remove(parentCategory);
    }

    public Set<Collection> getCollections()
    {
        return collections;
    }

    public List<Collection> getCollectionList()
    {
        Collection[] output = collections.toArray(new Collection[]{});
        return Arrays.asList(output);
    }

    public void addCollection(Collection collection)
    {
        collections.add(collection);
    }

    public void removeCollection(Collection collection)
    {
        collections.remove(collection);
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
        final Category otherCategory = (Category) other;
        if (!this.getId().equals(otherCategory.getId() ))
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

    public boolean isModified() {
        return modified;
    }
    public void clearModified() {
        this.modified = false;
    }
    protected void setModified() {
        this.modified = true;
    }
}

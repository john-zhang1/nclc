package org.nclc.content;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.apache.log4j.Logger;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.proxy.HibernateProxyHelper;

@Entity
@Table(name="ecase", uniqueConstraints = {
@UniqueConstraint(columnNames = "ID")})
public class ECase implements Serializable {

    /** log4j category */
    private static final Logger log = Logger.getLogger(ECase.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToMany(fetch = FetchType.EAGER, cascade={CascadeType.PERSIST})
    @BatchSize(size=100)
    @JoinTable(
            name = "collection2ecase",
            joinColumns = {@JoinColumn(name = "ecase_id") },
            inverseJoinColumns = {@JoinColumn(name = "collection_id") }
    )
    private final Set<Collection> collections = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sample_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Sample sample = null;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "eperson_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private EPerson ePerson = null;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resource_file_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private TFile tFile = null;

    @Column(name="test_date", length = 250)
    private Date testDate;

    public ECase() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Sample getSample() {
        return sample;
    }

    public void setSample(Sample sample) {
        this.sample = sample;
    }

    public EPerson getEPerson() {
        return ePerson;
    }

    public void setEPerson(EPerson ePerson) {
        this.ePerson = ePerson;
    }

    public EPerson getePerson() {
        return ePerson;
    }

    public void setePerson(EPerson ePerson) {
        this.ePerson = ePerson;
    }

    public TFile getTFile() {
        return tFile;
    }

    public void setTFile(TFile tFile) {
        this.tFile = tFile;
    }

    public Date getTestDate() {
        return testDate;
    }

    public void setTestDate(Date testDate) {
        this.testDate = testDate;
    }

    public List<Collection> getCollections()
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

    public void clearCollections(){
        collections.clear();
    }

    @Override
    public boolean equals(Object obj)
     {
         if (obj == null)
         {
             return false;
         }
         Class<?> objClass = HibernateProxyHelper.getClassWithoutInitializingProxy(obj);
         if (this.getClass() != objClass)
         {
             return false;
         }
         final ECase otherECase = (ECase) obj;
         if (!this.getId().equals(otherECase.getId()))
         {
             return false;
         }

         return true;
     }

    public String getName()
    {
        return "";
    }

     @Override
     public int hashCode()
     {
         int hash = 5;
         hash += 71 * hash + getType();
         hash += 71 * hash + getId().hashCode();
         return hash;
     }

    public int getType()
    {
        return Constants.ECASE;
    }

}

package org.nclc.content;

import org.nclc.content.data.Gender;
import org.nclc.content.data.EducationalStage;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.proxy.HibernateProxyHelper;
import org.nclc.content.data.LanguageCode;

@Entity
@Table(name="eperson", uniqueConstraints = {
@UniqueConstraint(columnNames = "ID")})
public class EPerson implements Serializable {

    /** log4j category */
    private static final Logger log = Logger.getLogger(Category.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="email", unique=true, length = 64)
    private String email;

    @Column(name="firstname", length = 64)
    private String firstName;

    @Column(name="lastname", length = 64)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name="gender", length = 10)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "native_language", length = 10)
    private LanguageCode nativeLanguage;

    @Column(name="major", length = 64)
    private String major;
    
    @Enumerated(EnumType.STRING)
    @Column(name="educational_stage", length = 20)
    private EducationalStage educationalStage;

    @Column(name="years_of_learning", nullable = false)
    private BigDecimal yearsOfLearning;
    
    @Column(name="study_abroad", nullable = false)
    private Boolean hasStudiedAbroad;

    public EPerson() {
    }

    public EPerson(String email, String firstName, String lastName, Gender gender, LanguageCode nativeLanguage, String major, EducationalStage educationalStage, BigDecimal yearsOfLearning, Boolean hasStudiedAbroad) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.nativeLanguage = nativeLanguage;
        this.major = major;
        this.educationalStage = educationalStage;
        this.yearsOfLearning = yearsOfLearning;
        this.hasStudiedAbroad = hasStudiedAbroad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LanguageCode getNativeLanguage() {
        return nativeLanguage;
    }

    public void setNativeLanguage(LanguageCode nativeLanguage) {
        this.nativeLanguage = nativeLanguage;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public EducationalStage getEducationalStage() {
        return educationalStage;
    }

    public void setEducationalStage(EducationalStage educationalStage) {
        this.educationalStage = educationalStage;
    }

    public BigDecimal getYearsOfLearning() {
        return yearsOfLearning;
    }

    public void setYearsOfLearning(BigDecimal yearsOfLearning) {
        this.yearsOfLearning = yearsOfLearning;
    }

    public Boolean getHasStudiedAbroad() {
        return hasStudiedAbroad;
    }

    public void setHasStudiedAbroad(Boolean hasStudiedAbroad) {
        this.hasStudiedAbroad = hasStudiedAbroad;
    }

    public String getFullName()
    {
        String f = getFirstName();
        String l= getLastName();

        if ((l == null) && (f == null))
        {
            return getEmail();
        }
        else if (f == null)
        {
            return l;
        }
        else
        {
            return (f + " " + l);
        }
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        Class<?> objClass = HibernateProxyHelper.getClassWithoutInitializingProxy(obj);
        if (getClass() != objClass)
        {
            return false;
        }
        final EPerson other = (EPerson) obj;
        if (!Objects.equals(this.getId(), other.getId()))
        {
            return false;
        }
        if (!StringUtils.equals(this.getEmail(), other.getEmail()))
        {
            return false;
        }
        if (!StringUtils.equals(this.getFullName(), other.getFullName()))
        {
            return false;
        }
        return true;
    }

    /**
     * Return a hash code for this object.
     *
     * @return int hash of object
     */
    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 89 * hash + this.getId().hashCode();
        hash = 89 * hash + (this.getEmail() != null? this.getEmail().hashCode():0);
        hash = 89 * hash + (this.getFullName() != null? this.getFullName().hashCode():0);
        return hash;
    }    
}

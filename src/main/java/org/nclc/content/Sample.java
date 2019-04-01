package org.nclc.content;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import org.apache.log4j.Logger;
import org.hibernate.proxy.HibernateProxyHelper;

@Entity
@Table(name="sample", uniqueConstraints = {
@UniqueConstraint(columnNames = "ID")})
public class Sample implements Serializable {

    /** log4j category */
    private static final Logger log = Logger.getLogger(Category.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="original_text", length = 256)
    private String text;

    @Column(name="error_text", length = 256)
    private String errorText;

    @Transient
    private static final String MISSED_TEXT = "missed";

    public Sample() {
    }

    public Sample(String text, String errorText) {
        this.text = text.toLowerCase();
        if(errorText==null || errorText.isEmpty()){
            errorText = MISSED_TEXT;
        }
        this.errorText = errorText.toLowerCase();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text.trim().toLowerCase();
    }

    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) {
        if(errorText.isEmpty()){
            errorText = MISSED_TEXT;
        }
        this.errorText = errorText.trim().toLowerCase();
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
         final Sample otherSample = (Sample) obj;
         if (!this.getId().equals(otherSample.getId()))
         {
             return false;
         }

         return true;
     }

    public String getSampleName()
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
        return Constants.SAMPLE;
    }    
}
